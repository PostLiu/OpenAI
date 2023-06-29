import java.io.FileInputStream
import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    kotlin("kapt")
//    id("kotlin-parcelize")
    id("com.google.devtools.ksp") version ("1.8.20-1.0.11")
}

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

kotlin {
    sourceSets {
        debug {
            kotlin.srcDir("build/generated/ksp/debug/kotlin")
        }
        release {
            kotlin.srcDir("build/generated/ksp/release/kotlin")
        }
    }
}

android {
    namespace = "com.postliu.openai"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.postliu.openai"
        minSdk = 24
        targetSdk = 33
        versionCode = 100
        versionName = "1.0.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        vectorDrawables {
            useSupportLibrary = true
        }

        buildConfigField("String", "IP", property("ip_address").toString())
    }



    signingConfigs {
        getByName("debug") {
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
        register("release") {
            storeFile = file(keystoreProperties.getProperty("storeFile"))
            storePassword = keystoreProperties.getProperty("storePassword")
            keyAlias = keystoreProperties.getProperty("keyAlias")
            keyPassword = keystoreProperties.getProperty("keyPassword")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("debug")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.5"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {

    val version = "1.9.42-beta"
    implementation("io.github.raamcosta.compose-destinations:animations-core:$version")
    ksp("io.github.raamcosta.compose-destinations:ksp:$version")

    val composeBom = platform("androidx.compose:compose-bom:2023.04.00")
    implementation(composeBom)
    androidTestImplementation(composeBom)
    implementation("androidx.compose.material:material")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
    implementation("androidx.compose.runtime:runtime-livedata")

    implementation("io.github.ltttttttttttt:ComposeViews:1.3.1")
    implementation("com.github.liangjingkanji:Channel:1.1.5")
    implementation("androidx.work:work-runtime-ktx:2.8.1")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("com.google.code.gson:gson:2.10")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.30.1")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.30.1")
    implementation("io.coil-kt:coil-compose:2.3.0")
    implementation("com.github.DylanCaiCoding:MMKV-KTX:1.2.14")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("androidx.paging:paging-compose:3.2.0-rc01")
    implementation("androidx.paging:paging-runtime-ktx:3.1.1")
    implementation("androidx.navigation:navigation-compose:2.6.0")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("com.google.dagger:hilt-android:2.45")
    kapt("com.google.dagger:hilt-android-compiler:2.45")
    implementation("androidx.hilt:hilt-work:1.0.0")
    kapt("androidx.hilt:hilt-compiler:1.0.0")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}