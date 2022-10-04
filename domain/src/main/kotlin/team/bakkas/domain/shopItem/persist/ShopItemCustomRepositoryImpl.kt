package team.bakkas.domain.shopItem.persist

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.bakkas.domain.shopItem.dto.ShopItemQuery

import team.bakkas.domain.shopItem.persist.QShopItem.*

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Repository
class ShopItemCustomRepositoryImpl(
    private val query: JPAQueryFactory
) : ShopItemCustomRepository {

    // 주어진 Shop에 대해서 메인 화면에 출력될 물품 dto list을 반환해주는 메소드
    override fun findAllWithMainRequest(mainRequest: ShopItemQuery.MainRequest): List<ShopItemQuery.MainResponse> {
        return query.select(
            Projections.constructor(
                ShopItemQuery.MainResponse::class.java,
                shopItem.id,
                shopItem.name,
                shopItem.priceInfo,
                shopItem.itemImage,
                shopItem.description,
                shopItem.itemQuantity,
                shopItem.category,
                shopItem.detailCategory,
                shopItem.shopId
            )
        ).from(shopItem)
            .where(shopItem.shopId.eq(mainRequest.shopId))
            .fetch()
    }
}