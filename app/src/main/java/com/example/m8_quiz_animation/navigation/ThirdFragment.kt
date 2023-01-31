package com.example.m8_quiz_animation.navigation

import android.animation.ObjectAnimator
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.R
import com.example.m8_quiz_animation.databinding.FragmentThirdBinding
import com.example.m8_quiz_animation.quiz.Question



private const val ARG_QUESTION_1 = "ARG_QUESTION_1"
private const val ARG_QUESTION_2 = "ARG_QUESTION_2"
private const val ARG_QUESTION_3 = "ARG_QUESTION_3"
private const val ARG_ANSWERS_LIST = "ARG_ANSWERS_LIST"
private lateinit var question1: String
private lateinit var question2: String
private lateinit var question3: String

class ThirdFragment : Fragment() {
    private var answer1: String = null.toString()
    private var answer2: String = null.toString()
    private var answer3: String = null.toString()
    private var answers: Question =
        Question(
            answers = listOf(answer1, answer2, answer3),
            question = String(),
            feedback = listOf()
        )

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentThirdBinding.inflate(inflater)
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

    private fun getArgument() {
        arguments?.let {
            answers = it.getParcelable(ARG_ANSWERS_LIST) ?: error("message")
            question1 = it.getString(ARG_QUESTION_1).toString()
            question2 = it.getString(ARG_QUESTION_2).toString()
            question3 = it.getString(ARG_QUESTION_3).toString()
        }
    }

    private fun setListenersButtons() {
        binding.bToRepeat.setOnClickListener {
            findNavController().navigate(R.id.action_ThirdFragment_to_SecondFragment)
        }
        binding.tvQuestionOne.text = question1
        binding.tvQuestionTwo.text = question2
        binding.tvQuestionThree.text = question3
        setAnimatedTextViewWithAlphaEffect()        // п.3 alpha and translationX
        setObjectAnimator()                         // п.4 objectAnimator

    }

    private fun setObjectAnimator() {
        ObjectAnimator.ofArgb(
            binding.tvQuestionOne,
            "textColor",
            Color.parseColor("#FF0000"),
            Color.parseColor("#00FF00"),
            Color.parseColor("#0000FF")
        ).apply {
            duration = 5000
            repeatCount = 4
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
        ObjectAnimator.ofFloat(
            binding.bToRepeat,
            View.ROTATION,
            -2F, 2F
        ).apply {
            duration = 500
            interpolator = AccelerateDecelerateInterpolator()
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            start()
        }
    }

    private fun setAnimatedTextViewWithAlphaEffect() {
        binding.tvAnswerOne.apply {
            text = answers.answers[0]
            alpha = 0F
            animate().apply {
                duration = 1500
                translationX(100F)
                alpha(1F)
            }
        }

        binding.tvAnswerTwo.apply {
            text = answers.answers[1]
            alpha = 0F
            animate().apply {
                duration = 1500
                translationX(100F)
                alpha(1F)
            }
        }

        binding.tvAnswerThree.apply {
            text = answers.answers[2]
            alpha = 0F
            animate().apply {
                duration = 1500
                translationX(100F)
                alpha(1F)
            }
        }
    }
}