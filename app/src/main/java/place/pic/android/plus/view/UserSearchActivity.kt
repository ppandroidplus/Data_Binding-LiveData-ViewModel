package place.pic.android.plus.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import place.pic.android.plus.R
import place.pic.android.plus.adapter.UserSearchAdapter
import place.pic.android.plus.databinding.ActivitySearchBinding
import place.pic.android.plus.model.User
import place.pic.android.plus.viewmodel.UserSearchViewModel

class UserSearchActivity : AppCompatActivity() {

    private lateinit var userSearchViewModel: UserSearchViewModel
    private val userSearchAdapter by lazy { UserSearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
    }

    private fun init() {
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        userSearchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(UserSearchViewModel::class.java)
        binding.viewModel = userSearchViewModel
        userSearchViewModel.users.observe(this) { it ->
            userSearchAdapter.setItem(it)
        }
        userSearchAdapter.setItemClickListener { onUserItemClick(it) }
        binding.rcvUserSearch.adapter = userSearchAdapter
        binding.btUserSearch.setOnClickListener { onSearchClick() }
        setContentView(binding.root)
    }

    private fun onUserItemClick(user: User) {
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("username", user.name)
        startActivity(intent)
    }

    private fun onSearchClick() {
        var input = findViewById<EditText>(R.id.et_user_search)
        if (input == null) {
            Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
        } else {
            input.run {
                userSearchViewModel.requestUserSearch(input.text.toString())
                hideKeyboard()
            }
        }
    }

    private fun hideKeyboard() {
        currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0) }
    }

}