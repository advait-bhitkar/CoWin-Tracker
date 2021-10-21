package com.silverstudio.cowintracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONException


class CowinDetailsFragment : Fragment() {

    private lateinit var backButton: ImageView
    private lateinit var cowinDetailsAdapter: CowinDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    internal lateinit var list: ArrayList<Cowin>
    internal lateinit var list22: ArrayList<Cowin>
    private var requestQueue: RequestQueue? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cowin_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list22 = ArrayList()


        backButton = requireActivity().findViewById(R.id.back)
        backButton.visibility = View.VISIBLE

        backButton.setOnClickListener {
            findNavController().popBackStack()
        }


        recyclerView = view.findViewById(R.id.recyclerview)
        cowinDetailsAdapter = CowinDetailsAdapter(list22)
        recyclerView.adapter = cowinDetailsAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireActivity())
        recyclerView.setHasFixedSize(true)

        initComponent()

    }


    private fun initComponent(){

        val actionBar: ActionBar? = (activity as AppCompatActivity?)!!.supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true);

        val date = arguments?.getString("date")
        val pincode = arguments?.getString("pincode")



        val url = "https://cdn-api.co-vin.in/api/v2/appointment/sessions/public/calendarByPin?pincode=$pincode&date=$date"

        jsonParse(url)
    }


    fun jsonParse(url: String) {


        val date = arguments?.getString("date")

        val requestQueue = Volley.newRequestQueue(requireActivity())
        val jsonObjectRequest = JsonObjectRequest(
                Request.Method.GET, url, null,
                { response ->
                    try {

//                    Toast.makeText(requireContext(), response.toString(), Toast.LENGTH_SHORT).show()
                        list = ArrayList()

                        val jsonArray = response.getJSONArray("centers")
                        for (i in 0 until jsonArray.length()) {
                            val jsonObject = jsonArray.getJSONObject(i)
                            val centerId = jsonObject.getString("center_id").toString()
                            val centername = jsonObject.getString("name").toString()
                            val city = jsonObject.getString("block_name")
                            val age = jsonObject.getString("address")
                            val address = jsonObject.getString("center_id")
                            val minimumage =
                                    jsonObject.getJSONArray("sessions").getJSONObject(0).getString(
                                            "min_age_limit"
                                    )



                            list.add(
                                    Cowin(
                                            i,
                                            centerId.toString(),
                                            centername.toString(),
                                            address,
                                            age,
                                            city,
                                            date.toString(),
                                            minimumage, date.toString()
                                    )
                            )

                        }



                        cowinDetailsAdapter.setData(list)


                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                }) { error -> error.printStackTrace() }
        requestQueue.add(jsonObjectRequest)







    }



        override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                findNavController().popBackStack()
                return true
            }
        }
        return super.onContextItemSelected(item)
    }




    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home -> {
                findNavController().popBackStack()

                return false
            }
        }
        return false
    }
}