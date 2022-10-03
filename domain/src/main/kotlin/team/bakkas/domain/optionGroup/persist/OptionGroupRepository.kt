package team.bakkas.domain.optionGroup.persist

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Repository
interface OptionGroupRepository: JpaRepository<OptionGroup, Long> {

}