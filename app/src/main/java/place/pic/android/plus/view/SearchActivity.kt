package place.pic.android.plus.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import place.pic.android.plus.R
import place.pic.android.plus.adapter.UserAdapter
import place.pic.android.plus.databinding.ActivitySearchBinding
import place.pic.android.plus.viewmodel.SearchViewModel

class SearchActivity : AppCompatActivity() {

    private lateinit var searchViewModel: SearchViewModel
    private val userAdapter by lazy { UserAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        init()
    }

    private fun init() {
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        searchViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SearchViewModel::class.java)
        searchViewModel.users.observe(this) {
            userAdapter.setItem(it)
        }
        binding.viewModel = searchViewModel
        binding.rcvUserSearch.adapter = userAdapter
        binding.btUserSearch.setOnClickListener { onSearchClick() }
        setContentView(binding.root)
    }

    private fun onSearchClick() {
        var input = findViewById<EditText>(R.id.et_user_search)
        Log.d("dahye input test", input.text.toString())

        if (input == null) {
            Toast.makeText(this, "검색어를 입력하세요.", Toast.LENGTH_SHORT).show()
        } else {
            input.run {
                searchViewModel.requestUserSearch(input.text.toString())
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