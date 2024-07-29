package com.example.selflearning.fragment.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.selflearning.R
import com.example.selflearning.databinding.FragmentLoginBinding
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        initViews()
        return view
    }

    private fun initViews() {
        auth = FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.tvRedirectSignUp.setOnClickListener {
            requireFragmentManager().beginTransaction().replace(R.id.main, RegistrationFragment()).commit()
        }
    }

    private fun login() {
        val email = binding.etEmailAddress.text.toString()
        val pass = binding.etPassword.text.toString()

        auth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(OnCompleteListener {
            if (it.isSuccessful) {
                val user = auth.currentUser
                Toast.makeText(requireContext(), "Successfully Logged In ${user?.email}", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Log In failed ${it.exception?.message} ", Toast.LENGTH_SHORT).show()
            }
        })
    }

}