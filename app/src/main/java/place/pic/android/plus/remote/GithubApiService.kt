package place.pic.android.plus.remote

import place.pic.android.plus.model.UserDetail
import place.pic.android.plus.remote.response.UserSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

interface GithubApiService {
    @GET("search/users")
    fun requestUserSearch(
        @Query("q") param: String?
    ): Call<UserSearchResponse>

    @GET("users/{username}")
    fun requestUserDetail(
        @Header("accept") token: String,
        @Path("username") login: String
    ): Call<UserDetail>
}
