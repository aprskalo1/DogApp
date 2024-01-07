package hr.algebra.dogapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import hr.algebra.dogapp.databinding.ActivityHostBinding
import hr.algebra.dogapp.fragment.FavouritesFragment
import hr.algebra.dogapp.fragment.HomeFragment
import hr.algebra.dogapp.fragment.PersonalFragment

class HostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHostBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
        binding = ActivityHostBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFragment()

        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(HomeFragment())
                    true
                }

                R.id.favourites -> {
                    replaceFragment(FavouritesFragment())
                    true
                }

                R.id.personal -> {
                    replaceFragment(PersonalFragment())
                    true
                }

                else -> false
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.hostFragmentContainer, fragment)
            .commit()
    }

    private fun initFragment() {
        replaceFragment(HomeFragment())
        binding.bottomNavigationView.selectedItemId = R.id.home
    }
}