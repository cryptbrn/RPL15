# Lost and Found IPB

## Nama Sistem, Paralel, Kelompok, Nama Asisten Praktikum
Nama Sistem : Lost and Found IPB <br>
Paralel : P2 <br>
Kelompok : 15 <br>
Nama Asisten Praktikum : 
1. G64180019 Levina Siatono
2. G64180080 Ali Naufal Ammarullah
3. G64180117 M. Fauzan Ramadhan

## Anggota Kelompok 15
1. G14190047 Alvin Christian : UI Designer & Android Developer
2. G64190064 Nabil Abidi : Android Developer & UI Designer
3. G64190083 Muhammad Reyhan : Backend Developer & Android Developer

## Deskripsi Singkat Aplikasi
Lost and Found IPB merupakan sebuah aplikasi berbasis mobile yang berguna untuk mempertemukan orang-orang yang mengalami kehilangan barang dan orang-orang yang menemukan barang-barang tercecer yang terjadi di sekitar wilayah kampus IPB. Aplikasi ini akan memiliki fitur untuk membuat post mengenai barang-barang yang hilang, membuat post barang-barang tercecer yang ditemukan, dan menghubungi penemu/pencari barang tersebut melalui kontak yang tersedia.

## User Analysis
Target dari aplikasi ini adalah anggota civitas IPB yang melakukan banyak aktivitas sehari-hari di lingkungan kampus IPB. Banyaknya aktivitas yang dilakukan terkadang membuat kita mengalami kehilangan barang atau bahkan menemukan barang milik orang lain. Aplikasi ini bertujuan untuk mempertemukan kedua pihak sehingga para civitas IPB dapat saling membantu satu sama lain dalam mencari barang yang terhilang.

### User Story
- Sebagai pengguna yang terdaftar, agar dapat menemukan barang yang hilang, saya dapat mem-posting kehilangan barang.
- Sebagai pengguna yang terdaftar, ketika saya menemukan barang seseorang, saya dapat melaporkan penemuan barang.
- Sebagai pengguna yang telah log out, agar dapat menggunakan aplikasi, saya perlu melakukan login dengan memasukkan username dan password.


## Spesifikasi Teknis Lingkungan Pengembangan
### Perangkat Keras (Hardware):  
Prosesor	: AMD A8 7410 APU  
Memori	: 8 GB DDR3  
Penyimpanan	: 120GB SSD dan 500GB HDD  
Kartu Grafis	: Radeon R5  

### Perangkat Lunak (Software):  
Sistem Operasi : Ubuntu LTS 20 dan Windows 10  

### Tech Stack:
Mobile Front-End  
IDE		 : Android Studio  
Back-End  
Text Editor	: Visual Studio Code  
Database	: PostgreSql  
Server		: Apache (Development)  
Framework	: Laravel  

## Hasil dan Pembahasan

