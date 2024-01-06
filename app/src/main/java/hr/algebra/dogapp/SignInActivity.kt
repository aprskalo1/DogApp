package hr.algebra.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.dogapp.databinding.ActivitySignInBinding
import hr.algebra.dogapp.framework.setStringPreference
import hr.algebra.dogapp.framework.startActivity

class SignInActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignInBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        setContentView(binding.root)

        binding.tvRedirectToSignUp.setOnClickListener {
            startActivity<SignUpActivity>()
        }

        binding.buttonSingIn.setOnClickListener {
            val email = binding.tfEmail.text.toString()
            val password = binding.tfPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                firebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            setStringPreference("email", email)
                            startActivity<HostActivity>()
                        } else {
                            Toast.makeText(
                                this,
                                getString(R.string.sign_in_failed),
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            } else {
                Toast.makeText(
                    this,
                    getString(R.string.empty_fields_are_not_allowed),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}