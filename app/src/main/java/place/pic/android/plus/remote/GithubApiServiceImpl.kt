package place.pic.android.plus.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

object GithubApiServiceImpl {
    private val retrofit: Retrofit
        get() = Retrofit.Builder()
            .baseUrl("https://api.github.com/search/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val service : GithubApiService = retrofit.create(GithubApiService::class.java)
}