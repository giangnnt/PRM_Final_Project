plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.prm392_final_project"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.prm392_final_project"
        minSdk = 25
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}
dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)

    implementation("androidx.activity:activity:1.9.0")
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // ViewModel and LiveData (Jetpack)
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.7.0")
    implementation("androidx.lifecycle:lifecycle-livedata:2.7.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-savedstate:2.7.0") // ✅ Added

    // Navigation Component (Optimized)
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7") // ✅ Kotlin-friendly
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7") // ✅ Kotlin-friendly UI

    // Retrofit & OkHttp
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:okhttp:4.11.0") // ✅ Missing OkHttp core
    implementation("com.squareup.okhttp3:logging-interceptor:4.11.0")

    // Room Database
    implementation("androidx.room:room-runtime:2.6.1")
    annotationProcessor("androidx.room:room-compiler:2.6.1")

    // RecyclerView
    implementation("androidx.recyclerview:recyclerview:1.3.2")

    // Paging 3
    implementation("androidx.paging:paging-runtime:3.2.1")

    // Glide (Fixed duplicate)
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // ExoPlayer
    implementation("androidx.media3:media3-exoplayer:1.2.1")
    implementation("androidx.media3:media3-ui:1.2.1")

    // Gson
    implementation("com.google.code.gson:gson:2.10.1")

    // GOOGLE SIGN IN
    implementation ("com.google.android.gms:play-services-auth:20.7.0")
}
