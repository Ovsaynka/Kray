package com.example.kray.restaurant

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kray.R
import com.example.kray.SessionManager
import com.example.kray.data.*
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.restaurant_fragment.*
import kotlinx.android.synthetic.main.toolbar.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val RESTAURANT_KEY = "restaurant"

class RestaurantFragment : Fragment(), RestaurantView {

    lateinit var session: SessionManager

    companion object {

        fun newInstance(restaurant: Restaurant): Fragment {
            val args = Bundle()
            args.putSerializable(RESTAURANT_KEY, restaurant)

            val fragment = RestaurantFragment()
            fragment.arguments = args

            return fragment
        }
    }

    //private val mAdapter: RestaurantAdapter = RestaurantAdapter()

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val restaurant = requireArguments().getSerializable(RESTAURANT_KEY) as Restaurant

        loadData(restaurant)

        feedbackRecyclerView.layoutManager = LinearLayoutManager(context)
        feedbackRecyclerView.adapter = RestaurantAdapter(restaurant.comments)



            if(session.isLoggedIn()) {
                sendCommentButton.setOnClickListener {
                    if (commentField.text.toString().trim() != "") {
                        val rating: Int = ratingStars.numStars
                        val commentBody: CommentToSend =
                            CommentToSend(commentField.text.toString(), rating)

                        val user: Map<String, String>

                        user = session.getUserDetails()

                        val token: String =
                            "Bearer_" + (user[SessionManager.KEY_TOKEN] ?: error(""))
                        val id: Int = (user[SessionManager.KEY_USER_ID] ?: error("")).toInt()

                        sendComment(commentBody, restaurant.id!!, id, token)
                    }
                }
            } else {
                commentField.setText("Please, login to comment")
                commentField.isEnabled = false
                ratingStars.isEnabled = false
            }

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        session = SessionManager(requireContext())
        return inflater.inflate(R.layout.restaurant_fragment, container, false)
    }

    override fun loadData(restaurant: Restaurant) {
        Picasso.get().load(restaurant.image).into(restImageView)

        nameRestTextView.text = restaurant.name.toString()
        descriptionTextView.text = restaurant.description.toString()
        restAddressTextView.text = restaurant.address.toString()
    }

    /*override fun setCommentList(comment: List<Comment>) {
       // mAdapter.addItems(comment)
    }*/

    private fun sendComment(commentToSend: CommentToSend, idRestaurant: Int, idUser: Int, token: String){
        val retIn = provideRetrofit().create(RestaurantApi::class.java)

        retIn.sendComment(commentToSend, idRestaurant, idUser, token)
            .enqueue(object :
            Callback<ResponseBody>{
                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    Toast.makeText(
                        context,
                        "Fail to send comment",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(context, "Thank you for comment!", Toast.LENGTH_SHORT)
                            .show()
                    } else {
                        Toast.makeText(context, response.code().toString(), Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                })
        }
    }
