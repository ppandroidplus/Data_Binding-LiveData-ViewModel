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

    private val _userDetail = MutableLiveData<UserDetail>()
    val userDetail: LiveData<UserDetail>
        get() = _userDetail

    fun requestUserDetail(login: String) {
        GithubApiServiceImpl.service.requestUserDetail(
            token = token,
            login = login
        ).enqueue(object : Callback<UserDetail> {
            override fun onResponse(call: Call<UserDetail>, response: Response<UserDetail>) {
                if (response.isSuccessful) {
                    _userDetail.value = response.body()
                }

                if (response.code() in 400..499) {
                    Log.d("error code 4xx", "Client errors")
                }

                if (response.code() in 500..599) {
                    Log.d("error code 5xx", "Server errors")
                }
            }

            override fun onFailure(call: Call<UserDetail>, t: Throwable) {
                t.message?.let { Log.d("fail", it) }
            }
        })
    }
}
