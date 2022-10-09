import org.springframework.boot.gradle.tasks.bundling.BootJar

val deployVersion = "0.0.1"

dependencies {
    // Dependency from domain
    api(project(":domain"))
}

tasks {
    named<Jar>("jar") {
        enabled = false
    }

    named<BootJar>("bootJar") {
        archiveName = "withmarket-item-$deployVersion.jar"
    }
}