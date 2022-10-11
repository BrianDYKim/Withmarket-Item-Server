dependencies {

}

tasks.withType<Test> {
    exclude("**/grpcinterface/**")
}