package place.pic.android.plus.data.model

import com.google.gson.annotations.SerializedName

/**
 * Created By kimdahyee
 * on 11월 03일, 2020
 */

data class UserDetail(
    @SerializedName("avatar_url")
    val imageUrl: String?,

    @SerializedName("login")
    val name: String,

    @SerializedName("bio")
    val bio: String?,

    @SerializedName("followers")
    val followers: Int,

    @SerializedName("html_url")
    val htmlUrl: String
)
