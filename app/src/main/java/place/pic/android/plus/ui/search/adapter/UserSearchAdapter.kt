package place.pic.android.plus.ui.search.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import place.pic.android.plus.data.model.User
import place.pic.android.plus.databinding.ItemUserSearchBinding

/**
 * Created By kimdahyee
 * on 11월 02일, 2020
 */

class UserSearchAdapter : RecyclerView.Adapter<UserSearchAdapter.UserViewHolder>() {

    private val data: MutableList<User> = mutableListOf()
    private lateinit var itemClickListener: ItemClickListener
    // private var itemClickListener: ((user: User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemUserSearchBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(data[position])
        holder.itemView.setOnClickListener {
            itemClickListener.onClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class UserViewHolder(
        private val binding: ItemUserSearchBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.user = user
            // binding.clickListener = createUserItemClickListener(user)
        }
    }

    interface ItemClickListener {
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setItem(users: List<User>) {
        data.clear()
        data.addAll(users)
        notifyDataSetChanged()
    }

    /*fun setItemClickListener(itemClickListener: ((user: User) -> Unit)?) {
        this.itemClickListener = itemClickListener
    }

    fun createUserItemClickListener(user: User) = View.OnClickListener {
        itemClickListener?.invoke(user)
    }*/
}
