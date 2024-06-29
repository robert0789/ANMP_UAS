package com.robert.anmp_uts.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.robert.anmp_uts.databinding.FragmentProfileBinding
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.viewmodel.ProfileViewModel

class ProfileFragment : Fragment(), UserChangeNameClickListener, UserChangePasswordClickListener {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        binding.changenamelistener = this
        binding.changepasslistener = this

        val userID = getUserIDFromPreference()
        viewModel.fetch(userID)
        observeViewModel()

        binding.btnLogOut.setOnClickListener {
            val sharedPrefs = requireContext().getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.clear()
            editor.apply()

            val action = ProfileFragmentDirections.actionProfileLogin()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getUserIDFromPreference(): Int {
        val sharedPrefs = requireContext().getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
        return sharedPrefs.getInt("id", 0)
    }

    private fun observeViewModel() {
        viewModel.userLD.observe(viewLifecycleOwner, Observer { user ->
            if (user != null) {
                binding.user = user
            } else {
                Log.e("ProfileFragment", "User data is null")
            }
        })
    }

    override fun onUserChangeNameClick(view: View, user: User) {
        if (user != null) {
            viewModel.changeName(user.firstName, user.lastName, user.id)
            Toast.makeText(view.context, "User first name and last name changed", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(view.context, "User data is not available", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onUserChangePasswordClick(view: View, user: User) {
        val newPassword = binding.txtNewPassword.text.toString()
        val repeatNewPassword = binding.txtRepeatNewPassword.text.toString()

        if (newPassword != repeatNewPassword) {
            Toast.makeText(view.context, "Passwords do not match", Toast.LENGTH_SHORT).show()
        } else {
            if (user != null) {
                viewModel.changePassword(newPassword, user.id)
                Toast.makeText(view.context, "User password changed", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(view.context, "User data is not available", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

