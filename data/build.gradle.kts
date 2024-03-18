import java.util.Properties

plugins {
    alias(libs.plugins.android.library.plugin)
    alias(libs.plugins.kotlin.android.plugin)
}

android {
    namespace = "fr.arrows.leaguepicker.data"
    compileSdk = 34

    buildFeatures.buildConfig = true

    defaultConfig {
        minSdk = 28

        consumerProguardFiles("consumer-rules.pro")

        /* Api key */
        Properties().apply {
            if (rootProject.file("gradle.properties").exists()) {
                load(rootProject.file("gradle.properties").inputStream())
                getProperty("SPORTS_API_KEY")?.let {
                    buildConfigField("String", "SPORTS_API_KEY", it)
                }
            }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }

}

dependencies {
    implementation(libs.androidx.core.ktx)
}