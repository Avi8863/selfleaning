package com.example.selflearning.fragment.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.selflearning.R
import com.example.selflearning.databinding.FragmentRegstrationBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth


class RegistrationFragment : Fragment() {

    private var _binding: FragmentRegstrationBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_regstration, container, false)
        _binding = FragmentRegstrationBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        auth = Firebase.auth
        binding.btnSSigned.setOnClickListener {
            signUpUser()
        }
        binding.tvRedirectLogin.setOnClickListener {
            requireFragmentManager().beginTransaction().replace(R.id.main, LoginFragment()).commit()
        }

    }

    private fun signUpUser() {
        val email = binding.etSEmailAddress.text.toString()
        val pass = binding.etSPassword.text.toString()
        val confirmPassword = binding.etSConfPassword.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(requireContext(), "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(requireContext(), "Password and Confirm Password do not match", Toast.LENGTH_SHORT)
                .show()
            return
        }
        // If all credential are correct
        // We call createUserWithEmailAndPassword
        // using auth object and pass the
        // email and pass in it.
        auth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful) {
                Toast.makeText(requireContext(), "Successfully Singed Up", Toast.LENGTH_SHORT).show()
                // finish()
            } else {
                Toast.makeText(requireContext(), "Singed Up Failed!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}