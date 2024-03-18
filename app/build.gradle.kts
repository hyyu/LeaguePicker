plugins {
    alias(libs.plugins.application.plugin)
    alias(libs.plugins.kotlin.android.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
    alias(libs.plugins.detekt.plugin)
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
    /* Detekt rules for compose */
    detektPlugins(libs.detekt.rules.compose)

    /* Core Ktx */
    implementation(libs.androidx.core.ktx)

    /* Hilt */
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    /* Compose */
    implementation(libs.bundles.compose)

    /* Google Material themes */
    implementation(libs.material)
}
