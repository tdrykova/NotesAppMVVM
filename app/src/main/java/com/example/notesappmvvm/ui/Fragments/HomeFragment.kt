package com.example.notesappmvvm.ui.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import com.example.notesappmvvm.R
import com.example.notesappmvvm.ViewModel.NotesViewModel
import com.example.notesappmvvm.databinding.FragmentHomeBinding
import com.example.notesappmvvm.ui.Adapter.NotesAdapter

class HomeFragment : Fragment() {

    lateinit var binding: FragmentHomeBinding
    val viewModel: NotesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
           binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
        }

        binding.allNotes.setOnClickListener{

            viewModel.getNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        binding.filterHigh.setOnClickListener{

            viewModel.getHighNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        binding.filterMedium.setOnClickListener{

            viewModel.getMediumNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        binding.filterLow.setOnClickListener{

            viewModel.getLowNotes().observe(viewLifecycleOwner) { notesList ->
                binding.rvAllNotes.layoutManager = GridLayoutManager(requireContext(), 2)
                binding.rvAllNotes.adapter = NotesAdapter(requireContext(), notesList)
            }

        }

        binding.btnAddNotes.setOnClickListener{
            Navigation.findNavController(it).navigate(R.id.action_homeFragment_to_createNotesFragment)
        }

        return binding.root
    }

}