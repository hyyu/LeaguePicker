plugins {
    alias(libs.plugins.android.library.plugin)
    alias(libs.plugins.kotlin.android.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
}

android {
    namespace = "fr.arrows.leaguepicker.home"
    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

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
    /* Core Ktx */
    implementation(libs.androidx.core.ktx)

    /* Hilt */
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    /* Compose */
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)
}
