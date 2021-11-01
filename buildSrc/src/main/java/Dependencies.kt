object Android {
    // android
    const val id = "place.pic.android.plus"
    const val minSdk = 26
    const val targetSdk = 31
    const val versionCode = 1
    const val versionName = "1.0"
}

object Libs {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"

    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val material = "com.google.android.material:material:${Versions.material}"

    object Hilt {
        const val hiltPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Lifecycle {
        const val viewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle_version}"
        const val liveData =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle_version}"
        const val lifecycleCompiler =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle_version}"
    }

    object Kotlin {
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"
    }
}

object TestLib {
    const val junit = "junit:junit:${Versions.junit}"
    const val extJunit = "androidx.test.ext:junit:${Versions.extJunit}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
}
