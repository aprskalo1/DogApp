package hr.algebra.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import hr.algebra.dogapp.databinding.ActivitySignUpBinding
import hr.algebra.dogapp.framework.startActivity

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        firebaseAuth = FirebaseAuth.getInstance()

        setContentView(binding.root)

        binding.tvRedirectToSignIn.setOnClickListener {
            startActivity<SignInActivity>()
        }

        binding.buttonSingUp.setOnClickListener {
            val email = binding.tfEmail.text.toString()
            val password = binding.tfPassword.text.toString()
            val confirmPassword = binding.tfPasswordConfirm.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    this,
                                    getString(R.string.user_created),
                                    Toast.LENGTH_SHORT
                                ).show()
                                startActivity<SignInActivity>()
                            } else {
                                Toast.makeText(
                                    this,
                                    getString(R.string.error_occurred),
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this,
                        R.string.password_not_matching,
                        Toast.LENGTH_SHORT
                    ).show()
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