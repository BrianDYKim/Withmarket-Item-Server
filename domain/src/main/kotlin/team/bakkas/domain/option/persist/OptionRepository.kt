package team.bakkas.domain.option.persist

import org.springframework.data.jpa.repository.JpaRepository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
interface OptionRepository : JpaRepository<Option, Long> {
}