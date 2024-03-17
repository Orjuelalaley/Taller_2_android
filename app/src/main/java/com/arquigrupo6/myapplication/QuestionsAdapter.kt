package com.arquigrupo6.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class QuestionsAdapter(private var questions: List<String>) :
    RecyclerView.Adapter<QuestionsAdapter.QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.question_item, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.questionText.text = questions[position]
    }

    override fun getItemCount(): Int = questions.size

    fun updateQuestions(newQuestions: List<String>) {
        questions = newQuestions
        notifyDataSetChanged()
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var questionText: TextView = itemView.findViewById(R.id.questionText)
    }
}
