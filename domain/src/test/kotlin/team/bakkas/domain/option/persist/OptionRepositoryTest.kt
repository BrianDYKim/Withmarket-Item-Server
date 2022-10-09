package team.bakkas.domain.option.persist

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import team.bakkas.domain.shopItem.persist.QShopItem.*
import team.bakkas.domain.optionGroup.persist.QOptionGroup.*
import team.bakkas.domain.option.persist.QOption.*

@SpringBootTest
internal class OptionRepositoryTest @Autowired constructor(
    private val optionRepository: OptionRepository
) {
    @Test
    @DisplayName("[create] option 생성 테스트")
    fun createTest() {
        // given
        val groupId = 2L
        val option = generateOption(groupId).apply {
            this.name = "2샷 추가"
            this.price = 500
        }

        // when
        val savedOption = optionRepository.save(option)

        // then
        with(savedOption) {
            println(id)
            println(name)
            println(price)
        }
    }

    private fun generateOption(groupId: Long) = Option(
        name = "다크맛 원두",
        price = 0,
        groupId = groupId
    )
}