package place.pic.android.plus.data.remote.response

import java.io.Serializable

data class ResponseUserSearch(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<SearchUserData>
) : Serializable

data class SearchUserData(
    val login: String,
    val avatar_url: String,
    val html_url: String
) : Serializable
