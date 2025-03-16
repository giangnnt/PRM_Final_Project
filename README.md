# PRM_Final_Project

com.example.PRM_Final_Project
api # API và Retrofit Client
── ApiService.java
── RetrofitClient.java

model                
── User.java
── Course.java
── Payment.java
── Review.java

repository # Repository xử lý dữ liệu từ API/Database
── UserRepository.java
── CourseRepository.java
── PaymentRepository.java

viewmodel # ViewModel để giao tiếp giữa Repository và UI
── UserViewModel.java
── CourseViewModel.java
── PaymentViewModel.java

adapter   # Adapter cho RecyclerView
── CourseAdapter.java
── UserAdapter.java
── ReviewAdapter.java

ui   # UI (Activity & Fragment)
activity            # Activity chính của ứng dụng
── MainActivity.java
── AuthActivity.java
── CourseDetailActivity.java
── PaymentActivity.java
── AdminActivity.java

fragment    # Fragment trong từng Activity
── HomeFragment.java
── MyCoursesFragment.java
── ProfileFragment.java
── LoginFragment.java
── RegisterFragment.java
── ForgotPasswordFragment.java



