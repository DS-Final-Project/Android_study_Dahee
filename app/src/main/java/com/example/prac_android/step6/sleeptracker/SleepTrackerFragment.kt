package com.example.prac_android.step6.sleeptracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.prac_android.R
import com.example.prac_android.databinding.FragmentSleepTrackerBinding
import com.example.prac_android.step6.database.SleepDatabase
import com.example.prac_android.step6.database.SleepNight
import com.google.android.material.snackbar.Snackbar

class SleepTrackerFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Get a reference to the binding object and inflate the fragment views.
        val binding: FragmentSleepTrackerBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_sleep_tracker, container, false)

        val application = requireNotNull(this.activity).application

        val dataSource = SleepDatabase.getInstance(application).sleepDatabaseDao

        val viewModelFactory = SleepTrackerViewModelFactory(dataSource, application)

        val sleepTrackerViewModel = ViewModelProvider(
            this, viewModelFactory)[SleepTrackerViewModel::class.java]

        val adapter = SleepNightAdapter(SleepNightListener { nightId ->
            //Toast.makeText(context, "$nightId",Toast.LENGTH_LONG).show()
            sleepTrackerViewModel.onSleepNightClicked(nightId)
        })
        binding.sleepList.adapter = adapter


        sleepTrackerViewModel.nights.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.addHeaderAndSubmitList(it)
            }
        })

        //그리드 레이아웃으로 변경
        val manager = GridLayoutManager(activity, 3)
        binding.sleepList.layoutManager = manager

        binding.lifecycleOwner = this

        binding.sleepTrackerViewModel = sleepTrackerViewModel

        sleepTrackerViewModel.navigateToSleepQuality.observe(viewLifecycleOwner, Observer {
            night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections
                        .actionSleepTrackerFragmentToSleepQualityFragment(night.nightId)
                )
                sleepTrackerViewModel.doneNavigating()
            }
        })

        sleepTrackerViewModel.navigateToSleepDetail.observe(viewLifecycleOwner, Observer { night ->
            night?.let {
                this.findNavController().navigate(
                    SleepTrackerFragmentDirections
                        .actionSleepTrackerFragmentToSleepDetailFragment(night))
                sleepTrackerViewModel.onSleepDetailNavigated()
            }
        })

        sleepTrackerViewModel.showSnackBarEvent.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                Snackbar.make(
                    requireActivity().findViewById(android.R.id.content),
                    getString(R.string.cleared_message),
                    Snackbar.LENGTH_SHORT
                ).show()
                sleepTrackerViewModel.doneShowingSnackbar()
            }
        })

        return binding.root
    }
}