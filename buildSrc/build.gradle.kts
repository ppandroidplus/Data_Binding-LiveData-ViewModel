plugins{
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}
val androidGradleVersion = "4.2.2"
val kotlinVersion = "1.5.31"

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    languageVersion = kotlinVersion
}

gradlePlugin {
    plugins {
        register("android-default-config") {
            id = "android-default-config"
            implementationClass = "place.pic.android.plus.scripts.CommonConfig"
        }
    }
}

repositories {
    google()
    mavenCentral()
}

dependencies {
    implementation("com.android.tools.build:gradle:$androidGradleVersion")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation(gradleApi())
    implementation(localGroovy())
}
