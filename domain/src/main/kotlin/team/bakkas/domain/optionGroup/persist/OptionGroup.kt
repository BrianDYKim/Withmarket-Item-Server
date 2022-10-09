package team.bakkas.domain.optionGroup.persist

import team.bakkas.domain.common.BaseTimeEntity
import team.bakkas.domain.optionGroup.vo.GroupSelectInfo
import javax.persistence.Column
import javax.persistence.Embedded
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Entity
@Table(name = "item_option_group")
class OptionGroup(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    var id: Long = 0L,
    @Column(name = "group_name")
    var name: String = "",
    @Embedded
    var selectInfo: GroupSelectInfo = GroupSelectInfo(),
    @Column(name = "item_id", nullable = false)
    var itemId: Long = 0L
): BaseTimeEntity() {

}