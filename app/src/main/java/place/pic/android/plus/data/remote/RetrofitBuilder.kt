package place.pic.android.plus.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val baseUrl = "https://api.github.com"

    private var retrofit = Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    var service: GithubService = retrofit.create(GithubService::class.java)
}
