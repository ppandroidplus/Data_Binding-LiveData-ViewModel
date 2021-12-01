package place.pic.android.plus

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @BindingAdapter("setImageUrl")
    @JvmStatic
    fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
            .load(url)
            .circleCrop()
            .into(imageView)
    }

    @BindingAdapter("setButtonChange")
    @JvmStatic
    fun setButtonChange(button: ImageView, compass: Boolean) {
        when (compass) {
            true -> button.setBackgroundResource(R.drawable.ic_baseline_clear_24)
            else -> button.setBackgroundResource(R.drawable.ic_baseline_search_24)
        }
    }

    /*
    @BindingAdapter("TextChangeWatcher")
    @JvmStatic
    fun onEditTextWatcher(): TextWatcher {
        return object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
            }
        }
    }

    @BindingAdapter("bindRecyclerView")
    @JvmStatic
    fun bindRecyclerView(recyclerView: RecyclerView, searchUserData: MutableList<SearchUserData>?)
    {
        if (recyclerView.adapter != null) {
            with(recyclerView.adapter as SearchUserAdapter) {
                submitList(searchUserData)
            }
        }
    }
    */
}
