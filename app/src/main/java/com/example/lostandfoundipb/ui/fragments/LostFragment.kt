package com.example.lostandfoundipb.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.lostandfoundipb.R
import com.example.lostandfoundipb.Utils.SessionManagement
import com.example.lostandfoundipb.adapters.PostAdapter
import com.example.lostandfoundipb.retrofit.ApiService
import com.example.lostandfoundipb.retrofit.models.Post
import com.example.lostandfoundipb.ui.MainActivity
import com.example.lostandfoundipb.ui.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_found.view.*
import kotlinx.android.synthetic.main.fragment_found.view.found_progress
import kotlinx.android.synthetic.main.fragment_lost.view.*
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.toast


class LostFragment : Fragment(){
    lateinit var progress: LinearLayout
    lateinit var adapterPost: PostAdapter
    lateinit var lostRv: RecyclerView
    private var lost: MutableList<Post.Post> = mutableListOf()
    lateinit var session: SessionManagement
    private lateinit var refresh: SwipeRefreshLayout
    private val apiService by lazy {
        context?.let { ApiService.create(it) }
    }
    private lateinit var viewModel : PostViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_lost,container,false)
        session = SessionManagement(requireContext())
        viewModel = ViewModelProviders.of(requireActivity()).get(PostViewModel::class.java)
        init(view)
        getPost()
        return view
    }


    @SuppressLint("SetTextI18n")
    private fun init(view: View) {
        progress = view.lost_progress
        lostRv = view.lost_rv
        refresh = view.findViewById(R.id.lost_swipe_refresh)
        adapterPost = PostAdapter(lost, this.context!!)
        lostRv.adapter = adapterPost
        lostRv.layoutManager = LinearLayoutManager(context)

        refresh.onRefresh {
            refresh.isRefreshing = false
            getPost()
        }

    }

    private fun getPost(){
        showProgress(true)
        viewModel.getPost(apiService!!)
        viewModel.postString.observe({lifecycle},{s ->
            if(s == "Success"){
                viewModel.postResult.observe({lifecycle},{
                    if(it.success){
                        lost.clear()
                        for (data in it.post!!){
                            if(!data.status){
                                lost.add(data)
                            }
                        }
                        adapterPost.notifyDataSetChanged()
                        showProgress(false)

                    }
                    else{
                        toast(it.message.toString())
                        showProgress(false)
                    }
                })
            }
            else{
                s.let {
                    toast(it)
                }
                showProgress(false)
            }
        })
    }

    private fun showProgress(show: Boolean){
        progress.bringToFront()
        progress.visibility = if(show) View.VISIBLE else View.GONE
        if(activity!=null) (activity as MainActivity).disableTouch(show)
    }
}