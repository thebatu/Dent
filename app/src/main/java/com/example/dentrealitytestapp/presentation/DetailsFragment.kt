package com.example.dentrealitytestapp.presentation

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.dentrealitytestapp.R
import com.example.dentrealitytestapp.data.util.Utils
import com.example.dentrealitytestapp.data.util.Utils.FormatNumber.formatNum
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
            viewModel.setUserCountry(args.countryName)
            Toast.makeText(activity, R.string.country_set, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setSelectedCountry() {
        val countryArgs = args.countryName
        val countries = viewModel.getCountries()
        val selectedCountry = countries?.filter { it.name == countryArgs }?.get(0)

        binding.apply {
            countryCapital.text = selectedCountry?.capital
            countryName.text = selectedCountry?.name
            distanceValue.text = formatNum(selectedCountry?.distance)
        }
    }

}