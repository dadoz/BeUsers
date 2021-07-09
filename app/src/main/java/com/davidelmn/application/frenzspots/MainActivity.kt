package com.davidelmn.application.frenzspots

import android.app.SearchManager
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private val RC_SIGN_IN: Int = 999
    private val auth: FirebaseAuth by lazy {
        Firebase.auth
    }
    private val googleSignInClient: GoogleSignInClient by lazy {
        GoogleSignIn.getClient(
            this,
            GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(BuildConfig.REQUEST_ID_TOKEN)
                .requestEmail()
                .build()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        handleIntent(intent)

        // This callback will only be called when MyFragment is at least Started.
        onBackPressedDispatcher.addCallback(
            this,
            object : OnBackPressedCallback(true /* enabled by default */) {
                override fun handleOnBackPressed() {
                    // Handle the back button event
                    if (!Navigation.findNavController(window.decorView).popBackStack()) {
                        // Call finish() on your Activity
                        finish()
                    }
                }
            }
        )
    }

    override fun onStart() {
        super.onStart()
        auth.currentUser?.let { user ->
            Timber.e(user.toString())
        } ?: logUser()
    }

    private fun logUser() {
        startActivityForResult(googleSignInClient.signInIntent, RC_SIGN_IN)
    }

    private fun logUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                when (task.isSuccessful) {
                    true -> {
                        val user = auth.currentUser
                        Timber.e(user.toString())
                    }
                    else -> {
                        Toast.makeText(
                            baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Timber.e("signInWithCredential:success")
                    //auth.currentUser
                } else {
                    // If sign in fails, display a message to the user.
                    Timber.e("signInWithCredential:failure ${task.exception}")
                    Snackbar.make(
                        findViewById(R.id.nav_host_fragment),
                        "Authentication Failed.",
                        Snackbar.LENGTH_SHORT
                    ).show()

                }
            }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleIntent(intent)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            //use the query to search your data somehow
            Timber.e(query ?: "")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            try {
                val task = GoogleSignIn.getSignedInAccountFromIntent(data)
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)
                Timber.e("firebaseAuthWithGoogle ${account?.id}")
                account?.idToken?.let { firebaseAuthWithGoogle(it) }
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Timber.e("Google sign in failed $e")
                Snackbar.make(
                    findViewById(R.id.nav_host_fragment),
                    "Authentication Failed.",
                    Snackbar.LENGTH_SHORT
                ).show()
            }
        }
    }
}