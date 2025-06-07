package com.evoteckgeospatialconsult.features.auth.ui.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.credentials.Credential
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialException
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.evoteckgeospatialconsult.R
import com.evoteckgeospatialconsult.core.auth.AuthResult
import com.evoteckgeospatialconsult.features.auth.ui.viewmodels.AuthViewModel
import com.evoteckgeospatialconsult.databinding.FragmentSignupBinding
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignupFragment : Fragment() {
    private val LOG_TAG = "Signup Fragment"
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!
    private val viewModel: AuthViewModel by activityViewModels()
    private lateinit var credential: CredentialManager
    private lateinit var callbackManager: CallbackManager

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
        credential = CredentialManager.create(requireContext())
        callbackManager = CallbackManager.Factory.create()
        setupTouchListeners()
        setupClickListeners()
        setupObservers()
    }
    override fun onResume() {
        super.onResume()
        Log.d(LOG_TAG, "Current Fragment: SignupFragment")
    }

    private fun setupClickListeners() {
        binding.apply {
            tvLogin.setOnClickListener {
                findNavController().navigate(R.id.action_signupFragment_to_loginFragment)
            }

            btnSignup.setOnClickListener {
                val fullname = binding.etFullname.text.toString().trim()
                val email = binding.etEmail.text.toString().trim()
                val password = binding.etPassword.text.toString().trim()
                val confirmPassword = binding.etConfirmPassword.text.toString().trim()
                val agreed = binding.chkRemember.isChecked

                // Input validation
                if (fullname.isEmpty()) {
                    binding.etFullname.error = "Enter your full name"
                    return@setOnClickListener
                }
                if (email.isEmpty()) {
                    binding.etEmail.error = "Enter your email"
                    return@setOnClickListener
                }
                if (!isValidEmail(email)) {
                    binding.etEmail.error = "Please enter a valid email address"
                    return@setOnClickListener
                }
                if (password.isEmpty()) {
                    binding.etPassword.error = "Enter your password"
                    return@setOnClickListener
                }
                if (password.length < 6) {
                    binding.etPassword.error = "Password must be at least 6 characters"
                    return@setOnClickListener
                }
                if (confirmPassword.isEmpty()) {
                    binding.etConfirmPassword.error = "Confirm your password"
                    return@setOnClickListener
                }
                if (password != confirmPassword) {
                    binding.etConfirmPassword.error = "Password do not match"
                    return@setOnClickListener
                }
                if (!agreed) {
                    Toast.makeText(requireContext(), "Please agree to the terms and conditions", Toast.LENGTH_LONG).show()
                    return@setOnClickListener
                }
                viewModel.signup(email, password, fullname)
            }

            binding.tvTerms.setOnClickListener {
                /*val intent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("https://evoteckgeospatialconsult.com/terms-and-conditions/")
                }
                startActivity(intent)*/
                findNavController().navigate(R.id.action_signupFragment_to_termsWebViewFragment)
            }

            btnFacebook.setOnClickListener {
                LoginManager.getInstance().logInWithReadPermissions(this@SignupFragment, listOf("email", "public_profile"))
                LoginManager.getInstance().registerCallback(callbackManager, object :
                    FacebookCallback<LoginResult> {

                    override fun onSuccess(result: LoginResult) {
                        viewModel.loginWithFacebook(result.accessToken)
                    }

                    override fun onCancel() {
                        Toast.makeText(requireContext(), "Facebook login cancelled", Toast.LENGTH_SHORT).show()
                    }

                    override fun onError(error: FacebookException) {
                        Toast.makeText(requireContext(), "Facebook login failed: ${error.localizedMessage}", Toast.LENGTH_LONG).show()
                    }

                })
            }

            btnGoogle.setOnClickListener {
                launchGoogleCredentialManagerSignIn()
            }

            btnApple.setOnClickListener {
                MaterialAlertDialogBuilder(requireContext(), R.style.CustomAlertDialog)
                    .setTitle("Apple Login Unavailable")
                    .setMessage("Sign in with Apple is not supported at the moment. Please use Google, Facebook, or email to sign in. Thank you for your understanding.")
                    .setPositiveButton("OK", null)
                    .show()
            }
        }

    }

    private fun launchGoogleCredentialManagerSignIn() {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setServerClientId(getString(R.string.default_web_client_id))
            .setFilterByAuthorizedAccounts(false)
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val result = credential.getCredential(
                    context = requireContext(),
                    request = request
                )
                handleCredentialResult(result.credential)
            } catch (e: GetCredentialException) {
                Log.e(LOG_TAG, "Google sign in failed: ${e.localizedMessage}")
                Toast.makeText(requireContext(), "Google sign in failed: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            } catch (e: Exception) {
                Log.e(LOG_TAG, "Unexpected error: ${e.localizedMessage}")
                Toast.makeText(requireContext(), "Unexpected error: ${e.localizedMessage}", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun handleCredentialResult(credential: Credential) {
        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
            val idToken = googleIdTokenCredential.idToken
            if (!idToken.isNullOrBlank()) {
                viewModel.loginWithGoogle(idToken)
            } else {
                Toast.makeText(requireContext(), "Google sign in failed: No token", Toast.LENGTH_LONG).show()
            }
        } else {
            Toast.makeText(requireContext(), "Not a Google ID credential", Toast.LENGTH_SHORT).show()
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
        viewLifecycleOwner.lifecycleScope.launch {
            // observe login result
            viewModel.authResult.collect { result ->
                when (result) {
                    is AuthResult.Loading -> {
                        // Show loading indicator (progress bar)
                        binding.progressOverlay.progressOverlay.visibility = View.VISIBLE
                    }
                    is AuthResult.Success -> {
                        // Hide loading, navigate to course fragment
                        binding.progressOverlay.progressOverlay.visibility = View.GONE
                        findNavController().navigate(R.id.action_signupFragment_to_courseListFragment)
                    }
                    is AuthResult.Error -> {
                        // Hide loading, show error message
                        binding.progressOverlay.progressOverlay.visibility = View.GONE
                        Toast.makeText(requireContext(), "Error: ${result.error.message}", Toast.LENGTH_LONG).show()
                    }
                    is AuthResult.RequiresLink -> {
                        binding.progressOverlay.progressOverlay.visibility = View.GONE
//                        showLinkAccountDialog(result.message, result.pendingCredential)
                        viewModel.linkPendingCredential(result.pendingCredential)
                    }
                    null -> {
                        // Handle unexpected state
                        binding.progressOverlay.progressOverlay.visibility = View.GONE
                        // Toast.makeText(requireContext(), "Unexpected state", Toast.LENGTH_LONG).show()
                    }

                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}