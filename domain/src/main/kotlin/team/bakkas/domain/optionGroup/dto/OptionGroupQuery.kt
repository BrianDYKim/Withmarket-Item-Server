package team.bakkas.domain.optionGroup.dto

import team.bakkas.domain.option.dto.OptionQuery
import team.bakkas.domain.optionGroup.vo.GroupSelectInfo

/** OptionGroup에 대한 query dto를 모아둔 sealed class
 * @author Doyeop Kim
 * @since 2022/10/04
 */
sealed class OptionGroupQuery {

    // OptionGroup에 대한 response
    data class DetailResponse(
        var id: Long,
        var name: String,
        var selectInfo: GroupSelectInfo,
        var optionList: List<OptionQuery.DetailResponse>
    ) : java.io.Serializable {
        companion object {
            // detailTempResponse, optionList를 기반으로 DetailResponse를 생성하는 메소드
            fun of(
                detailTempResponse: DetailTempResponse, optionList: List<OptionQuery.DetailResponse>
            ): DetailResponse = with(detailTempResponse) {
                DetailResponse(id, name, selectInfo, optionList)
            }
        }
    }

    // OptionGroup에 대한 메타 데이터만을 담는 dto
    data class DetailTempResponse(
        var id: Long,
        var name: String,
        var selectInfo: GroupSelectInfo
    )
}