package com.robert.anmp_uts.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.robert.anmp_uts.R
import com.robert.anmp_uts.databinding.FragmentLoginBinding
import com.robert.anmp_uts.databinding.FragmentProfileBinding
import com.robert.anmp_uts.viewmodel.LoginViewModel


class LoginFragment : Fragment() {

    private lateinit var binding : FragmentLoginBinding
    private lateinit var viewModel:LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.txtRegis.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginSignup()
            Navigation.findNavController(binding.root).navigate(action)
        }

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        binding.refreshLayout.setOnRefreshListener {
            binding.refreshLayout.isRefreshing = false
        }

        binding.btnLogin.setOnClickListener {
            val username = binding.txtUsername.text.toString()
            val password = binding.txtPassword.text.toString()
            viewModel.checkLogin(username, password)
            observeViewModel()
        }



    }

    fun observeViewModel(){
        viewModel.statusLD.observe(viewLifecycleOwner, Observer {
            if (viewModel.userIDLD.value !=0){
                val sharedPrefs = requireContext().getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
                var editor: SharedPreferences.Editor = sharedPrefs.edit()

                editor.putString("id",viewModel.userIDLD.value.toString())
                editor.apply()

                val action = LoginFragmentDirections.actionLoginMain()
                Navigation.findNavController(binding.root).navigate(action)
            }
            Toast.makeText(requireContext(), viewModel.statusLD.value.toString(), Toast.LENGTH_SHORT).show()

        })

//        viewModel.userLD.observe(viewLifecycleOwner, Observer {
//            if(it != null){
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