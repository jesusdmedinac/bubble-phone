import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import java.util.Properties

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.koin.android)
            implementation(project.dependencies.platform("com.google.firebase:firebase-bom:33.15.0"))
            implementation(libs.firebase.crashlytics)
            implementation(libs.firebase.analytics)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.koin.core)
            implementation(libs.koin.compose)

            implementation(libs.navigation.compose)

            implementation(libs.kotlinx.serialization)

            implementation(libs.androidx.datastore)
            implementation(libs.androidx.datastore.preferences)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

fun readVersionProperties(): Properties {
    val properties = Properties()
    val versionFile = rootProject.file("version.properties")
    if (versionFile.exists()) {
        properties.load(versionFile.inputStream())
    }
    return properties
}

fun readEnvProperties(): Properties {
    val properties = Properties()
    val envFile = rootProject.file("env.properties")
    if (envFile.exists()) {
        properties.load(envFile.inputStream())
    }
    return properties
}

android {
    namespace = "com.jesusdmedinac.bubble.phone"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.jesusdmedinac.bubble.phone"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        val versionProperties = readVersionProperties()
        versionCode = versionProperties.getProperty("VERSION_CODE").toInt()
        val major = versionProperties.getProperty("VERSION_MAJOR")
        val minor = versionProperties.getProperty("VERSION_MINOR")
        val patch = versionProperties.getProperty("VERSION_PATCH")
        val versionNameFromVersionProperties = "$major.$minor.$patch"
        versionName = versionNameFromVersionProperties
    }
    
    signingConfigs {
        create("release") {
            storeFile = rootProject.file(readEnvProperties().getProperty("KEYSTORE_PATH_RELEASE") ?: "")
            storePassword = readEnvProperties().getProperty("KEYSTORE_PASSWORD") ?: ""
            keyAlias = readEnvProperties().getProperty("KEY_ALIAS") ?: ""
            keyPassword = readEnvProperties().getProperty("KEY_PASSWORD") ?: ""
        }
        
        getByName("debug") {
            storeFile = File(readEnvProperties().getProperty("KEYSTORE_PATH_DEBUG") ?: "")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("debug")
        }
        
        getByName("release") {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

