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
    @Column(name = "item_id")
    var id: Long = 0L,
    @Column(nullable = false)
    var name: String = "",
    @Embedded
    var priceInfo: PriceInfo = PriceInfo(),
    @Column(name = "item_image_url")
    var itemImage: String? = null,
    @Column(name = "is_on_sale") var isOnSale: Boolean = false,
    var description: String = "",
    @Column(name = "item_quantity")
    var itemQuantity: Int? = null,
    @Column(name = "discounted_at")
    var discountedAt: LocalDateTime? = null,
    var category: Category = Category.ITEM,
    @Column(name = "detail_category")
    var detailCategory: DetailCategory = DetailCategory.DETAIL_ITEM,
    @Column(name = "shop_id", nullable = false)
    var shopId: String = "",
    @Column(name = "shop_name", nullable = false)
    var shopName: String = ""
) : BaseTimeEntity() {
}