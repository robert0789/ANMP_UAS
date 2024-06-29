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
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.viewmodel.LoginViewModel
import com.robert.anmp_uts.viewmodel.SignUpViewModel
import com.robert.anmp_uts.viewmodel.UserDetailViewModel


class SignUpFragment : Fragment(), UserSignUpClickListener {

    private lateinit var binding: FragmentSignUpBinding
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.user = User("","","","","","")
        viewModel =ViewModelProvider(this).get(UserDetailViewModel::class.java)
        binding.addlistener = this

    }


    override fun onUserSignUpClick(v: View) {
        val firstName = binding.txtFirstNAme.text.toString().trim()
        val lastName = binding.txtLastName.text.toString().trim()
        val username = binding.txtUsername.text.toString().trim()
        val email = binding.txtEmail.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()
        val repeatPassword = binding.txtRePassword.text.toString().trim()

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || email.isEmpty() || password.isEmpty() || repeatPassword.isEmpty()) {
            Toast.makeText(view?.context, "Please fill in all fields", Toast.LENGTH_LONG).show()
            return
        }

        if (password != repeatPassword) {
            Toast.makeText(view?.context, "Passwords do not match", Toast.LENGTH_LONG).show()
            return
        }

        // Semua validasi terpenuhi, lakukan pendaftaran user
        viewModel.addUser(binding.user!!)
        Toast.makeText(view?.context, "Data added", Toast.LENGTH_LONG).show()
        Navigation.findNavController(v).popBackStack()
    }


}