package com.evoteckgeospatialconsult.features.auth.ui.screens

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.databinding.FragmentSignupBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private val LOG_TAG = "Signup Fragment"
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

   override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       _binding = FragmentSignupBinding.inflate(inflater, container, false)
       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentSignupBinding.bind(view)

        setupTouchListeners()
        setupSignup()
        setupObservers()
    }
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "Current Fragment: SignupFragment")
    }

    private fun setupSignup() {
        binding.tvLogin.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
        }

        binding.btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_signupFragment_to_courseListFragment)
        }
    }

    private fun setupTouchListeners() {
        // make scroll view consume touch events outside of input fields
        binding.scrollView.apply {
            isFocusableInTouchMode = true
            isClickable = true

            setOnTouchListener { view, event ->
                when (event.action) {
                    android.view.MotionEvent.ACTION_UP -> {
                        hideKeyboard()
                        activity?.currentFocus?.let {
                            if (it is EditText) it.clearFocus()
                        }
                        binding.scrollView.requestFocus()
                        view.performClick()
                    }
                }
                false
            }
        }
        binding.root.setOnClickListener {
            hideKeyboard()
        }
    }

    private fun hideKeyboard() {
        val view = activity?.currentFocus ?: view ?: return
        val imm = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun setupObservers() {
        // TODO: Setup LiveData observers
    }
}