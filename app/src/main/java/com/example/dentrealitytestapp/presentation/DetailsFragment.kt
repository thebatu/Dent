package com.example.dentrealitytestapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.dentrealitytestapp.R
import com.example.dentrealitytestapp.databinding.FragmentDetailsBinding


class DetailsFragment : Fragment(R.layout.fragment_details) {

    private val args: DetailsFragmentArgs by navArgs()
    private val viewModel: MainActivityViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailsBinding.bind(view)

        setSelectedCountry()

        binding.submitBtn.setOnClickListener {
            val userCountry = binding.homeCountry.text.toString()
            viewModel.setUserCountry(userCountry)
        }
    }

    private fun setSelectedCountry() {
        val countryArgs = args.countryName
        val countries = viewModel.getCountries()
        val selectedCountry = countries?.filter { it.mName == countryArgs }?.get(0)

        binding.countryCapital.text = selectedCountry?.MCapital
        binding.countryName.text = selectedCountry?.mName
        binding.distanceValue.text = selectedCountry?.mDistance.toString()
    }

}