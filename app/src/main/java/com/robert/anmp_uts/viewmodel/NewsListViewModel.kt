package com.robert.anmp_uts.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.robert.anmp_uts.model.News
import com.robert.anmp_uts.model.NewsAuthor
import com.robert.anmp_uts.model.NewsDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.json.JSONObject
import kotlin.coroutines.CoroutineContext

class NewsListViewModel(application: Application): AndroidViewModel(application), CoroutineScope {

    //life data berupa arr data supaya adapter bisa nerima data
    val newsLD = MutableLiveData<List<News>>()
    val authorLD = MutableLiveData<List<NewsAuthor>>()
    val loadingLD = MutableLiveData<Boolean>()
    val newsLoadErrorLD = MutableLiveData<Boolean>()
    private var job = Job()



    override val coroutineContext: CoroutineContext
        //dijalankan di thread io
        get() = job + Dispatchers.IO

    fun loadData() {
        launch{
            val newsDb = NewsDB.buildDatabase(
                getApplication()
            )
            val newsDao = newsDb.newsDao()
//            newsDao.deleteAllNews()
//            newsDao.resetAutoIncrementNewsId()
            if(newsDao.newsExist(1) == 0){
                val json = """
                            [
                {
                    "id": "1",
                    "title": "New Marvel Movie Announced: 'The Eternals'",
                    "author": "1",
                    "description": "Marvel Studios has officially announced their latest project, 'The Eternals', set to release next summer. Directed by Chloe Zhao, the film promises to explore a new corner of the Marvel Cinematic Universe.",
                    "imageURL": "https://phantom-marca.unidadeditorial.es/ef865faab0a1ef31caaa6cac07db8690/crop/68x0/1311x700/resize/828/f/jpg/assets/multimedia/imagenes/2021/08/20/16294695683527.jpg",
                    "date": 1713571200,
                    "category": "Entertainment",
                    "detail": "Marvel Studios has officially announced their latest project, 'The Eternals'. The film is set to release next summer and promises to explore a new corner of the Marvel Cinematic Universe./Directed by Chloe Zhao, 'The Eternals' features a star-studded cast, including Angelina Jolie, Richard Madden, and Kumail Nanjiani. The film will delve into the lore of the Eternals, a race of immortal beings who have shaped Earth's history./Fans are eagerly anticipating this new addition to the MCU, hoping it will offer fresh perspectives and exciting new characters. 'The Eternals' is expected to be a major blockbuster and a pivotal part of Marvel's Phase 4."
                },
                {
                    "id": "2",
                    "title": "Steven Spielberg Returns with 'Indiana Jones 5'",
                    "author": "1",
                    "description": "Legendary filmmaker Steven Spielberg is set to helm the highly anticipated 'Indiana Jones 5'. Fans can expect the iconic archaeologist to embark on a new adventure filled with thrills and excitement.",
                    "imageURL": "https://www.rollingstone.com/wp-content/uploads/2022/12/plt_dtlr1_uhd_r709f_stills_221123.088814c.jpg?w=1581&h=1054&crop=1",
                    "date": 1713571200,
                    "category": "Entertainment",
                    "detail": "Legendary filmmaker Steven Spielberg is set to helm the highly anticipated 'Indiana Jones 5'. This new installment promises to bring back the excitement and adventure that the series is known for./Harrison Ford returns as the iconic archaeologist, ready to embark on another thrilling quest. The plot details remain under wraps, but fans can expect the usual mix of historical intrigue, daring stunts, and exotic locations./'Indiana Jones 5' is set to be a nostalgic trip for long-time fans while introducing the character to a new generation. With Spielberg at the helm, the movie is expected to live up to its legendary predecessors."
                },
                {
                    "id": "3",
                    "title": "Christopher Nolan's Mystery Project Revealed",
                    "author": "2",
                    "description": "Renowned director Christopher Nolan has unveiled details about his upcoming film, which promises to be a mind-bending thriller. With a star-studded cast and Nolan's trademark style, anticipation is at an all-time high.",
                    "imageURL": "https://miro.medium.com/v2/resize:fit:1400/1*yhd5QHeecAiB5QjtFfuIzg.jpeg",
                    "date": 1713571200,
                    "category": "Entertainment",
                    "detail": "Renowned director Christopher Nolan has unveiled details about his upcoming film, which promises to be a mind-bending thriller. The project has been shrouded in mystery, fueling speculation and excitement./The film features a star-studded cast, including some of Hollywood's biggest names. Known for his complex narratives and stunning visuals, Nolan is expected to deliver another cinematic masterpiece./Anticipation is at an all-time high as fans eagerly await more information. With Nolan's trademark style, this new film is poised to be one of the most talked-about releases of the year."
                },
                {
                    "id": "4",
                    "title": "Pixar Announces 'Toy Story 5'",
                    "author": "2",
                    "description": "Animation powerhouse Pixar has surprised fans with the announcement of 'Toy Story 5'. Buzz, Woody, and the gang return for another heartwarming adventure that is sure to delight audiences of all ages.",
                    "imageURL": "https://i.ytimg.com/vi/08nUTrQ1tKI/maxresdefault.jpg",
                    "date": 1713571200,
                    "category": "Entertainment",
                    "detail": "Animation powerhouse Pixar has surprised fans with the announcement of 'Toy Story 5'. The beloved characters Buzz, Woody, and the rest of the gang are set to return for another heartwarming adventure./The new movie promises to bring fresh challenges and emotional moments, continuing the legacy of the previous films. Pixar's signature animation quality and storytelling are expected to shine once again./Fans of all ages are looking forward to seeing their favorite toys back on the big screen. 'Toy Story 5' is sure to be a delightful addition to the franchise, appealing to both long-time fans and new audiences."
                },
                {
                    "id": "5",
                    "title": "New James Bond Film, 'No Time to Die', Hits Theaters",
                    "author": "3",
                    "description": "After much anticipation, the latest installment in the James Bond franchise, 'No Time to Die', is finally hitting theaters. Daniel Craig's final outing as the iconic spy promises action-packed thrills and a fitting send-off.",
                    "imageURL": "https://upload.wikimedia.org/wikipedia/id/2/2f/Poster_NTTD.jpg",
                    "date": 1713571200,
                    "category": "Entertainment",
                    "detail": "After much anticipation, the latest installment in the James Bond franchise, 'No Time to Die', is finally hitting theaters. This film marks Daniel Craig's final outing as the iconic spy./The movie promises action-packed thrills, high-stakes drama, and a fitting send-off for Craig's Bond. Fans can expect the usual blend of spectacular stunts, exotic locations, and a gripping storyline./'No Time to Die' is poised to be a major blockbuster, drawing audiences eager to see how Craig's tenure as James Bond concludes. The film is a must-watch for fans of the franchise and action movies alike."
                }
            ]



        """.trimIndent()

                val gson = Gson()
                val listType = object : TypeToken<List<News>>() {}.type
                val newsList: List<News> = gson.fromJson(json, listType)
                newsDao.insertAll(*newsList.toTypedArray())
            }

            authorLD.postValue(newsDao.selectAuthorsName())



        }


    }

    //namanya bisa fetch, load
    fun refresh(){
        loadingLD.value = true
        newsLoadErrorLD.value = false
        launch{
            val newsDb = NewsDB.buildDatabase(
                getApplication()
            )

            newsLD.postValue(newsDb.newsDao().selectAllNews())
            authorLD.postValue(newsDb.newsDao().selectAuthorsName())
            Log.d("author list", authorLD.value.toString())
            loadingLD.postValue(false)
        }

    }

}