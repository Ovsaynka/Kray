package com.example.kray.restaurant

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kray.R
import com.example.kray.data.Comment
import kotlinx.android.synthetic.main.feedback_card.view.*
import kotlin.collections.ArrayList

class RestaurantAdapter(private val mCommentsList: Array<Comment?>) : RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {

    //private val mCommentsList: Array<Comment> = arrayOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.feedback_card, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mCommentsList[position]!!)
    }

    fun addItem(comment: Comment) {
        mCommentsList.plus(comment)
    }

    override fun getItemCount(): Int = mCommentsList.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comment: Comment) {

            itemView.feedbackRatingBar.rating = comment.stars!!.toFloat()
            itemView.feedbackTextView.text = comment.comment.toString()
            itemView.userNameTextView.text = comment.name.toString()
        }
    }
}
