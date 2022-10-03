package team.bakkas.domain.option.persist

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class OptionRepositoryTest @Autowired constructor(
    private val optionRepository: OptionRepository
) {
    @Test
    fun test1() {

    }
}