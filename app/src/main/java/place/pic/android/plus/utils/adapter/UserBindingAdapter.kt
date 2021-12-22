package place.pic.android.plus.utils.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created By kimdahyee
 * on 12월 05일, 2020
 */

class UserBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("userImageUrl")
        fun loadUserImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
