package team.bakkas.domain.shopItem.persist

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.bakkas.domain.option.dto.OptionQuery
import team.bakkas.domain.optionGroup.dto.OptionGroupQuery
import team.bakkas.domain.shopItem.dto.ShopItemQuery
import team.bakkas.domain.shopItem.vo.Category
import team.bakkas.domain.shopItem.vo.DetailCategory
import team.bakkas.domain.shopItem.vo.PriceInfo

import team.bakkas.domain.shopItem.persist.QShopItem.*
import team.bakkas.domain.optionGroup.persist.QOptionGroup.*
import team.bakkas.domain.option.persist.QOption.*

@SpringBootTest
internal class ShopItemRepositoryTest @Autowired constructor(
    private val shopItemRepository: ShopItemRepository,
    private val query: JPAQueryFactory
) {
    @Test
    @DisplayName("[create] 생성 테스트")
    fun create() {
        val shopItem = generateShopItem()
        val savedItem = shopItemRepository.save(shopItem)

        with(savedItem) {
            println(id)
            println(shopId)
            println(shopName)
        }
    }

    @Test
    @DisplayName("[findAllByShopId] 탐색 테스트")
    fun findAllByShopIdTest() {
        // given
        val shopId = "62291630-12e8-461e-8708-442c46eceeba"

        // when
        val itemList = shopItemRepository.findAllByShopId(shopId)

        // then
        itemList.forEach {
            println(it.id)
            println(it.name)
            println(it.shopName)
        }
    }

    @Test
    @DisplayName("[findAllWithMainRequest] mainRequest 기반 탐색 테스트")
    fun findAllWithMainRequest() {
        // given
        val mainRequest = ShopItemQuery.MainRequest(
            shopId = "62291630-12e8-461e-8708-442c46eceeba",
            shopName = "카페봄봄 영남대점"
        )

        // when
        val mainResponseList = shopItemRepository.findAllWithMainRequest(mainRequest)

        // then
        mainResponseList.forEach {
            assertEquals(it.shopId, mainRequest.shopId)
        }

        println(mainResponseList)
    }

    @Test
    @DisplayName("[find complex] 1. join test (optionGroup <=> option)")
    fun joinTest1() {
        // given
        val itemId = 1L

        // when
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
            .where(optionGroup.itemId.eq(itemId))
            .fetch()

        val result = optionGroupTempDto
            .groupBy { it.id }
            .values
            .map {
                val group = it.first()
                val optionList = it.map { OptionQuery.DetailResponse(it.optionId, it.optionName, it.optionPrice) }.toList()
                OptionGroupQuery.DetailResponse(group.id, group.name, group.selectInfo, optionList)
            }
            .toList()

        // then
        result.forEach { println(it) }
        optionGroupTempDto.forEach { println(it) }
    }

    @Test
    @DisplayName("[findWithDetailRequest] 1. 찾아오는데 성공하는 메소드")
    fun findWithDetailRequest1() {
        // given
        val itemId = 1L
        val shopId = "62291630-12e8-461e-8708-442c46eceeba"
        val shopName = "카페봄봄 영남대점"
        val detailRequest = ShopItemQuery.DetailRequest(itemId, shopId, shopName)

        // when
        val result = shopItemRepository.findWithDetailRequest(detailRequest)

        // then
        result?.let {
            println(it.id)
            println(it.name)
            println(it.optionGroupList)
        }
    }

    private fun generateShopItem() = ShopItem(
        name = "캬라멜 마끼야또",
        priceInfo = PriceInfo(originalPrice = 4000, salePrice = 3500),
        itemImage = null,
        isOnSale = true,
        description = "달달한 캬라멜 마끼야또",
        itemQuantity = null,
        category = Category.BEVERAGE,
        detailCategory = DetailCategory.COFFEE,
        shopId = "62291630-12e8-461e-8708-442c46eceeba",
        shopName = "카페봄봄 영남대점"
    )
}