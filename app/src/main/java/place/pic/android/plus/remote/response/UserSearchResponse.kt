package place.pic.android.plus.remote.response

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

data class UserSearchResponse (
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<Data>
) {
    data class Data (
        val login: String,
        val id: Long,
        val node_id: String,
        val avatar_url: String,
        val url: String,
        val html_url: String,
        val followers_url: String,
        val following_url: String,
        val gists_url: String,
        val starred_url: String,
        val subscriptions_url: String,
        val organizations_url: String,
        val repos_url: String,
        val events_url: String,
        val received_events_url: String,
        val type: String,
        val site_admin: Boolean,
        val score: Double
    )
}