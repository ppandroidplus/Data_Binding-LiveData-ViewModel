package place.pic.android.plus.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import place.pic.android.plus.databinding.ActivityUserDatailBinding
import place.pic.android.plus.ui.viewmodel.UserDetailViewModel

class UserDetailActivity : AppCompatActivity() {

    private val userDetailViewModel: UserDetailViewModel by viewModels()
    private var username: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        username = intent.getStringExtra("username").toString()
        init()
    }

    private fun init() {
        val binding = ActivityUserDatailBinding.inflate(layoutInflater)
        binding.viewModel = userDetailViewModel
        userDetailViewModel.requestUserDetail(username)
        setContentView(binding.root)
    }
}
