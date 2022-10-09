package team.bakkas.presentation.healthCheck

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/** Server의 Health Check를 위해 존재하는 클래스
 * @author Doyeop Kim
 * @since 2022/10/09
 */
@RestController
@RequestMapping("/api/v1")
class HealthCheckController {

    @GetMapping("/health")
    fun healthCheck(): String = "healthy"
}