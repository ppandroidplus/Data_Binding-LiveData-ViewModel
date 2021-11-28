package place.pic.android.plus.ui.detail.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import place.pic.android.plus.data.model.UserDetail
import place.pic.android.plus.data.remote.GithubApiServiceImpl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created By kimdahyee
 * on 11월 03일, 2020
 */

class UserDetailViewModel : ViewModel() {

    private val token = "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"

    private lateinit var user: UserDetail
    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail>
        get() = _userDetail

    fun setUserDetail() {
        _userDetail.value = user
    }

    fun requestUserDetail(login: String) {
        GithubApiServiceImpl.service.requestUserDetail(
            token = token,
            login = login
        ).enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful) {
                    user = UserDetail(
                        imageUrl = response.body()?.imageUrl,
                        name = response.body()!!.name,
                        bio = response.body()?.bio,
                        followers = response.body()!!.followers,
                        htmlUrl = response.body()!!.htmlUrl
                    )
                }
                setUserDetail()
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                t.message?.let { Log.d("fail", it) }
            }
        })
    }
}
