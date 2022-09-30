rootProject.name = "Withmarket-Item-Server"

rootProject.name = "liner-highlight"

pluginManagement {
    val kotlinVersion = "1.5.10"
    val springBootVersion = "2.6.6"
    val dependencyManagementVersion = "1.0.11.RELEASE"
    val protobufVersion = "0.8.15" // for gRPC

    plugins {
        id("org.springframework.boot") version springBootVersion
        id("io.spring.dependency-management") version dependencyManagementVersion
        id("org.jetbrains.kotlin.plugin.allopen") version kotlinVersion
        id("org.jetbrains.kotlin.plugin.noarg") version kotlinVersion
        kotlin("jvm") version kotlinVersion
        kotlin("plugin.spring") version kotlinVersion
        kotlin("plugin.jpa") version kotlinVersion

        id("com.google.protobuf") version protobufVersion // gRPC
    }
}

// include projects
include(
    "presentation"
)

include(
    "application"
)

include(
    "domain"
)

include(
    "infrastructure"
)

include(
    "independent"
)

include(
    "commons",
    "commons:common"
)