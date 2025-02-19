# ADVANCED PROGRAMMING TUTORIAL
#### Nama: Joe Mathew Rusli
#### NPM: 2306152310
#### Kelas: A

# Reflection

## Module 1
## Reflection 1.1
## Prinsip Clean Code yang Diterapkan
Setelah saya mempelajari tentang clean code, yang mana ternyata bukan sekedar kode bersih dan rapi saja, saya menerapkan beberapa hal berikut:
1. **Meaningful Names**: Saya merasa bahwa nama kelas, metode, dan variabel yang saya buat sudah deskriptif.
2. **Single Responsibility Principle (SRP)**: Saya membuat setiap kelas dan metode memiliki tanggung jawab yang jelas.
3. **DRY (Don't Repeat Yourself)**: Kode yang berulang sudah saya dihindari.
4. **Error Handling**: Penanganan error saya harap sudah diimplementasikan dengan baik.
5. **Consistency**: Format kode yang dibuat konsisten dan mudah dibaca.
6. **Git**: Penggunaan git flow dan branching yang rapi dan benar, serta menggunakan penamaan commit yang mudah dimengerti.

## Praktik Secure Coding yang Diterapkan
Saya menambahkan beberapa sekuritas pada kode yang saya buat, agar pengalaman pengguna menjadi lebih baik serta mengurangi ancaman terhadap web saya.
1. **Validasi Input**: Input sudah divalidasi untuk memastikan data yang masuk valid.
2. **Penanganan Eksplisit untuk Kasus Tidak Ditemukan**: Pesan error yang jelas diberikan ketika data tidak ditemukan.
3. **Penggunaan HTTP Methods yang Tepat**: Metode HTTP sesuai dengan operasi yang dilakukan.
4. **Penggunaan `RedirectAttributes`**: Feedback ke pengguna disampaikan dengan baik.

## Area yang Perlu Ditingkatkan
1. **Penanganan Error yang Lebih Baik**: Gunakan exception yang lebih spesifik pada setiap case.
4. **Pengamanan CSRF**: Aktifkan Spring Security untuk melindungi dari serangan CSRF.

Dengan perbaikan ini, kode akan menjadi lebih robust, aman, dan mudah dipelihara.

## Reflection 1.2
## 1.
Setelah membuat unit test dan functional test, saya merasa kode yang saya buat setidaknya aman dari bug-bug yang saya cover pada test-nya. Saya dapat mengimprove kode saya serta membuatnya menjadi lebih aman saat pembuatan unit test.

Untuk batasan unit test menurut saya bebas, asal tidak terlalu banyak yang berulang saja. Dalam artian, bila sudah ada test untuk create product dan itu bekerja dengan baik, tidak perlu dibuat hal yang sama berulang-ulang lagi hanya dengan mengetes product yang berbeda. Lain cerita apabila pembuatan beberapa product memang diperlukan untuk suatu testing khusus.
    
Untuk memastikan apakah test yang kita buat sudah cukup untuk memverify kode kita, kita perlu memetakan setiap langkah-langkah dengan detail, baik create, edit, dan delete. Dan untuk setiap langkah tersebut, kita buat unit testnya. Memang terkadang akan ada yang terlewat, tapi dengan membaca ulang dengan pelan, diharapkan agar dapat menemukan hal-hal yang terlewat tersebut.

Apabila kode kita memiliki 100% Code Coverage, itu tidak menjamin bahwa kode kita bersih dari bugs, karena tentu saja itu semua hanya bagian yang kita test, dan dapat terjadi kesalahan dalam pembuatan test kita yang mengakibatkan bagian kode tersebut correct, padahal sebenarnya unit test kita lah yang salah. Bahkan menurut Sten Pittet, secara umum code coverage sekitar 80% adalah persentase yang cukup.

## 2.
### Code Cleanliness
Saya merasa kode menjadi tidak clean dikarenakan terdapat duplikasi dalam kode setup test. Duplikasi ini melanggar prinsip DRY (Don't Repeat Yourself). Dimana sebaiknya kita membuat suatu base file agar tidak mengulang kembali setup dan variabel yang ada.

Hal ini juga dapat mengurangi maintanibility karena jika terjadi suatu perubahan, harus dilakukan di banyak tempat.

Lagipun, dalam hal mengecek banyak items di list itu tidak perlu dibuat pada file baru, karena hal itu bisa termasuk dalam create product functional test. Sehingga akan menyebabkan terjadi lebih banyak redundan.

### Solusi
1. Saya menyarankan pembuatan base file untuk setup, agar tidak perlu mengulang setiap kali ingin membuat functional test. Hal ini dapat mengurangi kesulitan kita saat ingin mengubah base setup tersebut, dan akan sangat berguna dalam long run.
2. Menyatukan pengecekan total product pada list ke dalam `CreateProductFunctionalTest.java`, karena hal tersebut dapat termasuk kesana, bersama dengan edit dan delete, sehingga tidak perlu membuat file baru.

## Module 2
## Reflection 1.1
1. Ambiguous Mapping (Masalah Mapping yang Ambigu)

Terdapat dua metode di ProductController yang memiliki mapping yang sama, yaitu:
editProductPage dan handleIncorrectEditMethod keduanya dipetakan ke GET /product/edit/{id}.
Hal ini menyebabkan Spring bingung menentukan metode mana yang harus dipanggil, sehingga mengakibatkan error BeanCreationException.


Strategi saya adalah dengan menghapus metode handleIncorrectEditMethod karena redundan (tidak diperlukan) dan sudah ditangani oleh editProductPage.
Memastikan setiap endpoint memiliki mapping yang unik untuk menghindari konflik.

2. Redundant Code (Kode yang Berulang dan Tidak Perlu)

Metode seperti handleIncorrectEditMethod dan handleIncorrectDeleteMethod tidak diperlukan karena fungsionalitasnya sudah tercakup dalam metode utama seperti editProductPage dan deleteProductPost.

Strategi saya adalah menghapus kode yang redundan untuk meningkatkan keterbacaan dan memudahkan pemeliharaan. Memastikan setiap metode memiliki tanggung jawab yang jelas dan tidak tumpang tindih.

3. Error Handling yang Tidak Konsisten

Beberapa metode menggunakan RedirectAttributes untuk menangani pesan error, sementara yang lain langsung menambahkan pesan error ke Model. Hal ini dapat menyebabkan inkonsistensi dalam penanganan error dan membuat kode sulit dipelihara.

Strategi saya adalah dengan  menstandarisasi penanganan error dengan selalu menggunakan RedirectAttributes untuk operasi yang melibatkan pengalihan (redirect). Memastikan pesan error selalu disertakan dalam RedirectAttributes untuk konsistensi.

4. Penamaan File yang Salah

Ketika Controller mengembalikan nama template seperti createProduct, productList, dan editProduct, saya mendapatkan error dimana file tidak ditemukan. Hal ini menyebabkan terjadinya error saat aplikasi mencoba me-render tampilan.

Strategi saya adalah hanya dengan mengganti nama file HTML menjadi sesuai dengan convention atau sama dengan

5. Maintainability Issues (Masalah Pemeliharaan)

Kode memiliki beberapa masalah pemeliharaan, seperti:
- Metode yang terlalu panjang dan kompleks.
- Penanganan error yang tidak konsisten.
- Kode yang redundan.

Strategi saya adalah dengan memecah metode yang terlalu panjang menjadi metode yang lebih kecil dan fokus pada satu tanggung jawab. Menstandarisasi penanganan error dan penggunaan RedirectAttributes. Menghapus kode yang redundan untuk meningkatkan keterbacaan dan kemudahan pemeliharaan.

## Reflection 1.2
Saya telah menambahkan workflow CI/CD ke dalam repositori GitHub, dan menurut saya, implementasi ini sudah sesuai dengan konsep Continuous Integration dan Continuous Deployment.

Berdasarkan definisi CI/CD, Continuous Integration merupakan proses integrasi dan verifikasi kode secara otomatis dan berkelanjutan dengan bantuan build scriptx. Dalam hal ini, saya telah menggunakan file ci.yml, sonarcloud.yml, dan scorecard.yml sebagai build script, sementara Gradle, SonarCloud, dan Scorecard berperan sebagai tools untuk memastikan bahwa kode yang saya kembangkan tetap dalam kondisi baik dan bebas dari masalah.

Selain itu, saya juga telah menerapkan Continuous Deployment dengan memanfaatkan layanan PaaS Koyeb yang menggunakan pendekatan pull-based. Koyeb secara otomatis menarik perubahan terbaru dari repositori dan melakukan redeploy, sehingga aplikasi web dapat terus diperbarui secara otomatis.