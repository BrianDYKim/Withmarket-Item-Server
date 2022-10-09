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
            // tempResponseList로부터 detailResponse의 목록을 추출하는 메소드
            fun of(tempResponseList: List<DetailTempResponse>): List<DetailResponse> {
                return tempResponseList.groupBy { it.id }
                    .values
                    .map { extractDetailResponseFromTempResponse(it) }
                    .toList()
            }

            // TempResponse로부터 DetailResponse를 추출하는 메소드
            fun extractDetailResponseFromTempResponse(tempResponseList: List<DetailTempResponse>): DetailResponse {
                val group = tempResponseList.first()
                val optionList = tempResponseList
                    .map { OptionQuery.DetailResponse(it.optionId, it.optionName, it.optionPrice) }
                    .toList()

                return DetailResponse(group.id, group.name, group.selectInfo, optionList)
            }
        }
    }

    // OptionGroup에 대한 메타 데이터만을 담는 dto
    data class DetailTempResponse(
        var id: Long,
        var name: String,
        var selectInfo: GroupSelectInfo,
        var optionId: Long,
        var optionName: String,
        var optionPrice: Int
    )
}