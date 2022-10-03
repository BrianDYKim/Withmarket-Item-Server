package team.bakkas.domain.optionGroup.vo

import javax.persistence.Column
import javax.persistence.Embeddable

/** Option Group의 선택 정보를 저장하는 값 객체
 *
 */
@Embeddable
data class GroupSelectInfo(
    @Column(name = "basic_select")
    var basicSelect: Boolean = false, // 기본 선택 여부
    @Column(name = "exclusive_select")
    var exclusiveSelect: Boolean = false // 배타 선택 여부
) {

}
