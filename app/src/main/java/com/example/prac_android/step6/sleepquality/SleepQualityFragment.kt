package com.example.prac_android.step6.sleepquality

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.prac_android.R
import com.example.prac_android.databinding.FragmentSleepQualityBinding
import com.example.prac_android.step6.database.SleepDatabase

class SleepQualityFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View { // Inflate the layout for this fragment
        val binding: FragmentSleepQualityBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_sleep_quality, container, false)
        val application = requireNotNull(this.activity).application
        val arguments = SleepQualityFragmentArgs.fromBundle(requireArguments())
        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao
        val viewModelFactory = SleepQualityViewModelFactory(arguments.sleepNightKey,dataSource)
        val sleepQualityViewModel = ViewModelProvider(
            this, viewModelFactory).get(SleepQualityViewModel::class.java)

        binding.sleepQualityViewModel = sleepQualityViewModel

        sleepQualityViewModel.navigateToSleepTracker.observe(viewLifecycleOwner) {
            if (it == true) {
                this.findNavController().navigate(
                    SleepQualityFragmentDirections.actionSleepQualityFragmentToSleepTrackerFragment()
                )
                sleepQualityViewModel.doneNavigating()
            }
        }
        return binding.root
    }
}