plugins {
    alias(libs.plugins.android.library.plugin)
    alias(libs.plugins.kotlin.android.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
}

android {
    namespace = "fr.arrows.laguepicker.domain"
    compileSdk = 34

    defaultConfig {
        minSdk = 28

        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {
    /* Hilt */
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)
}
