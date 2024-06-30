package com.robert.anmp_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.robert.anmp_uts.databinding.FragmentDetailBeritaBinding
import com.robert.anmp_uts.viewmodel.NewsDetailViewModel

class DetailBeritaFragment : Fragment(), NewsContentListener {


    private lateinit var binding :FragmentDetailBeritaBinding
    private lateinit var viewModel:NewsDetailViewModel
    var content_index = 0
    var num_content = 0
    var newsContents = listOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBeritaBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val newsID = arguments?.getInt("newsID") ?: 0
        Log.d("news id", newsID.toString())

//        val newsID = arguments?.getInt("newsID")
//        imageURL = arguments?.getString("imageURL")!!
//
//        binding.txtTitleDetail.text = arguments?.getString("newsTitle")!!
//        binding.txtAuthorDetail.text = arguments?.getString("newsAuthor")!!
//
        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        binding.newscontentlistener = this//        viewModel.get_article(newsID!!)

        viewModel.refresh(newsID)
        observeViewModel()




//
//        binding.btnNext.setOnClickListener{
//            if(article.size  > article_index +1){
//                article_index += 1
//                binding.txtContent.text = article[article_index].paragraph
//                binding.btnNext.isEnabled = article.size != article_index
//            }
//
//        }
//
//        binding.btnPrev.setOnClickListener{
//            if(0 < article_index){
//                article_index -= 1
//                binding.txtContent.text = article[article_index].paragraph
//                binding.btnPrev.isEnabled = article.size != article_index
//
//            }
//
//        }
    }

    fun observeViewModel(){

        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Log.d("DetailBeritaFragment", "Observed news: $it")
                binding.news = it
                newsContents = it.detail.split('/')
                Log.d("NewsDetailFragment", "newscontent: $newsContents")
                num_content = newsContents.size
                Log.d("NewsDetailFragment", "news num content: $num_content")

                viewModel.newsContent.value = newsContents[0]
                binding.viewModel = viewModel
//                val currContent = binding.viewModel.newsContent.value
//                Log.d("NewsDetailFragment", "news current content: $currContent")

            }

            else{
                Log.e("DetailBeritaFragment", "Observed news is null")

            }

//            binding.viewModel = viewModel


        })

        viewModel.authorLD.observe(viewLifecycleOwner, Observer {
            if(it!=null){
                Log.d("NewsDetailFragment", "Observed news: $it")
                binding.author = it
//                val currContent = binding.viewModel.newsContent.value
//                Log.d("NewsDetailFragment", "news current content: $currContent")

            }

            else{
                Log.e("DetailBeritaFragment", "Observed news is null")

            }

//            binding.viewModel = viewModel


        })


    }

//    override fun onLoadAuthorNameListener(authorId: Int): String {
//        var authorName = ""
//        Log.d("NewsDetailFragment", "News Author: $authorId")
//        viewModel.getAuthorName(authorId) { name ->
//            authorName = name
//        }
//        return authorName    }


    override fun onNextNewsContentListener(v: View) {
        if(content_index + 1 < num_content ){
            content_index += 1
            viewModel.newsContent.value = newsContents[content_index]
            binding.viewModel = viewModel



        }
    }

    override fun onPreviousNewsContentListener(v: View) {
        if(content_index > 0){
            content_index -= 1
            viewModel.newsContent.value = newsContents[content_index]
            binding.viewModel = viewModel

        }
    }



}