package team.bakkas.domain.shopItem.persist

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.bakkas.domain.optionGroup.dto.OptionGroupQuery
import team.bakkas.domain.shopItem.dto.ShopItemQuery

import team.bakkas.domain.shopItem.persist.QShopItem.*
import team.bakkas.domain.optionGroup.persist.QOptionGroup.*
import team.bakkas.domain.option.persist.QOption.*

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
                shopItem.isOnSale,
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

    override fun findWithDetailRequest(detailRequest: ShopItemQuery.DetailRequest): ShopItemQuery.DetailResponse? {
        // request에 대응하는 item
        val itemTempDto = query.select(
            Projections.constructor(
                ShopItemQuery.DetailTempResponse::class.java,
                shopItem.id,
                shopItem.name,
                shopItem.priceInfo.salePrice,
                shopItem.itemImage,
                shopItem.description,
                shopItem.itemQuantity
            )
        ).from(shopItem)
            .where(shopItem.id.eq(detailRequest.itemId))
            .fetchOne()

        // request에 대응하는 item의 optionGroup을 가져오는 로직
        val optionGroupTempDto = query.select(
            Projections.constructor(
                OptionGroupQuery.DetailTempResponse::class.java,
                optionGroup.id,
                optionGroup.name,
                optionGroup.selectInfo
            )
        ).from(optionGroup)
            .innerJoin(shopItem).on(shopItem.id.eq(optionGroup.itemId))
            .fetch()

        // optionGroup에 대한 option들을 대응시켜서 넣어주는 로직


        TODO("Not yet implemented")
    }
}