package com.android.gitrepoapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.android.gitrepoapp.R
import com.android.gitrepoapp.models.RepositoryResponseItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_repository.view.*

class RepositoryAdapter : RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    inner class RepositoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<RepositoryResponseItem>(){

        override fun areItemsTheSame(
            oldItem: RepositoryResponseItem,
            newItem: RepositoryResponseItem
        ): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(
            oldItem: RepositoryResponseItem,
            newItem: RepositoryResponseItem
        ): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        val repository = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(repository.owner.avatarUrl).into(item_repository_image)
            item_repository_name.text = repository.name
            item_repository_owner.text = repository.owner.login

            setOnClickListener {
                onItemClickListener?.let{it(repository)}
            }
        }
    }

    private var onItemClickListener: ((RepositoryResponseItem) -> Unit)? = null

    fun setOnItemClickListener(listener: (RepositoryResponseItem) -> Unit){
        onItemClickListener = listener
    }



}