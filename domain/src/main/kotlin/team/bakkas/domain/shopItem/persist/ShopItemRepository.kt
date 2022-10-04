package team.bakkas.domain.shopItem.persist

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/** ShopItem을 정의하기 위한 jpa repository interface
 * @author Brian
 * @since 2022/10/02
 */
interface ShopItemRepository: JpaRepository<ShopItem, Long>, ShopItemCustomRepository {
    // shopId를 기반으로 shopItem의 모든 목록을 반환하는 메소드
    fun findAllByShopId(shopId: String): List<ShopItem>
}