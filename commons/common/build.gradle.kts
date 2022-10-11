dependencies {

}

tasks.withType<Test> {
    exclude("**/common/**")
}