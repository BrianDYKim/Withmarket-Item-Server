package team.bakkas.domain.shopItem.persist

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Repository
class ShopItemCustomRepositoryImpl(
    private val jpaQUeryFactory: JPAQueryFactory
): ShopItemCustomRepository {


}