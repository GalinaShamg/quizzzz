package com.example.m8_quiz_animation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.R
import com.example.m8_quiz_animation.databinding.FragmentFirstBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.*

class FirstFragment : Fragment() {
    private val dateFormat = SimpleDateFormat("dd.MM.yyyy")
    private val calendar = Calendar.getInstance()
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListenersButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setListenersButtons() {
        binding.bReady.setOnClickListener {
            showDialog()
        }
        binding.bToBegin.setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.buttonDate.setOnClickListener {
            showDialog()
        }
    }

    private fun showDialog() {
        val dateDialog = MaterialDatePicker.Builder.datePicker()
            .setTitleText(resources.getString(R.string.date))
            .build()
        dateDialog.addOnPositiveButtonClickListener { timeInMillis ->
            calendar.timeInMillis = timeInMillis
            binding.apply {
                bReady.isEnabled = false
                bToBegin.isEnabled = true
            }
            Snackbar.make(
                binding.bReady,
                dateFormat.format(calendar.time),
                Snackbar.LENGTH_SHORT
            )
                .setBackgroundTint(resources.getColor(R.color.purple_500))
                .setTextColor(resources.getColor(R.color.white))
                .show()
        }
        dateDialog.addOnNegativeButtonClickListener {
            binding.apply {
                bReady.isEnabled = true
                bToBegin.isEnabled = false
            }
        }
        dateDialog.show(childFragmentManager, "DatePicker")
    }
}