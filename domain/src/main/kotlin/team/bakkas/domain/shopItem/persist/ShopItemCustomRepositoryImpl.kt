package team.bakkas.domain.shopItem.persist

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository
import team.bakkas.domain.option.dto.OptionQuery
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

        // itemId를 이용해서 optionGroup, option 묶음을 불러오는 로직 -> innerJoin 활용
        val optionGroupTempDto = query.select(
            Projections.constructor(
                OptionGroupQuery.DetailTempResponse::class.java,
                optionGroup.id,
                optionGroup.name,
                optionGroup.selectInfo,
                option.id.`as`("option_id"),
                option.name.`as`("option_name"),
                option.price
            )
        ).from(optionGroup)
            .join(option).on(optionGroup.id.eq(option.groupId))
            .where(optionGroup.itemId.eq(detailRequest.itemId))
            .fetch()

        // tempResponse에서 detailResponse의 목록을 추출해온다
        val optionGroupResponseList = OptionGroupQuery.DetailResponse.of(optionGroupTempDto)

        return ShopItemQuery.DetailResponse.of(itemTempDto!!, optionGroupResponseList)
    }
}