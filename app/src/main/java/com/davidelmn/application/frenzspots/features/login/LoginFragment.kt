package com.davidelmn.application.frenzspots.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.davidelmn.application.frenzspots.R
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.login_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onAuthUserReady()
    }

    fun onAuthUserReady() {
        FirebaseAuth.getInstance().addAuthStateListener {
            it.currentUser?.let { user ->
                findNavController().navigate(R.id.action_LoginFragment_to_SpotListFragment)
            }
        }
    }
}