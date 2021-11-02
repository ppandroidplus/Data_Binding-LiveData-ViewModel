package place.pic.android.plus.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.android.plus.databinding.ItemUserSearchBinding
import place.pic.android.plus.model.User

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

class UserAdapter : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    var data : MutableList<User> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSearchBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class UserViewHolder(
        private val binding: ItemUserSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
        }
    }

    fun setItem(users: List<User>) {
        data = users as MutableList<User>
        notifyDataSetChanged()
    }
}