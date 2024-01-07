package hr.algebra.dogapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hr.algebra.dogapp.HostActivity
import hr.algebra.dogapp.R
import hr.algebra.dogapp.SignInActivity
import hr.algebra.dogapp.databinding.FragmentPersonalBinding
import hr.algebra.dogapp.framework.clearPreferences
import hr.algebra.dogapp.framework.getStringPreference
import hr.algebra.dogapp.framework.startActivity

class PersonalFragment : Fragment() {
    private lateinit var binding: FragmentPersonalBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()

        binding.btnLogout.setOnClickListener {
            context?.clearPreferences()
            context?.startActivity<SignInActivity>()
        }
    }

    private fun init() {
        val email = context?.getStringPreference("email")
        binding.tvWelcome.text = "Welcome, $email"
    }
}