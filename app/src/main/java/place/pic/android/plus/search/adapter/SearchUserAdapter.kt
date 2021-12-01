package place.pic.android.plus.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import place.pic.android.plus.data.remote.response.SearchUserData
import place.pic.android.plus.databinding.ItemUserSearchBinding

class SearchUserAdapter : ListAdapter<SearchUserData, SearchUserAdapter.SearchUserViewHolder>(SearchUserDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchUserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSearchBinding.inflate(layoutInflater, parent, false)
        return SearchUserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SearchUserViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
        if (itemClick != null) {
            holder.itemView.setOnClickListener { v ->
                itemClick?.onClick(v, position)
            }
        }
    }

    class SearchUserViewHolder(private val binding: ItemUserSearchBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(searchUserData: SearchUserData) {
            binding.user = searchUserData
            binding.executePendingBindings()
        }
    }

    private class SearchUserDiffUtil : DiffUtil.ItemCallback<SearchUserData>() {
        override fun areItemsTheSame(oldItem: SearchUserData, newItem: SearchUserData): Boolean {
            return oldItem.login == newItem.login
        }

        override fun areContentsTheSame(oldItem: SearchUserData, newItem: SearchUserData): Boolean {
            return oldItem == newItem
        }
    }

    interface ItemClick {
        fun onClick(view: View, position: Int)
    }

    var itemClick: ItemClick? = null
}
