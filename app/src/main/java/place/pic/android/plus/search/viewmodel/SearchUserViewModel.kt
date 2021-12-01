package place.pic.android.plus.search.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import place.pic.android.plus.data.remote.RetrofitBuilder
import place.pic.android.plus.data.remote.response.SearchUserData
import retrofit2.HttpException
import java.io.IOException

class SearchUserViewModel : ViewModel() {
    private val _recyclerListData = MutableLiveData<List<SearchUserData>>()
    val recyclerListData: LiveData<List<SearchUserData>>
        get() = _recyclerListData

    /*
    private val _compass = MutableLiveData<Boolean>(false)
    val compass: LiveData<Boolean> = _compass

    private val _searchText = MutableLiveData<String>()
    val searchText: LiveData<String> = _searchText

    fun compassIcon() {
        _compass.value = !_compass.value!!
    }

    fun searchTextWacher(){
    }
    */

    fun requestUserData(toString: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val userData = RetrofitBuilder.service.userList(toString)
                _recyclerListData.postValue(userData.items)
            } catch (e: HttpException) {
                Log.d("request", e.toString())
            } catch (e: IOException) {
                this.cancel()
            }
        }
        // userClick()
    }

    /*
    private fun userClick(){
       _recyclerListData.call()
    }
     */
}
