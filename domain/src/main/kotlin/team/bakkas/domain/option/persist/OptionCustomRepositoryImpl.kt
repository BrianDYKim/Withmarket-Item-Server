package team.bakkas.domain.option.persist

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Repository
class OptionCustomRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): OptionCustomRepository {

}