### Use Case Diagram
![ERD   Usecase Lost and Found  IPB-Usecase](https://user-images.githubusercontent.com/70255413/119362585-0f8fb980-bcd7-11eb-9d64-8ac92a7bb177.png)

### Activity Diagram
![AD alur utama](https://github.com/nabilabidi/hello-world/blob/main/Activity%20Diagram%20Lost%20and%20Found%20IPB-alur%20utama.png)
![AD alur membuat akun](https://github.com/nabilabidi/hello-world/blob/main/alur%20membuat%20akun)
![AD alur menemukan barang](https://github.com/nabilabidi/hello-world/blob/main/alur%20menemukan%20barang)
![AD alur mencari barang](https://github.com/nabilabidi/hello-world/blob/main/alur%20mencari%20barang)
![AD alur deaktivasi akun](https://github.com/nabilabidi/hello-world/blob/main/alur%20deaktivasi%20akun)
![AD alur ganti password](https://github.com/nabilabidi/hello-world/blob/main/alur%20ganti%20password)

### Class Diagram
![Class Diagram Lost and Found IPB](https://user-images.githubusercontent.com/70255413/120824350-91f36580-c582-11eb-9f59-01546f253a34.png)

### Entity Relationship Diagram
![ERD   Usecase Lost and Found  IPB-ERD](https://user-images.githubusercontent.com/70255413/119362506-f8e96280-bcd6-11eb-9d16-91341b1b2269.png)

### System Architecture
![AD](https://user-images.githubusercontent.com/70255413/120831952-4e9cf500-c58a-11eb-83a1-a6e41772c84d.jpeg)

### Fungsi Utama yang Dikembangkan
Pengguna dapat membuat postingan baru yang mengenai barang yang sedang ia cari atau barang yang ia temukan. Pengguna lainnya dapat melihat postingan yang ada dan menghubungi pengguna yang membuat postingan tersebut. 

### Fungsi CRUD
- Create:
  - pengguna dapat membuat akun
  - pengguna dapat membuat postingan baru
- Read:
  - pengguna dapat melihat postingan kehilangan barang yang tersedia
  - pengguna dapat melihat postingan penemuan barang yang tersedia
  - pengguna dapat melihat detail postingan
- Update:
  - pengguna dapat mengubah data diri
  - pengguna dapat mengubah detail postingan
  - pengguna dapat mengubah status barang (belum/sudah ditemukan)
- Delete:
  - pengguna dapat menghapus postingan
  - pengguna dapat melaukan deaktivasi akun

## Hasil Implementasi Perangkat Lunak
### Screenshot Sistem
Login Page<br>
![login_360x640](https://user-images.githubusercontent.com/70255413/120834410-2b277980-c58d-11eb-8db2-4521995d09e9.jpg)

Register Page<br>
![regist_360x640](https://user-images.githubusercontent.com/70255413/120834418-2f539700-c58d-11eb-94e0-c8e925db2057.jpg)

Home<br>
![home](https://user-images.githubusercontent.com/70255413/120833321-d59e9d00-c58b-11eb-8353-58a47bf949eb.png)

Formulir Pembuatan Post<br>
![form_360x640](https://user-images.githubusercontent.com/70255413/120834430-337fb480-c58d-11eb-9d44-e1d7ab3bcb2e.jpg)

Contoh Halaman Postingan Barang<br>
![post_360x640](https://user-images.githubusercontent.com/70255413/120834445-37abd200-c58d-11eb-9659-d93a0f2d9c0e.png)

Profile Page<br>
![profil_360x640](https://user-images.githubusercontent.com/70255413/121228430-f0884e80-c8b6-11eb-916b-5c65b881680e.jpeg)

### Link Aplikasi
https://lostandfoundipb.herokuapp.com/

## Testing (Test Cases)
### Positive Cases
| Test Case ID | Test Scenario | Test Steps | Test Data | Expected Result | Actual Result | Pass/Fail |
| ------------- | ------------- | ------------- | ------------- | ------------- | ------------- | ------------- |
| P1 | Membuat akun baru | 1. Buka aplikasi<br> 2. Klik “register here”<br> 3. Isi form pendaftaran<br> 4. Klik daftar<br> 5. Verifikasi email | Email: blackanalogman@gmail.com <br> Pass: 123123 | Berhasil membuat akun | Berhasil membuat akun | Pass |
| P2 | Login dengan akun yang telah dibuat | 1. Buka aplikasi<br> 2. Masukkan email/ username<br> 3. Masukkan password<br> 4. Klik log in | Email: blackanalogman@gmail.com <br> Pass: 123123 | Berhasil login dengan akun yang telah dibuat | Berhasil login dengan akun yang telah dibuat | Pass |
| P3 | Mengubah data-data profil user | 1. Klik profile page<br> 2. Pilih “Edit Profile”<br> 3. Ubah beberapa data<br> 4. Klik simpan | Name: Edited User <br>Username: edited_username<br> Phone: 085811223344<br> NIM: G100<br> Faculty: FMIPA<br> Department: Computer Science<br> Batch: 58 | Berhasil mengubah data user | Berhasil mengubah data user | Pass |
| P4 | Mengganti foto profile user  | 1. Klik profile page<br> 2. Pilih “Edit Profile”<br> 3. Pilih “Edit Profile Picture”<br> 4. Klik “Select Picture from Gallery”<br> 5. Pilih Gambar<br> 6. Klik “Save” | Foto: new_photo.jpg | Berhasil mengubah foto profile user | Berhasil mengubah foto profile user | Pass |
| P5 | Membuat postingan baru | 1. Klik “I found something” atau “I lost something”<br> 2. Pilih kategori item<br> 3. Klik “Create a new post”<br> 4. Isi form postingan<br> 5. Klik “Post” | Post Type: Lost<br> Post Title: Buku Pengantar Sosiologi Umum<br> Post Description: Buku Pengantar Sosiologi Umum, Hilang di sekitar CCR hari Senin, ada nama “Venti” di halaman pertama<br> Item Name: Sosiologi Umum<br> Item Category: Important Documents<br> Item Last Location: CCR | Berhasil membuat postingan baru | Berhasil membuat postingan baru | Pass |
| P6 | Mengubah detail postingan | 1. Klik “My Post”<br> 2. Pilih salah satu post yang dimiliki<br> 3. Klik “Edit Post”<br> 4. Pilih “Edit Post Detail”<br> 5. Ubah data yang diinginkan<br> 6. Klik “Edit Post” | Post Type: Lost<br> Post Title: Edited_Buku Pengantar Sosiologi Umum<br> Post Description: Edited_Buku Pengantar Sosiologi Umum, Hilang di sekitar CCR hari Senin, ada nama “Venti” di halaman pertama<br> Item Name: Edited_Sosiologi Umum<br> Item Category: Stationery and Module Book<br> Item Last Location: Edited_CCR | Berhasil mengubah detail postingan | Berhasil mengubah detail postingan | Pass |
| P7 | Mengubah status postingan jika barang sudah kembali ke pemiliknya | 1. Klik “My Post”<br> 2. Klik postingan barang yang telah kembali ke pemiliknya<br> 3. Klik “Edit Post”<br> 4. Pilih “Edit Post Status”<br> 5. Pilih “Item Found”<br> 6. Klik “Submit” | Post: Edited_Buku Pengantar Sosiologi Umum<br> Status: “Item Found” | Berhasil mengubah status postingan | Berhasil mengubah status postingan | Pass |
| P8 | Menghubungi user lain melalui whatsapp | 1. Klik sebuah post<br> 2. Klik “Message”<br> 3. Pilih “Whatsapp” | Post: Edited_Buku Pengantar Sosiologi Umum | Berhasil menghubungi user lain melalui whatsapp | Berhasil menghubungi user lain melalui whatsapp | Pass |
| P9 | Menghapus postingan | 1. Klik “My Post”<br> 2. Pilih postingan yang ingin dihapus<br> 3. Klik “Delete Post”<br> 4. Klik “Yes” | Post: Edited_Buku Pengantar Sosiologi Umum | Postingan berhasil dihapus | Postingan berhasil dihapus | Pass |
| P10 | Mencari dengan menggunakan filter kategori barang | 1. Klik “I found something” atau “I lost something”<br> 2. Pilih kategori item<br> 3. Klik “See existing posts” | I found Something<br> Stationery and Module Book | Berhasil mencari barang (filter) berdasarkan kategori | Berhasil mencari barang (filter) berdasarkan kategori | Pass |

### Negative Cases
| Test Case ID | Test Scenario | Test Steps | Test Data | Expected Result | Actual Result | Success/ Fail |
| ------------ | ------------- | ---------- | --------- | --------------- | ------------- | ------------- |
| N1 | Membuat sebuah akun dengan mengosongkan email | 1. Buka aplikasi<br> 2. Klik “register here”<br> 3. Isi form kecuali email <br> 4. Klik “register now”<br> | Name: nabil abidi<br> Username: nabilabidi<br> Email:<br> Telephone: 08123456789<br> Role: Student<br> NIM: G64190000<br> Faculty: fmipa<br> Department: ilkom<br> Batch: 56 | Muncul pesan “This field is required” pada kolom email | Sesuai expected result | Success |
| N2 | Membuat sebuah akun dengan mengisi password dan confirm password berbeda | 1. Buka aplikasi<br> 2. Klik “register here”<br> 3. Isi form<br> 4. Klik “register now” | Name: nabil abidi<br> Username: nabilabidi<br> Email: nabilabidi@apps.ipb.ac.id<br> Telephone: 08123456789<br> Password: password<br> Confirm Password: passw0rd <br> Role: Student<br> NIM: G64190000<br> Faculty: fmipa<br> Department: ilkom<br> Batch: 56 | Muncul pesan “Password doesn’t match” pada kolom Confirm Password | Sesuai expected result | Success |
| N3 | Mencoba login tanpa melakukan verifikasi email | 1. Buka aplikasi<br> 2. Masukkan email/username<br> 3. Masukkan password<br> 4. Klik “log in” | Username: nabilabidi<br> Password: password | Muncul pesan “You need to validate your email address first, we have sent your verification link, please check your email. | Sesuai expected result | Success |
| N4 | Membuat post tanpa memasukkan judul | 1. Buka dan login ke aplikasi<br> 2. Klik menu lost<br> 3. Pilih category<br> 4. Klik create new post<br> 5. Isi detail post kecuali judul | Post type: Lost<br> Post title:<br> Post Description: telah ditemukan tumblr tuppleware<br> Item Name: botol<br> Item Category: Lunch Box & Bottles<br> Item Last Location<br> Item Picture: botol.jpg | Muncul pesan “This field is required” | Sesuai expected result | Success |
| N5 | Membuat post tanpa mengunggah gambar | 1. Buka dan login ke aplikasi<br> 2. Klik menu lost<br> 3. Pilih category<br> 4. Klik create new post<br> 5. Isi detail post kecuali gambar | Post Type: Lost<br> Post Title: Buku Perilaku Konsumen<br> Post Description: telah ditemukan buku perilaku konsumen di komlong<br> Item Name: Buku Perilaku Konsumen<br> Item Category: Stationary & Module Book<br> Item Last Location: komlong<br> Item picture: | Muncul alert “Please insert Item Image first” | Sesuai expected result | Success |


## Saran untuk Pengembangan Selanjutnya
- Jika Mendeploy Backend Service ke heroku, ada baiknya menyiapkan Amazon S3 untuk penyimpanan file atau bisa mengubah penyimpanan file ke public/database pada laravel (namun hal ini akan membebani storage pada heroku)
- Dapat menambahkan fitur untuk mengidentifikasi kepemilikan barang pada aplikasi. Hal ini akan membuat lokasi barang menjadi lebih terkontrol.
- Picture/foto/gambar untuk setiap postingan barang dapat dibuat lebih dari satu agar dapat memberikan gambaran yang lebih komprehensif dan membantu user dalam proses identifikasi barang
- Dapat disambungkan dengan SSO IPB sehingga hanya civitas IPB yang dapat mengakses aplikasi 
- Dapat ditambahkan fitur notifikasi sehingga jika ada post baru yang sesuai dengan barang yang dicari (memiliki ciri-ciri tertentu), user dapat menerima pemberitahuan
