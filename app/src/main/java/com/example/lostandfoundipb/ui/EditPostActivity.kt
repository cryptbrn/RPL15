package com.example.lostandfoundipb.ui

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.MenuItem
import android.view.View
import android.view.WindowManager
import android.widget.Spinner
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.lostandfoundipb.R
import com.example.lostandfoundipb.Utils.SessionManagement
import com.example.lostandfoundipb.retrofit.ApiService
import com.example.lostandfoundipb.Utils.Global
import com.example.lostandfoundipb.retrofit.models.Post
import com.example.lostandfoundipb.ui.viewmodel.EditPostViewModel
import kotlinx.android.synthetic.main.activity_form_post.*
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.alert
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class EditPostActivity : AppCompatActivity() {
    private var picture: File = File("")
    private val sizeKb = 1024.0F
    private val sizeMb = sizeKb * sizeKb

    lateinit var type: String
    lateinit var title: String
    lateinit var description: String
    lateinit var name: String
    lateinit var category: String
    lateinit var location: String
    lateinit var session: SessionManagement
    lateinit var typePost: Spinner
    lateinit var itemCategory: Spinner
    lateinit var viewModel: EditPostViewModel

    lateinit var post: Post.Post


    companion object {
        private val REQUEST_IMAGE_CAPTURE = 1
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 2
    }
    private val apiService by lazy {
        this.let { ApiService.create(this) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_post)
        supportActionBar?.title = getString(R.string.edit_post)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        post = intent.getParcelableExtra("post")!!
        init()
        setData()
        onClick()
        editPostResult()
    }

    private fun init(){
        viewModel = ViewModelProviders.of(this).get(EditPostViewModel::class.java)
        session = SessionManagement(this)
        typePost = findViewById(R.id.form_post_type)
        itemCategory = findViewById(R.id.form_post_category)
        form_post_btn_post.setText(R.string.edit_post)
    }

    private fun setData() {
        form_post_title.setText(post.title)
        form_post_desc.setText(post.description)
        form_post_name.setText(post.item.name)
        form_post_location.setText(post.item.location)
        if(post.type){
            typePost.setSelection(1)
        }
        else{
            typePost.setSelection(2)
        }

        when (post.item.category) {
            "Important Documents" -> {
                itemCategory.setSelection(1)
            }
            "Gadgets & Electronics" -> {
                itemCategory.setSelection(2)
            }
            "Lunch Box & Bottles" -> {
                itemCategory.setSelection(3)
            }
            "Clothes & Accessories" -> {
                itemCategory.setSelection(4)
            }
            "Stationary" -> {
                itemCategory.setSelection(5)
            }
            "Others" -> {
                itemCategory.setSelection(6)
            }
        }

        post.item.picture.let { setImage(it) }


    }

    private fun onClick() {
        form_post_btn_post.setOnClickListener { checkPost() }
        form_post_picture_layout.setOnClickListener { chooseMethod() }
    }

    private fun chooseMethod() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle(getString(R.string.select_method))
        val pictureDialogItems = arrayOf(getString(R.string.from_gallery), getString(R.string.from_camera))
        pictureDialog.setItems(pictureDialogItems)
        { _, which ->
            when (which) {
                0 -> selectFromGallery()
                1 -> takeFromCamera()
            }
        }
        pictureDialog.show()
    }

    private fun takeFromCamera() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun selectFromGallery() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data!!.extras!!.get("data") as Bitmap
            picture = createTempFile(imageBitmap)
            setImageNew(imageBitmap)
        }

        else if (requestCode == REQUEST_SELECT_IMAGE_IN_ALBUM && resultCode == RESULT_OK) {
            val uri = data!!.data
            try {
                val imageBitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, uri)
                picture = createTempFile(imageBitmap)
                setImageNew(imageBitmap)
            } catch (excp: IOException) {
                excp.printStackTrace()
                println(excp.toString())
            }
        }
    }

    private fun createTempFile(bitmap: Bitmap): File {
        val file = File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "post" + "_" + System.currentTimeMillis().toString() + ".JPEG")
        var compressionConstant = 100
        var bitmapData = getByteArray(bitmap, compressionConstant)
        while ((bitmapData.size) > 500000) {
            when {
                compressionConstant > 50 -> compressionConstant -= 15
                compressionConstant > 25 -> compressionConstant -= 10
                compressionConstant > 10 -> compressionConstant -= 5
                else -> compressionConstant--
            }
            bitmapData = getByteArray(bitmap, compressionConstant)
        }

        try {
            val fos = FileOutputStream(file)
            fos.write(bitmapData)
            fos.flush()
            fos.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return file
    }

    private fun getByteArray(bitmap: Bitmap, compressConstant: Int): ByteArray {
        val bos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, compressConstant, bos)
        return bos.toByteArray()
    }

    private fun setImage(url: String) {
        Glide.with(this)
                .load(Global.URL_POST +url)
                .apply(RequestOptions().placeholder(R.drawable.ic_baseline_image_24))
                .apply(RequestOptions().centerCrop())
                .into(form_post_picture)
    }

    private fun setImageNew(imageBitmap: Bitmap) {
        Glide.with(this)
                .asBitmap()
                .load(imageBitmap)
                .apply(RequestOptions().placeholder(R.drawable.ic_baseline_image_24))
                .apply(RequestOptions().centerCrop())
                .into(form_post_picture)
    }

    private fun createPart(value: String): RequestBody {
        return RequestBody.create(
                MultipartBody.FORM, value
        )
    }

    private fun checkPost() {
        form_post_title.error = null
        form_post_desc.error = null
        form_post_name.error = null
        form_post_location.error = null

        var cancel = false
        var focusView: View? = null

        type = typePost.selectedItem.toString()
        title = form_post_title.text.toString()
        description = form_post_desc.text.toString()
        name = form_post_name.text.toString()
        category = itemCategory.selectedItem.toString()
        location = form_post_location.text.toString()




        if(type=="-- Select Type --"){
            toast("Please select Post Type first")
            cancel = true
            focusView = form_post_type
        }
        if (TextUtils.isEmpty(title)) {
            form_post_title.error = getString(R.string.error_empty)
            focusView = form_post_title
            cancel = true
        }
        if (TextUtils.isEmpty(description)) {
            form_post_desc.error = getString(R.string.error_empty)
            focusView = form_post_desc
            cancel = true
        }
        if (TextUtils.isEmpty(name)) {
            form_post_name.error = getString(R.string.error_empty)
            focusView = form_post_name
            cancel = true
        }
        if(category=="-- Select Category --"){
            toast("Please select Item Category first")
            cancel = true
            focusView = form_post_category
        }
        if (TextUtils.isEmpty(location)) {
            form_post_location.error = getString(R.string.error_empty)
            focusView = form_post_location
            cancel = true
        }


        if(cancel){
            focusView?.requestFocus()
        }
        else{
            val map: HashMap<String, RequestBody> = HashMap()
            map["title"] = createPart(title)
            map["description"] = createPart(description)
            map["type"] = createPart("0")
            if(type=="Found"){
                map["status"] = createPart("true")
            }
            else if(type=="Lost"){
                map["status"] = createPart("false")
            }

            map["item[name]"] = createPart(name)
            map["item[category]"] = createPart(category)
            map["item[location]"] = createPart(location)
            attemptPost(map)
        }

    }

    private fun attemptPost(map: HashMap<String, RequestBody>) {
        showProgress(true)

        val file = RequestBody.create("image/jpeg".toMediaTypeOrNull(), picture)
        val pictureReq = MultipartBody.Part.createFormData("item[picture]", picture.name, file)
        if(pictureReq.body.contentLength()<1){
            viewModel.editPost(apiService,map,post.id.toString())
        }
        else{
            viewModel.editPost(apiService,pictureReq,map,post.id.toString())
        }


    }

    private fun editPostResult(){
        viewModel.editPostResult.observe({lifecycle},{
            if(it.success){
                startActivity<MainActivity>("goto" to "home")
                finish()
                showProgress(false)
            }
            else{
                alert(it.message){
                    yesButton {  }
                }.show()
                showProgress(false)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showProgress(show: Boolean){
        form_post_progress.visibility = if(show) View.VISIBLE else View.GONE
        disableTouch(show)
    }

    private fun disableTouch(status: Boolean){
        if(status){
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        }
    }

}