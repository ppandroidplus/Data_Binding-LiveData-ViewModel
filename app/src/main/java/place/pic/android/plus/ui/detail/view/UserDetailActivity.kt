package place.pic.android.plus.ui.detail.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import place.pic.android.plus.R
import place.pic.android.plus.databinding.ActivityUserDatailBinding
import place.pic.android.plus.ui.detail.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        checkErrorAndRequest()
    }

    private fun init() {
        val binding =
            DataBindingUtil.setContentView<ActivityUserDatailBinding>(this, R.layout.activity_user_datail)
        binding.viewModel = userDetailViewModel
        binding.lifecycleOwner = this
    }

    private fun checkErrorAndRequest() {
        val username = intent.getStringExtra("username").toString()
        if (username.isEmpty()) {
            Log.d("error check", "userDetailActivity username is null")
            return
        }
        userDetailViewModel.requestUserDetail(username)
    }
}
