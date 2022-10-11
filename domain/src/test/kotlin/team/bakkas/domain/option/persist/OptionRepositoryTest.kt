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
    @DisplayName("[create] 생성 테스트")
    fun create() {
        // given
        val groupId = 2L
        val option = generateOption(groupId)

        // when
        val result = optionRepository.save(option)
    }

    private fun generateOption(groupId: Long) = Option(
        name = "1샷 추가",
        price = 300,
        groupId = groupId
    )
}