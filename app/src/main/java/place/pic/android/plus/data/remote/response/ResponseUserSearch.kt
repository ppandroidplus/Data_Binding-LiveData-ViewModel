package place.pic.android.plus.data.remote.response

import java.io.Serializable

data class ResponseUserSearch(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<SearchUserData>
)

data class SearchUserData(
    var login: String,
    var avatar_url: String,
    var html_url: String
) : Serializable
