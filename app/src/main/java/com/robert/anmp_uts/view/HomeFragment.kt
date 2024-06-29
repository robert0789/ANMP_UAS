package com.robert.anmp_uts.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.anmp_uts.R
import com.robert.anmp_uts.databinding.FragmentHomeBinding
import com.robert.anmp_uts.model.News
import com.robert.anmp_uts.viewmodel.NewsDetailViewModel
import com.robert.anmp_uts.viewmodel.NewsListViewModel


class HomeFragment : Fragment() {
    private lateinit var binding :FragmentHomeBinding
    private lateinit var viewModel : NewsListViewModel
    private val newsListAdapter = NewsAdapter(arrayListOf(), arrayListOf())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {




        binding = FragmentHomeBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModel.loadData()
        viewModel.refresh()

        binding.recView.layoutManager = LinearLayoutManager(context)
        binding.recView.adapter = newsListAdapter


        observeViewModel()

        binding.refreshLayout.setOnRefreshListener {
            viewModel.refresh()
            binding.recView.visibility = View.GONE
//            binding.txtError.visibility = View.GONE
//            binding.progressLoad.visibility = View.VISIBLE
            binding.refreshLayout.isRefreshing = false
        }



    }

    fun observeViewModel(){
        viewModel.newsLD.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateNewsList(it)
            Log.d("news data", it.toString())
        })

        viewModel.authorLD.observe(viewLifecycleOwner, Observer {
            newsListAdapter.updateAuthorList(it)
        })

//        viewModel.studentLoadErrorLD.observe(viewLifecycleOwner, Observer {
//            if(it == true){
//                binding.txtError.visibility = View.VISIBLE
//            }
//
//            else{
//                binding.txtError.visibility = View.GONE
//            }
//        })
//
//        viewModel.loadingLD.observe(viewLifecycleOwner, Observer {
//            if(it == true){
//                binding.progressLoad.visibility = View.VISIBLE
//                binding.recView.visibility = View.GONE
//
//            }
//
//            else{
//                binding.progressLoad.visibility = View.GONE
//                binding.recView.visibility = View.VISIBLE
//
//            }
//        })
    }




}