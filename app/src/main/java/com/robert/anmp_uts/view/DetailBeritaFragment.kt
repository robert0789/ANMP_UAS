package com.robert.anmp_uts.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.robert.anmp_uts.R
import com.robert.anmp_uts.databinding.FragmentDetailBeritaBinding
import com.robert.anmp_uts.model.Article
import com.robert.anmp_uts.viewmodel.NewsDetailViewModel
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.schedulers.Schedulers
import java.lang.Exception
import java.util.concurrent.TimeUnit

class DetailBeritaFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var binding :FragmentDetailBeritaBinding
    private lateinit var viewModel:NewsDetailViewModel
    var imageURL = ""
    var article_index = 0
    var article = arrayListOf<Article>()
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
        val newsID = arguments?.getInt("newsID")
        imageURL = arguments?.getString("imageURL")!!

        binding.txtTitleDetail.text = arguments?.getString("newsTitle")!!
        binding.txtAuthorDetail.text = arguments?.getString("newsAuthor")!!

        viewModel = ViewModelProvider(this).get(NewsDetailViewModel::class.java)
        viewModel.get_article(newsID!!)
        observeViewModel()



        binding.btnNext.setOnClickListener{
            if(article.size  > article_index +1){
                article_index += 1
                binding.txtContent.text = article[article_index].paragraph
                binding.btnNext.isEnabled = article.size != article_index
            }

        }

        binding.btnPrev.setOnClickListener{
            if(0 < article_index){
                article_index -= 1
                binding.txtContent.text = article[article_index].paragraph
                binding.btnPrev.isEnabled = article.size != article_index

            }

        }
    }

    fun observeViewModel(){

        viewModel.articleLD.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            article = it

            Log.d("article", article.toString())

            binding.txtContent.text =article[0].paragraph

            val picasso = Picasso.Builder(requireContext())
            picasso.listener{
                    picasso,uri, exception->
                exception.printStackTrace()
            }

            //callback untuk ngetahui image berhasil diload
            picasso.build().load(imageURL).into(binding.imageView2, object:
                Callback {
                override fun onSuccess() {
                    binding.imageView2.visibility = View.VISIBLE

                }

                override fun onError(e: Exception?) {
                    Log.e("picasso error", e.toString())
                }
            })

        })


    }




}