package team.bakkas.domain.shopItem.persist

import team.bakkas.domain.shopItem.dto.ShopItemQuery

/** ShopItem에 대한 queryDSL interface
 * @author Doyeop Kim
 * @since 2022/10/03
 */
interface ShopItemCustomRepository {

    // 주어진 mainRequest에 대해서 mainResponse의 list를 반환해주는 메소드
    fun findAllWithMainRequest(mainRequest: ShopItemQuery.MainRequest): List<ShopItemQuery.MainResponse>

    // 주어진 detailRequest에 대해서 shopItem의 detailResponse를 반환해주는 메소드
    fun findWithDetailRequest(detailRequest: ShopItemQuery.DetailRequest): ShopItemQuery.DetailResponse?
}