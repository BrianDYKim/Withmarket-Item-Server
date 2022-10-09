package team.bakkas.domain.shopItem.dto

import team.bakkas.domain.optionGroup.dto.OptionGroupQuery
import team.bakkas.domain.shopItem.vo.Category
import team.bakkas.domain.shopItem.vo.DetailCategory
import team.bakkas.domain.shopItem.vo.PriceInfo
import javax.validation.constraints.NotEmpty

/** ShopItem에 대한 조회용 dto를 정의하는 sealed class
 * @author Doyeop Kim
 * @since 2022/10/04
 */
sealed class ShopItemQuery {

    // Shop의 메인 화면에 주어지는 request
    data class MainRequest(
        @NotEmpty
        var shopId: String,
        @NotEmpty
        var shopName: String
    )

    // Shop의 메인 화면에 주어지는 response -> 일반적으로 List로 바인딩해서 보내준다
    data class MainResponse(
        var id: Long,
        var name: String,
        var priceInfo: PriceInfo,
        var itemImage: String?,
        var isOnSale: Boolean,
        var description: String,
        var itemQuantity: Int?,
        var category: Category,
        var detailCategory: DetailCategory,
        var shopId: String
    ) : java.io.Serializable

    // ShopItem의 상세 정보를 얻어내기 위한 request
    data class DetailRequest(
        @NotEmpty
        var itemId: Long,
        @NotEmpty
        var shopId: String,
        @NotEmpty
        var shopName: String
    )

    // ShopItem의 상세 정보를 반환하는 response
    data class DetailResponse(
        var id: Long,
        var name: String,
        var salePrice: Int,
        var itemImage: String?,
        var description: String,
        var itemQuantity: Int?,
        var optionGroupList: List<OptionGroupQuery.DetailResponse>
    ) : java.io.Serializable {
        companion object {
            // detailTempResponse, optionGroupList를 기반으로 detailResponse를 생성하는 메소드
            fun of(
                detailTempResponse: DetailTempResponse, optionGroupList: List<OptionGroupQuery.DetailResponse>
            ): DetailResponse = with(detailTempResponse) {
                DetailResponse(id, name, salePrice, itemImage, description, itemQuantity, optionGroupList)
            }
        }
    }

    // ShopItem 자체의 메타데이터를 담는 dto
    data class DetailTempResponse(
        var id: Long,
        var name: String,
        var salePrice: Int,
        var itemImage: String?,
        var description: String,
        var itemQuantity: Int?
    )
}