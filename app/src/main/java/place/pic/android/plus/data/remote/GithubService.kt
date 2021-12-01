package place.pic.android.plus.data.remote

import place.pic.android.plus.data.remote.response.ResponseUserSearch
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubService {
    @GET("/search/users")
    suspend fun userList(
        @Query("q") param: String?
    ): ResponseUserSearch
}
