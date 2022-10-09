package team.bakkas.domain.option.persist

import team.bakkas.domain.common.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/** OptionGroup의 옵션 정보들을 저장하는 entity
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Entity
@Table(name = "item_option")
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    var id: Long = 0L,
    @Column(name = "option_name")
    var name: String = "",
    var price: Int = 0,
    @Column(name = "group_id", nullable = false)
    var groupId: Long = 0L // 외래키 속성
): BaseTimeEntity() {

}