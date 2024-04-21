package com.robert.anmp_uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.robert.anmp_uts.databinding.NewsCardBinding
import com.robert.anmp_uts.model.News
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsAdapter(val newsList: ArrayList<News>)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){
        class NewsViewHolder(var binding: NewsCardBinding)
            : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        var binding = NewsCardBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.txtTitle.text = newsList[position].title
        holder.binding.txtAuthor.text = newsList[position].author
        holder.binding.txtDesc.text = newsList[position].description

        holder.binding.btnRead.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeDetail(newsList[position].id, newsList[position].imageURL, newsList[position].author, newsList[position].title)
            Navigation.findNavController(holder.itemView).navigate(action)
        }
        val picasso = Picasso.Builder(holder.itemView.context)
        picasso.listener{
                picasso,uri, exception->
            exception.printStackTrace()
        }

        //callback untuk ngetahui image berhasil diload
        picasso.build().load(newsList[position].imageURL).into(holder.binding.imageView, object:
            Callback {
            override fun onSuccess() {
//                holder.binding.progress.visibility = View.INVISIBLE
                holder.binding.imageView.visibility = View.VISIBLE

            }

            override fun onError(e: Exception?) {
                Log.e("picasso error", e.toString())
            }
        })
    }

    fun updateNewsList(newNewsList: ArrayList<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()

        // Log to check if the film list is updated
        Log.d("NewsListAdapter", "News list updated: $newsList")
    }
}