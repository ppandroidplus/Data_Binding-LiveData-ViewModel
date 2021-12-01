package place.pic.android.plus.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import place.pic.android.plus.data.remote.response.SearchUserData
import place.pic.android.plus.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setBindingUserData()
    }

    private fun setBindingUserData() {
        val user = intent.getSerializableExtra("user") as SearchUserData

        with(binding) {
            tvUser.text = user.login

            Glide.with(this@DetailUserActivity)
                .load(user.avatar_url)
                .circleCrop()
                .into(imgUser)

            btnUser.setOnClickListener {
                val webIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(user.html_url))
                startActivity(webIntent)
            }
        }
    }
}
