package place.pic.android.plus.ui.search.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import place.pic.android.plus.data.model.User
import place.pic.android.plus.databinding.ActivitySearchBinding
import place.pic.android.plus.ui.detail.view.UserDetailActivity
import place.pic.android.plus.ui.search.adapter.UserSearchAdapter
import place.pic.android.plus.ui.search.viewmodel.UserSearchViewModel

class UserSearchActivity : AppCompatActivity() {

    private val userSearchViewModel: UserSearchViewModel by viewModels()
    private val userSearchAdapter by lazy { UserSearchAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    private fun init() {
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        binding.viewModel = userSearchViewModel
        binding.rcvUserSearch.adapter = userSearchAdapter
        userSearchViewModel.showErrorToast.observe(
            this,
            Observer {
                it.peekContent()?.let {
                    Toast.makeText(this, "검색어를 입력하세요!", Toast.LENGTH_SHORT).show()
                }
            }
        )
        userSearchViewModel.users.observe(this) { it ->
            hideKeyboard()
            userSearchAdapter.setItem(it)
        }
        /*userSearchViewModel.userItemClickEvent.observe(this, { it ->
            it.getContentIfNotHandled()?.let {
                userSearchAdapter.setItemClickListener { onUserClick(it) }
            }
        })*/
        userSearchAdapter.setItemClickListener { onUserClick(it) }
        setContentView(binding.root)
    }

    private fun onUserClick(user: User) {
        userSearchViewModel.onUserItemClick("userItemClick")
        val intent = Intent(this, UserDetailActivity::class.java)
        intent.putExtra("username", user.name)
        startActivity(intent)
    }

    private fun hideKeyboard() {
        currentFocus?.run {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(windowToken, 0)
        }
    }
}
