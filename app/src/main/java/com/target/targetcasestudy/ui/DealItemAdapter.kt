package com.target.targetcasestudy.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.targetcasestudy.R
import com.target.targetcasestudy.data.DealItem

class DealItemAdapter(
    private val listener: (dealId: Long) -> Unit
) : ListAdapter<DealItem, DealItemViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DealItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.deal_list_item, parent, false)
        return DealItemViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: DealItemViewHolder, position: Int) {
        val item = getItem(position)
        viewHolder.bind(item)
        viewHolder.itemView.setOnClickListener { listener.invoke(item.id) }
    }
}

class DealItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val title: TextView = itemView.findViewById(R.id.deal_list_item_title)
    private val price: TextView = itemView.findViewById(R.id.deal_list_item_price)
    private val image: ImageView = itemView.findViewById(R.id.deal_list_item_image_view)

    fun bind(item: DealItem) {
        title.text = item.title
        price.text = item.price
        Picasso.get().load(item.imageURL).into(image)
    }

}

class DiffCallback : DiffUtil.ItemCallback<DealItem>() {
    override fun areItemsTheSame(oldItem: DealItem, newItem: DealItem) =
        oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: DealItem, newItem: DealItem) =
        oldItem == newItem
}