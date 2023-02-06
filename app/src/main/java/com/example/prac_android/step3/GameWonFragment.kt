package com.example.prac_android.step3

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.prac_android.R
import com.example.prac_android.R.string
import com.example.prac_android.databinding.FragmentGameWonBinding

class GameWonFragment : Fragment() {
    private lateinit var binding : FragmentGameWonBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? { // Inflate the layout for this fragment
        binding = com.example.prac_android.databinding.FragmentGameWonBinding.inflate(inflater,container,false)
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        binding.nextMatchButton.setOnClickListener { view : View ->
            view.findNavController().navigate(GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        Toast.makeText(context, "NumCorrect: ${args.numCorrect}, NumQuestions: ${args.numQuestions}",Toast.LENGTH_LONG).show()

        setHasOptionsMenu(true)
        return binding.root
    }

    //Creating our Share Intent
    private fun getShareIntent() : Intent {
        val args = GameWonFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(string.share_success_text, args.numCorrect, args.numQuestions))
        return shareIntent
    }

    //Starting an Activity with our new Intent
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    //Showing the share Menu Item Dynamically
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.winner_menu, menu)
        if(getShareIntent().resolveActivity(requireActivity().packageManager)==null){
            menu.findItem(R.id.share).isVisible = false
        }
    }

    //Sharing from the Menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}