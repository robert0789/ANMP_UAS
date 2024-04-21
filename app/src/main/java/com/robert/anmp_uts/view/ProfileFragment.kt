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
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.robert.anmp_uts.R
import com.robert.anmp_uts.databinding.FragmentDetailBeritaBinding
import com.robert.anmp_uts.databinding.FragmentProfileBinding
import com.robert.anmp_uts.model.User
import com.robert.anmp_uts.viewmodel.LoginViewModel
import com.robert.anmp_uts.viewmodel.ProfileViewModel
import com.robert.anmp_uts.viewmodel.SharedViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding : FragmentProfileBinding
    private lateinit var viewModel:ProfileViewModel
    private lateinit var  sharedviewModel: SharedViewModel

    var userID  = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //initializing view model to start observe live data
        sharedviewModel = ViewModelProvider(this).get(SharedViewModel::class.java)
        viewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        val userID = getUserIDFromPreference()
        viewModel.fetch(userID)
        observeViewModel()

        binding.btnChangePassword.setOnClickListener{
            val oldPassword = binding.txtOldPassword.text.toString()
            val newPassword = binding.txtNewPassword.text.toString()
            val repeatNewPassword = binding.txtRepeatNewPassword.text.toString()

            val status = viewModel.changePassword(oldPassword, newPassword, repeatNewPassword)
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()
        }

        binding.btnChangeName.setOnClickListener{

            val firstName = binding.txtFirstName.text.toString()
            val lastName = binding.txtLastName.text.toString()

            val status = viewModel.changeName(firstName, lastName)
            Toast.makeText(requireContext(), status, Toast.LENGTH_SHORT).show()


        }

        binding.btnLogOut.setOnClickListener{
            val sharedPrefs = requireContext().getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPrefs.edit()

// Clearing the SharedPreferences
            editor.clear()
            editor.apply()

            val action = ProfileFragmentDirections.actionProfileLogin()
            Navigation.findNavController(binding.root).navigate(action)
        }
    }

    private fun getUserIDFromPreference(): Int{
        val sharedPrefs = requireContext().getSharedPreferences("com.robert.anmp_uts", Context.MODE_PRIVATE)
        val idUser = sharedPrefs.getString("id", "0")
        return idUser!!.toInt()

    }

    fun observeViewModel(){
        viewModel.userLD.observe(viewLifecycleOwner, Observer {
            binding.txtFirstName.setText(it.firstName)
            binding.txtLastName.setText(it.lastName)
            binding.txtUserNamePrefs.setText(it.username)
        })

    }
}