package team.bakkas.application

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["team.bakkas"])
class ApplicationLayer

fun main(args: Array<String>) {
    runApplication<ApplicationLayer>(*args)
}
