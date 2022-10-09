package team.bakkas.presentation.shopItem

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import team.bakkas.domain.shopItem.dto.ShopItemQuery

/** ShopItem에 대한 presentation layer
 * @author Doyeop Kim
 * @since 2022/10/09
 */
@RestController
@RequestMapping("/api/v1/item")
class ShopItemController {

    // Shop의 간략한 아이템 목록을 반환해주는 메소드
    @GetMapping("/list")
    fun getItemList(
        @RequestParam("shop-id") shopId: String,
        @RequestParam("shop-name") shopName: String
    ): ResponseEntity<ShopItemQuery.MainResponse> {
        TODO("Not yet Implemented")
    }

    // Item의 상세 정보롤 반환해주는 메소드
    @GetMapping("/detail")
    fun getDetailInfo(
        @RequestParam("shop-id") shopId: String,
        @RequestParam("shop-name") shopName: String,
        @RequestParam("item-id") itemId: Long
    ): ResponseEntity<ShopItemQuery.DetailResponse> {
        TODO("Not yet Implemented")
    }
}