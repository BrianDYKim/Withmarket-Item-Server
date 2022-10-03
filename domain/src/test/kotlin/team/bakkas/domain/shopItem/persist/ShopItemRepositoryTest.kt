package team.bakkas.domain.shopItem.persist

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import team.bakkas.domain.shopItem.vo.Category
import team.bakkas.domain.shopItem.vo.DetailCategory
import team.bakkas.domain.shopItem.vo.PriceInfo

@SpringBootTest
internal class ShopItemRepositoryTest @Autowired constructor(
    private val shopItemRepository: ShopItemRepository
) {
    @Test
    fun create() {
        val shopItem = generateShopItem()
        val savedItem = shopItemRepository.save(shopItem)

        with(savedItem) {
            println(id)
            println(shopId)
            println(shopName)
        }
    }

    private fun generateShopItem() = ShopItem(
        name = "봄봄 아메리카노",
        priceInfo = PriceInfo(originalPrice = 2000, salePrice = 2000),
        itemImage = null,
        isOnSale = true,
        description = "봄봄의 갓성비 메뉴, 아메리카노입니다!",
        itemQuantity = null,
        category = Category.BEVERAGE,
        detailCategory = DetailCategory.COFFEE,
        shopId = "62291630-12e8-461e-8708-442c46eceeba",
        shopName = "카페봄봄 영남대점"
    )
}