package place.pic.android.plus.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created By kimdahyee
 * on 11월 04일, 2020
 */

class UserDetailBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("detailImageUrl")
        fun loadDetailImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}