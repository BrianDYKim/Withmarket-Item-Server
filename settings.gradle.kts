rootProject.name = "Withmarket-Item-Server"

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

        kotlin("kapt") version kotlinVersion

        id("com.google.protobuf") version protobufVersion // gRPC
    }
}

// include projects
include(
    "application"
)

include(
    "presentation"
)

include(
    "domain"
)

include(
    "infrastructure",
    "infrastructure:dao"
)

include(
    "independent",
    "independent:grpc-interface"
)

include(
    "commons",
    "commons:common"
)