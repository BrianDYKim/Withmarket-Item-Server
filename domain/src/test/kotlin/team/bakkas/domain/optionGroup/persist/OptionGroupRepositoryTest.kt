package team.bakkas.domain.optionGroup.persist

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.bakkas.domain.optionGroup.vo.GroupSelectInfo

@SpringBootTest
internal class OptionGroupRepositoryTest @Autowired constructor(
    private val optionGroupRepository: OptionGroupRepository
) {

    private fun generateOptionGroup(itemId: Long) = OptionGroup(
        name = "원두 종류",
        selectInfo = GroupSelectInfo(basicSelect = true, exclusiveSelect = false), // 배타 선택을 false로 하여 한 가지만 선택
        itemId = itemId
    )
}