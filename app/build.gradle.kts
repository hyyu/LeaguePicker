plugins {
    alias(libs.plugins.application.plugin)
    alias(libs.plugins.kotlin.android.plugin)
    alias(libs.plugins.hilt.plugin)
    alias(libs.plugins.ksp.plugin)
}

android {

    project.tasks {
        preBuild {
            dependsOn(":data:build")
            dependsOn(":ui:common:build")
            dependsOn(":ui:home:build")
            dependsOn(":navigation:build")
        }
    }

    namespace = "fr.arrows.leaguepicker"
    compileSdk = 34

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

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

    /* Modules to import */
    implementation(project(":data"))
    implementation(project(":navigation"))
    implementation(project(":ui:common"))
    implementation(project(":ui:home"))

    /* Core Ktx */
    implementation(libs.androidx.core.ktx)

    /* Hilt */
    implementation(libs.dagger.hilt)
    ksp(libs.dagger.hilt.compiler)

    /* Google Material themes */
    implementation(libs.material)

    /* Compose */
    implementation(platform(libs.compose.bom))
    implementation(libs.bundles.compose)
    debugImplementation(libs.compose.ui.tooling)

    /* Lifecycle */
    implementation(libs.lifecycle.runtime.ktx)
}
