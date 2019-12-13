import com.soywiz.korge.gradle.korge

buildscript {
    repositories {
        jcenter()
        mavenCentral()
        maven { url = uri("http://dl.bintray.com/korlibs/korlibs/") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
    }
    dependencies {
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:1.5.0d")
        classpath("org.jetbrains.dokka:dokka-gradle-plugin:0.10.0")
    }
}


apply(plugin = "korge")
apply(plugin = "org.jetbrains.dokka")

repositories {
    mavenCentral()
}

korge {
    id = "me.emig.engineEmi"
    name = "EngineEmi"
    description = ""
    jvmMainClassName = "MainKt"
    supportBox2d()
}


tasks.register<org.jetbrains.dokka.gradle.DokkaTask>("createDokkaDocs") {
    outputFormat = "html"
    outputDirectory = "dokka"
}

tasks.register<DefaultTask>("openInBrowser") {
    group = "engineemi"
    dependsOn("jsWebRun")
}

tasks.register<DefaultTask>("openLocal") {
    group = "engineemi"
    dependsOn("runJvmFirstThread")
}