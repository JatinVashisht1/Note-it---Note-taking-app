package com.thetechguy.myapplication

import android.content.Intent.getIntentOld
import android.graphics.ColorFilter
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_category.*
import kotlinx.android.synthetic.main.fragment_add_note.*
import kotlinx.android.synthetic.main.fragment_home.*

class AddCategoryFragment : Fragment(R.layout.fragment_add_category){

    var savedOrNot = 0

    val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        button_add_category.setOnClickListener{
            val category = edit_text_view_add_category.text.toString().trim(' ')
            if(category == "" || category == " ")
            {

                Toast.makeText(requireContext(), "Add text to add into category", Toast.LENGTH_SHORT).show()
            }
            else{
                viewModel.insertNote(Note(0, "Untitled", " ", category))
                text_view_show_category.setText(viewModel.allNoteCategory.value.toString())
            }
        }

        viewModel.allNoteCategory.observe(viewLifecycleOwner, {
            text_view_show_category.text = it.toString()
        })



    }






}