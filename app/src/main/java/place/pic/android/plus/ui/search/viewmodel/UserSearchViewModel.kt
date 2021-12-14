package place.pic.android.plus.ui.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import place.pic.android.plus.data.model.User
import place.pic.android.plus.data.remote.GithubApiServiceImpl
import place.pic.android.plus.data.remote.response.UserSearchResponse
import place.pic.android.plus.utils.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

class UserSearchViewModel : ViewModel() {

    private val list = mutableListOf<User>()
    val searchQuery = MutableLiveData("")

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    private fun addUser(user: User) {
        list.add(user)
        _users.value = list
    }

    private val _showErrorToast = MutableLiveData<Event<Boolean>>()
    val showErrorToast: LiveData<Event<Boolean>>
        get() = _showErrorToast

    private fun sendErrorEvent() {
        _showErrorToast.value = Event(true)
    }

    private fun requestUserSearch(query: String) {
        GithubApiServiceImpl.service.requestUserSearch(
            param = query
        ).enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                if (response.isSuccessful) { // 200~300
                    Log.d("server check", "통신 성공")
                    list.clear()
                    response.body()!!.items.forEach {
                        addUser(
                            User(
                                imageUrl = it.avatar_url,
                                name = it.login,
                                id = it.id
                            )
                        )
                    }
                }

                // 4xx Client error 5xx server error
                if (response.code() in 400..499) {
                    Log.d("error code 4xx", "Client errors")
                }

                if (response.code() in 500..599) {
                    Log.d("error code 5xx", "Server errors")
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                // 통신 자체가 실패
                t.message?.let { Log.d("fail", it) }
            }
        })
    }

    fun onSearchButtonClick(input: String) {
        if (input.isEmpty()) {
            sendErrorEvent()
            return
        }
        requestUserSearch(input)
    }
}
