package com.arquigrupo6.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private lateinit var questionsAdapter: QuestionsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()
        loadQuestions()
    }

    private fun initRecyclerView() {
        questionsAdapter = QuestionsAdapter(listOf())
        val recyclerView = findViewById<RecyclerView>(R.id.questionsRecyclerView)
        recyclerView.adapter = questionsAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadQuestions() {
        QuestionModel.loadQuestions { questions ->
            runOnUiThread {
                updateUI(questions.map { it.questionText })
            }
        }
    }



    private fun updateUI(questions: List<String>) {
        questionsAdapter.updateQuestions(questions)
    }
}
