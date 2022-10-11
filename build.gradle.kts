import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.springframework.boot.gradle.tasks.bundling.BootJar

val groupName = "team.bakkas"
val excludeTestModuleList = listOf("common", "domain", "grpcinterface")

val coroutineVersion = "1.6.3"
val mockkVersion = "1.12.0"
val kotestVersion = "5.3.2"
val springCloudVersion = "2021.0.2"
val queryDslVersion = "5.0.0"

plugins {
    id("org.springframework.boot")
    id("io.spring.dependency-management")
    id("org.jetbrains.kotlin.plugin.allopen")
    id("org.jetbrains.kotlin.plugin.noarg")
    kotlin("jvm")
    kotlin("plugin.spring")
    kotlin("plugin.jpa")

    kotlin("kapt")

    id("com.google.protobuf")
}

allprojects {
    group = groupName
    version = "1.0.0"

    apply(plugin = "kotlin")

    dependencies {
        // common을 포함한 모든 모듈 대상으로 coroutine 의존을 포함해준다
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
    }

    repositories {
        mavenCentral()
        maven("https://jitpack.io")
        maven("https://plugins.gradle.org/m2/")
    }

    // DuplicatesStrategy 설정
    tasks.withType<Jar> {
        duplicatesStrategy = DuplicatesStrategy.INCLUDE
    }
}

// 공통 Dependency 적용을 제외할 모듈 리스트
val nonDependencyProjects = listOf("commons", "common", "independent", "grpc-interface")

configure(subprojects.filter { it.name !in nonDependencyProjects }) {
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    apply(plugin = "kotlin")
    apply(plugin = "kotlin-spring")
    apply(plugin = "kotlin-jpa")

    apply(plugin = "kotlin-kapt")

    apply(plugin = "org.jetbrains.kotlin.plugin.allopen")
    apply(plugin = "org.jetbrains.kotlin.plugin.noarg")

    dependencies {
        // Kotlin Standard Library
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

        // Jackson
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("com.fasterxml.jackson.module:jackson-module-afterburner")

        // Spring Implements
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-aop")
        implementation("org.springframework.boot:spring-boot-starter-validation")

        // JPA
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("mysql:mysql-connector-java")

        implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
        kapt("com.querydsl:querydsl-apt:$queryDslVersion:jpa")

        // Kotlin Coroutines
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion")
        implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:$coroutineVersion")

        // Test Implementation
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        // mockk
        testImplementation("io.mockk:mockk:$mockkVersion")
        testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion") // for kotest framework
        testImplementation("io.kotest:kotest-assertions-core:$kotestVersion") // for kotest core jvm assertions
        testImplementation("io.kotest:kotest-property:$kotestVersion") // for kotest property test
        testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion")

        // Annotation Processing Tool
        kapt("org.springframework.boot:spring-boot-configuration-processor")
    }

    // QueryDSL이 만들어주는 Qclass를 사용하기 위해 저 위치로 접근할 수 있도록 설정해주는 부분이다.
    sourceSets["main"].withConvention(KotlinSourceSet::class) {
        kotlin.srcDir("$buildDir/generated/source/kapt/main")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "11"
        }
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

tasks {
    named<Jar>("jar") {
        enabled = true
    }

    named<BootJar>("bootJar") {
        enabled = false
    }

    named<Test>("test") {
        // grpcInterface, common, domain에 대해선 테스트 목록에서 제외시킨다
        excludeTestModuleList.forEach {
            exclude("${groupName}.${it}/**")
        }
    }
}