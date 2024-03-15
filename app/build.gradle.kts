plugins {
    alias(libs.plugins.application.plugin)
    alias(libs.plugins.kotlin.android.plugin)
}

android {
    namespace = "fr.arrows.leaguepicker"
    compileSdk = 34

    defaultConfig {
        applicationId = "fr.arrows.leaguepicker"
        minSdk = 28
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
}

dependencies {
    /* Core */
    implementation(libs.androidx.core.ktx)
}
