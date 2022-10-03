package team.bakkas.domain.optionGroup.persist

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Repository
class OptionGroupCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): OptionGroupCustomRepository {

}