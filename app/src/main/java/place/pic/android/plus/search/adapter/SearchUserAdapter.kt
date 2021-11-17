package place.pic.android.plus.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import place.pic.android.plus.data.remote.response.SearchUserData
import place.pic.android.plus.databinding.ItemUserSearchBinding

class SearchUserAdapter : RecyclerView.Adapter<SearchUserAdapter.SearchUserViewHolder>() {
    var datas: MutableList<SearchUserData> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSearchBinding.inflate(layoutInflater, parent, false)
        return SearchUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        holder.bind(datas[position])
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    class SearchUserViewHolder(private val binding: ItemUserSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        private val layout = binding.clItemUserSearch
        private val userName = binding.tvUserName
        private val userImage = binding.imgUser

        fun bind(searchUserData: SearchUserData) {
            userName.text = searchUserData.login
            Glide.with(itemView)
                .load(searchUserData.avatar_url)
                .circleCrop()
                .into(userImage)
            layout.isEnabled = true
        }
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    fun clearData() {
        datas.clear()
        notifyDataSetChanged()
    }

    var itemClick: ItemClick? = null
}
