package team.bakkas.domain.shopItem.persist

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import team.bakkas.domain.common.BaseTimeEntity
import team.bakkas.domain.shopItem.vo.*
import java.time.LocalDateTime
import javax.persistence.*

/** ShopItem에 관한 entity
 * @author Brian
 * @since 2022/10/02
 */
@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "shop_item")
class ShopItem(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,
    var name: String = "",
    @Embedded var priceInfo: PriceInfo = PriceInfo(),
    var itemImage: String = "",
    var isOnSale: Boolean = false,
    var description: String = "",
    var itemQuantity: Int = 0,
    var discountedAt: LocalDateTime? = null,
    var category: Category = Category.ITEM,
    var detailCategory: DetailCategory = DetailCategory.DETAIL_ITEM
) : BaseTimeEntity() {
}