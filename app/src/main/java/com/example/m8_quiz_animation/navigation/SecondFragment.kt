package com.example.m8_quiz_animation.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.m8_quiz_animation.R
import com.example.m8_quiz_animation.databinding.FragmentSecondBinding
import com.example.m8_quiz_animation.quiz.Question
import com.example.m8_quiz_animation.quiz.QuizStorage



private val questionText = QuizStorage.getQuiz(QuizStorage.Locale.Ru)
private lateinit var question1: String
private lateinit var question2: String
private lateinit var question3: String

class SecondFragment : Fragment() {
    private var answer1: String = null.toString()
    private var answer2: String = null.toString()
    private var answer3: String = null.toString()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getArgument()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRadioGroups()
        setListenersButtons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getArgument() {
        arguments?.let {
            question1 = it.getString("ARG_QUESTION_1")!!
            question2 = it.getString("ARG_QUESTION_2")!!
            question3 = it.getString("ARG_QUESTION_3")!!
        }
    }

    private fun setListenersButtons() {
        binding.bToBack.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
        binding.bToSend.setOnClickListener {
            val answers = Question(answers = listOf(answer1, answer2, answer3), question = String(), feedback = listOf())
            val bundle = Bundle().apply {
                putParcelable("ARG_ANSWERS_LIST", answers)
                putString("ARG_QUESTION_1", binding.tvQuestionOne.text.toString())
                putString("ARG_QUESTION_2", binding.tvQuestionTwo.text.toString())
                putString("ARG_QUESTION_3", binding.tvQuestionThree.text.toString())
            }
            findNavController().navigate(R.id.action_SecondFragment_to_ThirdFragment, bundle)
        }
    }

    private fun initRadioGroups() {
        binding.apply {
            // блок вопрос-ответ 1
            tvQuestionOne.text = questionText.questions[0].question
            rbAnswerOneOnQOne.text = questionText.questions[0].answers[0]
            rbAnswerTwoOnQOne.text = questionText.questions[0].answers[1]
            rbAnswerThreeOnQOne.text = questionText.questions[0].answers[2]
            rbAnswerFourOnQOne.text = questionText.questions[0].answers[3]
            // блок вопрос-ответ 2
            tvQuestionTwo.text = questionText.questions[1].question
            rbAnswerOneOnQTwo.text = questionText.questions[1].answers[0]
            rbAnswerTwoOnQTwo.text = questionText.questions[1].answers[1]
            rbAnswerThreeOnQTwo.text = questionText.questions[1].answers[2]
            rbAnswerFourOnQTwo.text = questionText.questions[1].answers[3]
            // блок вопрос-ответ 3
            tvQuestionThree.text = questionText.questions[2].question
            rbAnswerOneOnQThree.text = questionText.questions[2].answers[0]
            rbAnswerTwoOnQThree.text = questionText.questions[2].answers[1]
            rbAnswerThreeOnQThree.text = questionText.questions[2].answers[2]
            rbAnswerFourOnQThree.text = questionText.questions[2].answers[3]
        }

        binding.rbAnswersOnQuestionOne.setOnCheckedChangeListener { _, button ->
            when (button) {
                binding.rbAnswerOneOnQOne.id -> answer1 = binding.rbAnswerOneOnQOne.text.toString()
                binding.rbAnswerTwoOnQOne.id -> answer1 = binding.rbAnswerTwoOnQOne.text.toString()
                binding.rbAnswerThreeOnQOne.id -> answer1 =
                    binding.rbAnswerThreeOnQOne.text.toString()
                binding.rbAnswerFourOnQOne.id -> answer1 =
                    binding.rbAnswerFourOnQOne.text.toString()
            }
        }
        binding.rbAnswersOnQuestionTwo.setOnCheckedChangeListener { _, button ->
            when (button) {
                binding.rbAnswerOneOnQTwo.id -> answer2 = binding.rbAnswerOneOnQTwo.text.toString()
                binding.rbAnswerTwoOnQTwo.id -> answer2 = binding.rbAnswerTwoOnQTwo.text.toString()
                binding.rbAnswerThreeOnQTwo.id -> answer2 =
                    binding.rbAnswerThreeOnQTwo.text.toString()
                binding.rbAnswerFourOnQTwo.id -> answer2 =
                    binding.rbAnswerFourOnQTwo.text.toString()
            }
        }
        binding.rbAnswersOnQuestionThree.setOnCheckedChangeListener { _, button ->
            when (button) {
                binding.rbAnswerOneOnQThree.id -> answer3 = binding.rbAnswerOneOnQThree.text.toString()
                binding.rbAnswerTwoOnQThree.id -> answer3 = binding.rbAnswerTwoOnQThree.text.toString()
                binding.rbAnswerThreeOnQThree.id -> answer3 =
                    binding.rbAnswerThreeOnQThree.text.toString()
                binding.rbAnswerFourOnQThree.id -> answer3 =
                    binding.rbAnswerFourOnQThree.text.toString()
            }
        }
    }
}