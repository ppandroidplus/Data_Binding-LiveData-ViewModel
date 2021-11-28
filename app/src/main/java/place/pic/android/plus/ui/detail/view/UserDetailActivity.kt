package place.pic.android.plus.ui.detail.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import place.pic.android.plus.databinding.ActivityUserDatailBinding
import place.pic.android.plus.ui.detail.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel: UserDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val binding = ActivityUserDatailBinding.inflate(layoutInflater)
        binding.viewModel = userDetailViewModel
        binding.lifecycleOwner = this
        val username = intent.getStringExtra("username").toString()
        userDetailViewModel.requestUserDetail(username)
        userDetailViewModel.userDetail.observe(this) {
            // userDetailViewModel.setUserDetail()
        }
        setContentView(binding.root)
    }
}
