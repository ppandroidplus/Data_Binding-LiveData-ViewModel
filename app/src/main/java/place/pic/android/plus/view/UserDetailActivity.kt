package place.pic.android.plus.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import place.pic.android.plus.R
import place.pic.android.plus.databinding.ActivityUserDatailBinding
import place.pic.android.plus.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private lateinit var userDetailViewModel: UserDetailViewModel
    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_datail)
        username = intent.getStringExtra("username").toString()
        init()
    }

    private fun init() {
        val binding = ActivityUserDatailBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        userDetailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserDetailViewModel::class.java)
        binding.viewModel = userDetailViewModel
        userDetailViewModel.requestUserDetail(username)
        userDetailViewModel.userDetail.observe(this) {
            // userDetailViewModel.setUserDetail()
        }
        setContentView(binding.root)
    }
}
