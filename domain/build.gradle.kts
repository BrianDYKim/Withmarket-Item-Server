dependencies {

}

tasks.withType<Test> {
    exclude("team.bakkas.domain/**")
}