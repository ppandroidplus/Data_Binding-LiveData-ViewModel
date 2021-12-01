package place.pic.android.plus.search

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import place.pic.android.plus.R
import place.pic.android.plus.data.remote.response.SearchUserData
import place.pic.android.plus.databinding.ActivitySearchUserBinding
import place.pic.android.plus.detail.DetailUserActivity
import place.pic.android.plus.search.adapter.SearchUserAdapter
import place.pic.android.plus.search.viewmodel.SearchUserViewModel

class SearchUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchUserBinding
    private val searchUserViewModel: SearchUserViewModel by viewModels()
    private lateinit var searchUserAdapter: SearchUserAdapter
    private val userList = mutableListOf<SearchUserData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search_user)
        searchUserAdapter = SearchUserAdapter()
        binding.searchUserActivity = searchUserViewModel
        binding.lifecycleOwner = this
        binding.rvUserSearch.adapter = searchUserAdapter

        searchUser()
        changeButton()
        deleteText()
        gotoDetail()
    }

    private fun searchUser() {
        searchUserViewModel.recyclerListData.observe(this) {
            searchUserAdapter.submitList(it)
        }

        binding.etUserSearch.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    lifecycleScope.launch {
                        searchUserViewModel.requestUserData(binding.etUserSearch.text.toString())
                    }
                    return true
                }
                return false
            }
        })
    }

    // livedata 쓰기
    private fun changeButton() {
        binding.etUserSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                binding.btnUserSearch.visibility = View.VISIBLE
                binding.btnUserSearchDelete.visibility = View.INVISIBLE
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                binding.btnUserSearch.visibility = View.INVISIBLE
                binding.btnUserSearchDelete.visibility = View.VISIBLE
            }
        })
    }

    // Btn 바인딩으로빼기
    private fun deleteText() {
        binding.btnUserSearchDelete.setOnClickListener {
            binding.etUserSearch.text.clear()
        }
    }

    // 여기 바꿔야함요 ㅎㅎ
    private fun gotoDetail() {
        searchUserAdapter.itemClick = object : SearchUserAdapter.ItemClick {
            override fun onClick(view: View, position: Int) {
                val intent = Intent(this@SearchUserActivity, DetailUserActivity::class.java)

                intent.putExtra("user", userList[position])
                startActivity(intent)
            }
        }
    }
}
