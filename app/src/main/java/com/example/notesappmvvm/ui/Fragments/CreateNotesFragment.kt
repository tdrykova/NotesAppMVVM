package com.example.notesappmvvm.ui.Fragments

import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.example.notesappmvvm.Model.Notes
import com.example.notesappmvvm.R
import com.example.notesappmvvm.ViewModel.NotesViewModel
import com.example.notesappmvvm.databinding.FragmentCreateNotesBinding
import java.lang.String.format
import java.util.*
import kotlin.time.Duration
import kotlin.time.DurationUnit
import kotlin.time.ExperimentalTime

class CreateNotesFragment : Fragment() {

    lateinit var binding: FragmentCreateNotesBinding
    var priority: String = "1"
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(layoutInflater, container, false)

        binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)

        binding.pGreen.setOnClickListener {
            priority = "1"
            binding.pGreen.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pRed.setImageResource(0)
        }

        binding.pYellow.setOnClickListener {
            priority = "2"
            binding.pYellow.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pRed.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }

        binding.pRed.setOnClickListener {
            priority = "3"
            binding.pRed.setImageResource(R.drawable.ic_baseline_done_24)
            binding.pYellow.setImageResource(0)
            binding.pGreen.setImageResource(0)
        }


        binding.btnSaveNotes.setOnClickListener {
            createNotes(it)
        }
        OnClickTime()
        return binding.root
    }

    private fun OnClickTime() {
        val textView = binding.etSubtitle
        val timePicker = binding.editTextDate
        timePicker.setOnTimeChangedListener { _, hour, minute ->
            var hour = hour
            if (textView != null) {
                val hour = if (hour < 10) "0" + hour else hour
                val min = if (minute < 10) "0" + minute else minute
                // display format of time
                val msg = "$hour:$min"
                textView.text = msg
                textView.visibility = ViewGroup.VISIBLE
            }
        }
    }

    @OptIn(ExperimentalTime::class)
    private fun createNotes(it: View?) {
        val title = binding.etTitle.text.toString()
        val time = binding.etSubtitle.text.toString()
        val duration = binding.etDuration.text.toString()
        val notes = binding.etNotes.text.toString()
        val d = Date()
        val notesDate: CharSequence = DateFormat.format("MMMM d, yyyy", d.time)

        val data = Notes(
            null,
            title = title,
            time = time,
            duration = duration,
            notes = notes,
            date = notesDate.toString(),
            priority
        )
        viewModel.addNotes(data)

        Toast.makeText(requireContext(), "Notes Created Successfully", Toast.LENGTH_SHORT).show()

        Navigation.findNavController(it!!).navigate(R.id.action_createNotesFragment_to_homeFragment)
    }

}