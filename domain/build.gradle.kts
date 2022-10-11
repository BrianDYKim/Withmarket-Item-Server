dependencies {

}

tasks.withType<Test> {
     exclude("**/domain/**")
}