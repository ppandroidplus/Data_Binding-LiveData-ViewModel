package place.pic.android.plus.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

/**
 * Created By kimdahyee
 * on 11월 17일, 2020
 */

class BindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(imageView: ImageView, url: String) {
            Glide.with(imageView.context).load(url).into(imageView)
        }

        @JvmStatic
        @BindingAdapter("detailImageUrl")
        fun loadDetailImage(imageView: ImageView, url: String?) {
            Glide.with(imageView.context).load(url).into(imageView)
        }
    }
}
