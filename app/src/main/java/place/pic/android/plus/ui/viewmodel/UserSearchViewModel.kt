package place.pic.android.plus.ui.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import place.pic.android.plus.data.model.User
import place.pic.android.plus.data.remote.GithubApiServiceImpl
import place.pic.android.plus.data.remote.response.UserSearchResponse
import place.pic.android.plus.ui.Event
import place.pic.android.plus.ui.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

class UserSearchViewModel : ViewModel() {

    private val list = mutableListOf<User>()

    private val _users = MutableLiveData<List<User>>()
    val users: LiveData<List<User>>
        get() = _users

    fun addUser(user: User) {
        list.add(user)
        _users.value = list
    }

    private val _searchItemClickEvent = SingleLiveEvent<Event<String>>()
    val searchItemClickEvent: SingleLiveEvent<Event<String>>
        get() = _searchItemClickEvent

    fun setSearchItemClickEvent() { // itemId: String
        _searchItemClickEvent.call()
        // _searchItemClickEvent.value = Event(itemId)
    }

    fun requestUserSearch(query: String) {
        GithubApiServiceImpl.service.requestUserSearch(
            param = query
        ).enqueue(object : Callback<UserSearchResponse> {
            override fun onResponse(
                call: Call<UserSearchResponse>,
                response: Response<UserSearchResponse>
            ) {
                if (response.isSuccessful) {
                    Log.d("server check", "통신 성공")
                    list.clear()
                    for (i in response.body()!!.items.indices) {
                        addUser(
                            User(
                                imageUrl = response.body()!!.items[i].avatar_url,
                                name = response.body()!!.items[i].login,
                                id = response.body()!!.items[i].id
                            )
                        )
                    }
                }
            }

            override fun onFailure(call: Call<UserSearchResponse>, t: Throwable) {
                t.message?.let { Log.d("fail", it) }
            }
        })
    }
}
