package team.bakkas.domain.option.persist

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

/** OptionGroup의 옵션 정보들을 저장하는 entity
 * @author Doyeop Kim
 * @since 2022/10/03
 */
@Entity
class Option(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    var id: Long = 0L,
    var name: String = "",
    var price: Int = 0,
    @Column(name = "group_id")
    var groupId: Long = 0L // 외래키 속성
) {

}