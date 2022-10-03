package team.bakkas.domain.shopItem.persist

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/** ShopItem을 정의하기 위한 jpa repository interface
 * @author Brian
 * @since 2022/10/02
 */
interface ShopItemRepository: JpaRepository<ShopItem, Long>, ShopItemCustomRepository {

}