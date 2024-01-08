package hr.algebra.dogapp.fragment

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import hr.algebra.dogapp.databinding.FragmentHomeBinding
import hr.algebra.dogapp.viewmodel.BreedViewModel
import hr.algebra.dogapp.viewmodel.BreedViewModelFactory

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var breedViewModel: BreedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSpinner()
    }

    private fun initSpinner() {
        val breedViewModelFactory = BreedViewModelFactory()
        breedViewModel =
            ViewModelProvider(this, breedViewModelFactory).get(BreedViewModel::class.java)

        breedViewModel.breedsLiveData.observe(viewLifecycleOwner) { breeds ->
            val breedsOnly = breeds.keys.toList()

            val spinner: Spinner = binding.spinnerBreed

            val adapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, breedsOnly)
            adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        breedViewModel.fetchDogBreeds()
    }
}