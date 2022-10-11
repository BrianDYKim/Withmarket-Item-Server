import org.springframework.boot.gradle.tasks.bundling.BootJar

val deployVersion = "0.0.1"

val groupName = "team.bakkas"
val excludeTestModuleList = listOf("common", "domain", "grpcinterface")

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

    named<Test>("test") {
        // grpcInterface, common, domain에 대해선 테스트 목록에서 제외시킨다
        excludeTestModuleList.forEach {
            exclude("**/${it}/**")
        }
    }
}