com.example.prm392_final_project
│── api                     # API và Retrofit Client
│   ├── ApiService.java
│   ├── RetrofitClient.java
│
│── model                   # Các Model (Dữ liệu)
│   ├── User.java
│   ├── Course.java
│   ├── Payment.java
│   ├── Review.java
│
│── repository              # Repository xử lý dữ liệu từ API/Database
│   ├── UserRepository.java
│   ├── CourseRepository.java
│   ├── PaymentRepository.java
│
│── viewmodel               # ViewModel để giao tiếp giữa Repository và UI
│   ├── UserViewModel.java
│   ├── CourseViewModel.java
│   ├── PaymentViewModel.java
│
│── adapter                 # Adapter cho RecyclerView
│   ├── CourseAdapter.java
│   ├── UserAdapter.java
│   ├── ReviewAdapter.java
│
│── ui                      # UI (Activity & Fragment)
│   │── activity            # Activity chính của ứng dụng
│   │   ├── MainActivity.java # Lớp Application chính
│   │   ├── AuthActivity.java
│   │   ├── CourseDetailActivity.java
│   │   ├── PaymentActivity.java
│   │   ├── AdminActivity.java
│   │── fragment            # Fragment trong từng Activity
│   │   ├── HomeFragment.java
│   │   ├── MyCoursesFragment.java
│   │   ├── ProfileFragment.java
│   │   ├── LoginFragment.java
│   │   ├── RegisterFragment.java
│   │   ├── ForgotPasswordFragment.java
│   │   ├── CourseInfoFragment.java
│   │   ├── ChapterListFragment.java
│   │   ├── VideoPlayerFragment.java
│   │   ├── ManageCoursesFragment.java
│   │   ├── ManageUsersFragment.java
│   │   ├── SupportRequestsFragment.java
│   │   ├── ReportsFragment.java
│
│── utils                   # Chứa các class tiện ích
│   ├── Constants.java
│   ├── PrefsHelper.java
│
└──     

Công nghệ sử dụng

Ngôn ngữ lập trình: Java

Framework: Android Jetpack (MVVM)

Networking: Retrofit

Database: Room

UI Components: RecyclerView, ViewModel, LiveData
