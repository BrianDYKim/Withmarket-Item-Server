package team.bakkas.domain.optionGroup.persist

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
interface OptionGroupRepository: JpaRepository<OptionGroup, Long>, OptionGroupCustomRepository {
    // itemId를 기반으로 OptionGroup을 찾아오는 메소드
    fun findAllByItemId(itemId: Long): List<OptionGroup>
}