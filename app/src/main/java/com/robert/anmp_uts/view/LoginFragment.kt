package com.robert.anmp_uts.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.robert.anmp_uts.databinding.FragmentLoginBinding
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.viewmodel.UserDetailViewModel


class LoginFragment : Fragment(), UserLoginClickListener {

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: UserDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(UserDetailViewModel::class.java)

        binding.txtRegis.setOnClickListener {
            val action = LoginFragmentDirections.actionLoginSignup()
            Navigation.findNavController(it).navigate(action)
        }

        binding.user = User("", "", "", "", "", "") // Inisialisasi user untuk data binding
        binding.loginlistener = this // Mengatur listener untuk tombol login
        binding.lifecycleOwner = this // Penting untuk LiveData

        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()

                saveUserIDToPreference(requireContext(), user.id)

                // Pindah ke halaman lain setelah login berhasil
                val action = LoginFragmentDirections.actionLoginMain()
                Navigation.findNavController(binding.root).navigate(action)
            } else {
                Toast.makeText(context, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onUserLoginClick(v: View) {
        val username = binding.txtUsername.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()

        if (username.isNotEmpty() && password.isNotEmpty()) {
            try {
                viewModel.login(username, password)
            } catch (e: Exception) {
                Toast.makeText(context, "Login failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveUserIDToPreference(context: Context, userID: Int) {
        val sharedPrefs = context.getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putInt("id", userID)
        editor.apply()
    }


}
