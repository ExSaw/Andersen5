package com.rickrip.andersen5

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener

class AFragment : Fragment(R.layout.fragment_a),
    CustomDialogFragment.PositiveButtonOnClickListener {

    private var buttonClickListener: ButtonClickListener? = null

    // указываем способ создания, что требуется
    companion object {
        fun newInstance() = AFragment()
        const val FRAGMENT_A_TAG = "FRAGMENT_A_TAG"
    }

    interface ButtonClickListener {
        fun onButtonClicked(view: View, text: String)
    }

    override fun onPositiveButtonClicked() {
        Toast.makeText(requireContext(), "Hello there!", Toast.LENGTH_SHORT).show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d("'", "A onAttach")

        if (context is ButtonClickListener) {
            buttonClickListener = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("'", "A onCreate")

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("'", "A onCreateView")
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    //для взаим-я с ui
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("'", "A onViewCreated")

        val contactOne = view.findViewById<TextView>(R.id.tv_contact_1)
        val contactTwo = view.findViewById<TextView>(R.id.tv_contact_2)
        val contactThree = view.findViewById<TextView>(R.id.tv_contact_3)

        if (savedInstanceState != null) {
            contactOne.text = savedInstanceState.getString("contactOneText")
            contactTwo.text = savedInstanceState.getString("contactTwoText")
            contactThree.text = savedInstanceState.getString("contactThreeText")
        }

        setFragmentResultListener("key_previous") { key, result ->

            val resultId = result.getInt("viewId")
            val resultText = result.getString("viewText")
            view.findViewById<TextView>(resultId).text = resultText
            // Toast.makeText(requireContext(), "$key: $resultId", Toast.LENGTH_SHORT).show()
            Toast.makeText(requireContext(), "Changes saved", Toast.LENGTH_SHORT).show()
        }

        contactOne.run {

            setOnClickListener {
                buttonClickListener?.onButtonClicked(this, this.text.toString())
            }
            setOnLongClickListener {
                CustomDialogFragment.newInstance().show(childFragmentManager, "what?")
                true
            }
        }

        contactTwo.run {
            setOnClickListener {
                buttonClickListener?.onButtonClicked(this, this.text.toString())
            }
        }

        contactThree.run {
            setOnClickListener {
                buttonClickListener?.onButtonClicked(this, this.text.toString())
            }
        }

    }

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        Log.d("'", "A onViewStateRestored")
    }

    override fun onStart() {
        super.onStart()
        Log.d("'", "A onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d("'", "A onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("'", "A onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.d("'", "A onStop")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("'", "A onDestroyView")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("'", "A onDestroy")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("'", "A onSaveInstanceState")

        outState.putString(
            "contactOneText",
            view?.findViewById<TextView>(R.id.tv_contact_1)?.text.toString()
        )
        outState.putString(
            "contactTwoText",
            view?.findViewById<TextView>(R.id.tv_contact_2)?.text.toString()
        )
        outState.putString(
            "contactThreeText",
            view?.findViewById<TextView>(R.id.tv_contact_3)?.text.toString()
        )
    }

    override fun onDetach() {
        super.onDetach()
        buttonClickListener = null
    }

}