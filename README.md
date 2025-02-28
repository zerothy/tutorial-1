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

Selain itu, saya juga telah menerapkan Continuous Deployment dengan memanfaatkan layanan PaaS Koyeb yang menggunakan pendekatan pull-based. Koyeb secara otomatis menarik perubahan terbaru dari repositori dan melakukan redeploy, sehingga aplikasi web dapat terus diperbarui secara otomatis.# ADVANCED PROGRAMMING TUTORIAL
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

## Module 3
## Reflection 1.1
## Penerapan Prinsip SOLID

Setelah mempelajari tentang SOLID principles, saya menerapkan beberapa prinsip berikut pada proyek saya:

1. **Single Responsibility Principle (SRP)**
   Saya memisahkan tanggung jawab antara Controller, Service, dan Repository dengan lebih jelas. Misalnya, ProductController hanya bertanggung jawab untuk menangani request dan response, ProductService hanya fokus pada logika bisnis, dan ProductRepository khusus untuk operasi database.

   ```java
   // Controller hanya bertanggung jawab menangani HTTP request
   @Controller
   @RequestMapping("/product")
   public class ProductController {
        // ...
        @GetMapping("/create")
        public String createProductPage(Model model) {
            Product product = new Product();
            model.addAttribute("product", product);
            return "createProduct";
        }
       // ...
   }

   // Service bertanggung jawab untuk logika bisnis
   @Service
    public class ProductServiceImpl implements ProductService {

        @Autowired
        private ProductRepository productRepository;

        @Override
        public Product create(Product product) {
            if (product.getProductName() == null || product.getProductName().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty.");
            }
            if (product.getProductQuantity() < 0) {
                throw new IllegalArgumentException("Product quantity cannot be negative.");
            }
            return productRepository.create(product);
        }

        // ...
   }
   ```

2. **Open/Closed Principle (OCP)**
   Saya memodifikasi kode agar lebih mudah dikembangkan tanpa mengubah kode yang sudah ada. Contohnya dengan membuat interface untuk service yang memungkinkan implementasi baru ditambahkan tanpa mengubah kode pengguna.

   ```java
   // Interface untuk service
    public interface ProductService {
        public Product create(Product product);
        public List<Product> findAll();
        public Product get(String id);
        public void update(Product product);
        public boolean delete(String id);
    }


   // Implementasi standar
   @Service
   public class ProductServiceImpl implements ProductService {
       // implementasi method
   }

   // Implementasi baru (misalnya untuk caching) tanpa mengubah kode yang menggunakan ProductService
   @Service
   @Primary
   public class CachedProductServiceImpl implements ProductService {
       private final ProductServiceImpl delegate;
       private final Cache<Long, Product> cache;
       
       // implementasi dengan caching
   }
   ```

3. **Liskov Substitution Principle (LSP)**
   Saya memastikan bahwa subclass dapat menggantikan parent class tanpa mengubah perilaku program. Misalnya, implementasi service dapat dipertukarkan selama mengikuti kontrak interface.

   ```java
   // Contoh kalau perlu caching
   public interface ProductService {
       Product create(Product product);
       List<Product> findAll();
       Product get(String id);
       void update(Product product);
       boolean delete(String id);
   }

   @Service
   public class ProductServiceImpl implements ProductService {
       // implementasi service
   }

   @Service
   @Primary
   public class CachedProductServiceImpl implements ProductService {
       private final ProductServiceImpl delegate;
       private final Cache<Long, Product> cache;

       // implementasi dengan caching
   }
   ```

4. **Interface Segregation Principle (ISP)**
   Saya memecah interface besar menjadi interface yang lebih kecil dan spesifik, sehingga class tidak perlu mengimplementasikan metode yang tidak mereka butuhkan. Pada kode saya, interface sudah tidak bisa dipecah lebih kecil lagi.

   ```java
   public interface CarService {
       // implementasi abstrak
   }
   ```

5. **Dependency Inversion Principle (DIP)**
   Saya menggunakan dependency injection untuk mengurangi ketergantungan langsung antara komponen. Class tingkat tinggi (seperti Controller) hanya bergantung pada abstraksi (interface) dan bukan implementasi konkret.

   ```java
   // High-level module bergantung pada abstraksi
   @Service
   public class ProductServiceImpl implements ProductService {
       private final ProductRepository productRepository; // abstraksi, bukan implementasi konkret
       
       // ...
   }
   ```

## 2. Keuntungan Penerapan Prinsip SOLID

1. **Maintainability yang Lebih Baik**
   Dengan pemisahan tanggung jawab yang jelas (SRP), kode menjadi lebih mudah dipahami dan dimodifikasi. Misalnya, saat saya perlu mengubah logika validasi produk, saya hanya perlu fokus pada service layer tanpa mengubah controller.

2. **Kemudahan dalam Pengujian**
   Komponen yang terisolasi dengan baik lebih mudah untuk diuji. Saya dapat membuat unit test untuk ProductService tanpa ketergantungan pada ProductRepository dengan menggunakan mock objects.

3. **Ekstensi Lebih Mudah**
   Dengan menerapkan OCP, saya dapat menambahkan fitur baru seperti validasi tambahan atau logika bisnis baru tanpa mengubah kode yang sudah ada, hanya dengan mengextend atau mengimplementasikan interface yang tersedia.

4. **Fleksibilitas dan Penggunaan Ulang**
   Dengan DIP, saya dapat dengan mudah mengubah implementasi database dari JPA ke implementasi lain tanpa mengubah logika bisnis, karena service hanya bergantung pada interface repository.

5. **Kode Lebih Modular**
   Dengan ISP, saya membuat interface yang lebih spesifik dan kohesif, sehingga perubahan pada satu aspek tidak mempengaruhi aspek lain yang tidak terkait.

## 3. Kerugian Tidak Menerapkan Prinsip SOLID

1. **Kode Sulit di Maintain**
   Tanpa SRP, saya pernah membuat ProductController yang menangani tidak hanya request mapping tetapi juga logika bisnis dan validasi, sehingga class menjadi sangat besar dan sulit dipahami.

2. **Kesulitan dalam Pengujian**
   Tanpa pemisahan dependency yang jelas, pengujian menjadi sulit. Misalnya, ketika ProductService langsung membuat instance ProductRepository, saya tidak dapat menguji service tanpa ikut menguji repository.

3. **Perubahan Kode yang Berisiko**
   Tanpa OCP, modifikasi fitur yang sudah ada berisiko mempengaruhi fungsi lain yang tidak terkait. Contohnya, saat saya menambahkan validasi baru pada ProductService, saya harus mengubah kode yang sudah berjalan, yang berpotensi menimbulkan bug.

4. **Duplikasi Kode**
   Tanpa prinsip SOLID, saya sering mengulang kode yang sama di beberapa tempat. Misalnya, logika validasi yang sama diimplementasikan di berbagai controller karena tidak ada abstraksi yang tepat.

5. **Ketergantungan Tinggi Antar Komponen**
   Tanpa DIP, komponen-komponen sistem saya saling bergantung secara langsung. Saat ProductRepository berubah, ProductService dan bahkan ProductController harus ikut berubah, menciptakan efek domino yang sulit dikelola.

Dengan menerapkan prinsip SOLID, saya dapat membuat kode yang lebih mudah dipelihara, fleksibel, dan tangguh terhadap perubahan di masa depan.