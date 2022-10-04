package team.bakkas.domain.shopItem.dto

import team.bakkas.domain.shopItem.vo.Category
import team.bakkas.domain.shopItem.vo.DetailCategory
import team.bakkas.domain.shopItem.vo.PriceInfo

/** ShopItem에 대한 조회용 dto를 정의하는 sealed class
 * @author Doyeop Kim
 * @since 2022/10/04
 */
sealed class ShopItemQuery {

    // Shop의 메인 화면에 주어지는 request
    data class MainRequest(
        var shopId: String,
        var shopName: String
    )

    // Shop의 메인 화면에 주어지는 response -> 일반적으로 List로 바인딩해서 보내준다
    data class MainResponse(
        var id: Long,
        var name: String,
        var priceInfo: PriceInfo,
        var itemImage: String?,
        var description: String,
        var itemQuantity: Int?,
        var category: Category,
        var detailCategory: DetailCategory,
        var shopId: String
    )
}