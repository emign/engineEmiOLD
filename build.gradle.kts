import com.soywiz.korge.gradle.korge

buildscript {
    repositories {
        jcenter()
        mavenLocal()
        maven { url = uri("https://dl.bintray.com/korlibs/korlibs") }
        maven { url = uri("https://plugins.gradle.org/m2/") }
        mavenCentral()
    }
    dependencies {
        classpath("com.soywiz.korlibs.korge.plugins:korge-gradle-plugin:1.4.1")
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
    supportShapeOps()
    supportTriangulation()
    supportDragonbones()
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

tasks.register<DefaultTask>("openLocalJNA") {
    group = "engineemi"
    dependsOn("runJvmFirstThread")
}

tasks.register<DefaultTask>("openLocal") {
    group = "engineemi"
    dependsOn("runJvm")
}
