package team.bakkas.domain.option.dto

/** Option에 대한 query dto를 모아둔 sealed class
 * @author Doyeop Kim
 * @since 2022/10/04
 */
sealed class OptionQuery {

    // Option에 대한 response
    data class DetailResponse(
        var id: Long,
        var name: String,
        var price: Int
    ): java.io.Serializable
}