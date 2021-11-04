package place.pic.android.plus.remote

import place.pic.android.plus.model.UserDetail
import place.pic.android.plus.remote.response.UserSearchResponse
import retrofit2.Call
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.*

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