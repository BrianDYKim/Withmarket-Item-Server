package team.bakkas.domain.shopItem.persist

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.bakkas.domain.shopItem.dto.ShopItemQuery
import team.bakkas.domain.shopItem.vo.Category
import team.bakkas.domain.shopItem.vo.DetailCategory
import team.bakkas.domain.shopItem.vo.PriceInfo

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