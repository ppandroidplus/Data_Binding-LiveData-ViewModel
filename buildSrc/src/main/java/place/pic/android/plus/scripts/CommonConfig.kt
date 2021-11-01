package place.pic.android.plus.scripts

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class CommonConfig : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            plugins.apply("kotlin-android")

            configAndroidBlock()
            configDependencies()
            configTestDependencies()
        }
    }

    private fun Project.configAndroidBlock() = this.extensions.getByType<BaseExtension>()
        .run {
            compileSdkVersion(Android.targetSdk)

            defaultConfig {
                minSdkVersion(Android.minSdk)
                targetSdkVersion(Android.targetSdk)
                testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
                consumerProguardFile("consumer-rules.pro")
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }

            buildTypes {
                getByName("release") {
                    isMinifyEnabled = false
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                }
            }
        }

    private fun Project.configDependencies() = this.extensions.getByType<BaseExtension>()
        .run {
            dependencies {
                add("implementation", Libs.coreKtx)
                add("implementation", Libs.appcompat)
                add("implementation", Libs.material)
            }
        }

    private fun Project.configTestDependencies() = this.extensions.getByType<BaseExtension>()
        .run {
            dependencies {
                add("testImplementation", TestLib.junit)
                add("androidTestImplementation", TestLib.extJunit)
                add("androidTestImplementation", TestLib.espresso)
            }
        }
}
