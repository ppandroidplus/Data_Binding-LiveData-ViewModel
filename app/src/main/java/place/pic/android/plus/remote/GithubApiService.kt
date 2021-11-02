package place.pic.android.plus.remote

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
    @GET("users")
    fun requestUserSearch(
        @Query("q") param: String?
    ): Call<UserSearchResponse>
}