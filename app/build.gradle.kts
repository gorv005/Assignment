plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    kotlin("kapt")
    alias(libs.plugins.dagger)
}

android {
    namespace = "com.tcs.assignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tcs.assignment"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    //Dagger hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    implementation(libs.androidx.hilt.navigation.compose)

    implementation(libs.compose.material)
    implementation(libs.hilt.compose.navigation)


    //Retrofit
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //Jetpack Compose pagination
    implementation(libs.androidx.paging.runtime)
    implementation(libs.androidx.paging.compose)

    //Coil image loading library
    implementation(libs.coil.compose)
    implementation(libs.coil.svg)

    implementation(project(":data"))
    implementation(project(":common"))
    implementation(project(":domain"))

    //Mockk
    testImplementation (libs.mockk)

    //Mockito library for testing
    testImplementation (libs.mockito.core)
    testImplementation (libs.mockito.inline)
    testImplementation (libs.mockito.junit.jupiter)
    //Coroutine Test
    testImplementation (libs.kotlinx.coroutines.test)
    //Google truth library
    testImplementation (libs.truth)
    androidTestImplementation (libs.truth)
    //Android testing core library
    testImplementation (libs.androidx.core.testing)
}