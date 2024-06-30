package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.robert.anmp_uts.model.News
import com.robert.anmp_uts.model.NewsAuthor
import com.robert.anmp_uts.model.NewsDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class NewsDetailViewModel(application: Application): AndroidViewModel(application), CoroutineScope {
    //life data berupa arr data supaya adapter bisa nerima data
    val newsLD = MutableLiveData<News>()
    val authorLD = MutableLiveData<NewsAuthor>()
    val newsContent = MutableLiveData<String>()
    val loadingLD = MutableLiveData<Boolean>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()
    private var job = Job()

    override val coroutineContext: CoroutineContext
        //dijalankan di thread io
        get() = job + Dispatchers.IO
    fun refresh(id: Int) {
        loadingLD.value = true // Set loading state
        launch {
            val newsDb = NewsDB.buildDatabase(
                getApplication()
            )
            val news = newsDb.newsDao().selectNews(id)
            val author = newsDb.newsDao().selectAuthorName(news.author)
            newsLD.postValue(news)
            authorLD.postValue(author)

            Log.d("NewsDetailViewModel", "News value: $news")
            if (newsLD.value != null) {
                // Update newsLD on the main thread
                Log.d("NewsDetailViewModel", "NewsLD value: ${newsLD.value}")
            } else {
                Log.e("NewsDetailViewModel", "News object is null for id: $id")
                // Handle null case here if needed
            }
            try {

            } catch (e: Exception) {
                Log.e("NewsDetailViewModel", "Error refreshing news", e)
                // Handle error case if needed
            } finally {
                loadingLD.postValue(false) // Clear loading state
            }

    }




}
//
//    fun getAuthorName(id: Int, callback: (String) -> Unit) {
//        launch {
//            val newsDb = NewsDB.buildDatabase(getApplication())
//            val authorName = newsDb.newsDao().selectAuthorName(id)
//            callback(authorName)
//        }
//    }
}