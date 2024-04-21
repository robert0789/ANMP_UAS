package com.robert.anmp_uts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.robert.anmp_uts.databinding.FragmentSignUpBinding
import com.robert.anmp_uts.viewmodel.LoginViewModel
import com.robert.anmp_uts.viewmodel.SignUpViewModel


class SignUpFragment : Fragment() {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(SignUpViewModel::class.java)

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnRegister.setOnClickListener{
            val username = binding.txtUsername.text.toString()
            val firstName = binding.txtFirstNAme.text.toString()
            val lastName = binding.txtLastName.text.toString()
            val email = binding.txtEmail.text.toString()
            val password = binding.txtPassword.text.toString()
            val repeatPassword = binding.txtRePassword.text.toString()


            if(password != repeatPassword){
                Toast.makeText(requireContext(), "password and repeat password are not same", Toast.LENGTH_SHORT).show()

            }

            else{
                viewModel.signUp(username, firstName,lastName,email,password,"")
                observeViewModel()

            }
        }

    }

    fun observeViewModel(){
        viewModel.successSignUpLD.observe(viewLifecycleOwner, Observer {
            if (it == true){
                val action = SignUpFragmentDirections.actionSignupLogin()
                Navigation.findNavController(binding.root).navigate(action)

            }
            else{
                Toast.makeText(requireContext(), "there is something wrong", Toast.LENGTH_SHORT).show()
            }
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