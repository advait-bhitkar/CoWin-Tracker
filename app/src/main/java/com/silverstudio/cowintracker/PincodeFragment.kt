package com.silverstudio.cowintracker
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import java.text.SimpleDateFormat
import java.util.*


//
class PincodeFragment : Fragment() {

    private lateinit var dateText: EditText
    private lateinit var pincodeText: EditText
    private lateinit var myCalendar: Calendar
    private lateinit var submitButton: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pincode, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)
        initComponent(view)
    }

    private fun initComponent(view: View){


        myCalendar = Calendar.getInstance()

         dateText = view.findViewById(R.id.date) as EditText
         pincodeText = view.findViewById(R.id.pincode) as EditText
        submitButton = view.findViewById(R.id.submit)

        val date =
            OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                myCalendar.set(Calendar.YEAR, year)
                myCalendar.set(Calendar.MONTH, monthOfYear)
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd/MM/yy" //In which you need put here
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                dateText.setText(sdf.format(myCalendar.getTime()))

            }


        dateText.setOnClickListener {

            DatePickerDialog(
                requireContext(), date, myCalendar[Calendar.YEAR],
                myCalendar[Calendar.MONTH],
                myCalendar[Calendar.DAY_OF_MONTH]
            ).show()

        }


        submitButton.setOnClickListener {

//            Toast.makeText(requireContext(),pincodeText.text.length.toString(), Toast.LENGTH_SHORT).show()

            if (pincodeText.text.length !=6)
            {
                Toast.makeText(requireContext(),"Please enter a valid 6 digit pin code", Toast.LENGTH_SHORT).show()
            }

            else {

                val myFormat = "dd/MM/yy" //In which you need put here
                val sdf = SimpleDateFormat(myFormat, Locale.US)
                dateText.setText(sdf.format(myCalendar.getTime()))

                val str = sdf.format(myCalendar.getTime())

                val bundle = Bundle()
                bundle.putString("pincode", pincodeText.text.toString())
                bundle.putString("date", str.toString())

                findNavController().navigate(
                    R.id.action_pincodeFragment_to_cowinDetailsFragment,
                    bundle
                )


            }
        }

    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.option_menu, menu);
        super.onCreateOptionsMenu(menu, inflater)
    }
}