package com.arquigrupo6.myapplication

import java.net.HttpURLConnection
import java.net.URL
import javax.xml.parsers.DocumentBuilderFactory
import kotlin.concurrent.thread

class QuestionModel(val questionText: String) {
    companion object {
        fun loadQuestions(callback: (List<QuestionModel>) -> Unit) {
            thread {
                try {
                    val url = URL("http://100.103.80.102:8000/questions/all")
                    val connection = url.openConnection() as HttpURLConnection
                    connection.requestMethod = "GET"
                    connection.connect()
                    val inputStream = connection.inputStream
                    val factory = DocumentBuilderFactory.newInstance()
                    val builder = factory.newDocumentBuilder()
                    val document = builder.parse(inputStream)
                    val questionsTextList = mutableListOf<QuestionModel>()
                    val questions = document.getElementsByTagName("question_text")
                    for (i in 0 until questions.length) {
                        val questionElement = questions.item(i)
                        val questionText = questionElement.textContent
                        questionsTextList.add(QuestionModel(questionText))
                    }
                    callback(questionsTextList)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}