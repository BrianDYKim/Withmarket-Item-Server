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
        // given
        val shopItem = generateShopItem()

        // when
        val result = shopItemRepository.save(shopItem)
    }

    @Test
    @DisplayName("[findWithDetailRequest] 세부적으로 찾아오는 테스트")
    fun findWithDetailRequest() {
        // given
        val itemId = 1L
        val shopId = "62291630-12e8-461e-8708-442c46eceeba"
        val shopName = "카페봄봄 영남대점"

        val detailRequest = ShopItemQuery.DetailRequest(itemId, shopId, shopName)

        // when
        val result = shopItemRepository.findWithDetailRequest(detailRequest)

        // then
        result?.let {
            assertEquals(result.id, itemId)
            println(result)
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