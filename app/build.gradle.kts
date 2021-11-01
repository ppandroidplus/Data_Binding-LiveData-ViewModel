plugins {
    id("com.android.application")
    `android-default-config`
}

android {
    defaultConfig {
        applicationId = Android.id
        versionCode = Android.versionCode
        versionName = Android.versionName
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation("androidx.constraintlayout:constraintlayout:2.1.1")
}
