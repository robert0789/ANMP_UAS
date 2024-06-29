package com.robert.anmp_uts.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.robert.anmp_uts.databinding.NewsCardBinding
import com.robert.anmp_uts.model.News
import com.robert.anmp_uts.model.NewsAuthor
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import java.lang.Exception

class NewsAdapter(val newsList: ArrayList<News>, val authorList: ArrayList<NewsAuthor>)
    : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(), NewsDetailClickListener, LoadAuthorNameListener{
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
        holder.binding.news =  newsList[position]
        holder.binding.detaillistener = this
        holder.binding.authorlistener = this
        holder.binding.txtDesc.text = newsList[position].description

//        val picasso = Picasso.Builder(holder.itemView.context)
//        picasso.listener{
//                picasso,uri, exception->
//            exception.printStackTrace()
//        }
//
//        //callback untuk ngetahui image berhasil diload
//        picasso.build().load(newsList[position].imageURL).into(holder.binding.imageView, object:
//            Callback {
//            override fun onSuccess() {
////                holder.binding.progress.visibility = View.INVISIBLE
//                holder.binding.imageView.visibility = View.VISIBLE
//
//            }
//
//            override fun onError(e: Exception?) {
//                Log.e("picasso error", e.toString())
//            }
//        })
    }

    fun updateNewsList(newNewsList: List<News>){
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()

        // Log to check if the film list is updated
        Log.d("NewsListAdapter", "News list updated: $newsList")
    }

    fun updateAuthorList(newAuthorList: List<NewsAuthor>){
        authorList.clear()
        authorList.addAll(newAuthorList)
        notifyDataSetChanged()

        Log.d("AuthorListAdapter", "Author list updated: $authorList")

    }

    override fun onNewsDetailClickListener(v: View) {
        val action = HomeFragmentDirections.actionHomeDetail(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

    override fun onLoadAuthorNameListener(authorId: Int) : String {
        Log.d("author list", authorList.toString())
        val authorName = authorList.find { it.id == authorId }?.username
        return if(authorName != null){
            authorName!!
        } else{
            "Author not found"
        }

    }
}