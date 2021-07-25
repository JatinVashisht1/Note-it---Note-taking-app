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
import kotlinx.android.synthetic.main.fragment_add_note.*

class AddTextFragment : Fragment(R.layout.fragment_add_note){

    var savedOrNot = 0

    val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()

       val callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
           if (edit_text_view_title.text.toString() != "" || edit_text_view_text.text.toString() != "")
           {
               if(savedOrNot==0)
               {
               insertNoteDefault()
               }
               val action = AddTextFragmentDirections.actionAddTextFragmentToHomeFragment()
               findNavController().navigate(action)
           }
           else{
               val action = AddTextFragmentDirections.actionAddTextFragmentToHomeFragment()
               findNavController().navigate(action)
           }

       }

    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        fragment_add_toolbar.setNavigationIcon(R.drawable.ic_back)
        fragment_add_toolbar.navigationIcon?.colorFilter = ColorFilter()
        fragment_add_toolbar.setNavigationOnClickListener{
            val action = AddTextFragmentDirections.actionAddTextFragmentToHomeFragment()
            findNavController().navigate(action)
        }

        fragment_add_toolbar.title = "Note it"

        edit_text_view_category.setText("default")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            fragment_add_toolbar.setTitleTextColor(resources.getColor(R.color.white))
        }



        fragment_add_toolbar.inflateMenu(R.menu.fragment_menu)



        fragment_add_toolbar.setOnMenuItemClickListener{
            when (it.itemId) {
                R.id.saveNote -> {

                    insertNoteDefault()

                    true
                }


                else -> false
            }
        }

    }



    private fun insertNoteDefault()
    {
        edit_text_view_title.text.toString().trim(' ')
        edit_text_view_category.text.toString().trim(' ')
        if(edit_text_view_title.text.toString() != ""  && edit_text_view_text.text.toString() != "" )
        {
            var categ = edit_text_view_category.text.toString()
            if(edit_text_view_category.text.toString()=="" || edit_text_view_category.text.toString() == " "){
                categ = "default"
            }
            viewModel.insertNote(Note(0, edit_text_view_title.text.toString(), edit_text_view_text.text.toString(), categ))
            savedOrNot = 1
            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
        }


        else if(edit_text_view_title.text.toString() == "" && edit_text_view_text.text.toString() != "")
        {
            var categ = edit_text_view_category.text.toString()
            if(edit_text_view_category.text.toString()=="" || edit_text_view_category.text.toString() == " "){
                categ = "default"
            }
            viewModel.insertNote(Note(0, "Untitled", edit_text_view_text.text.toString(), categ))
            Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            savedOrNot = 1
        }
        else if(edit_text_view_title.text.toString() == ""  && edit_text_view_text.text.toString() == "")
            {
                Toast.makeText(context, "Enter note or title", Toast.LENGTH_SHORT).show()
                savedOrNot = 0

            }
        else{
            var categ = edit_text_view_category.text.toString()
            if(edit_text_view_category.text.toString()=="" || edit_text_view_category.text.toString() == " "){
                categ = "default"
            }
            viewModel.insertNote(Note(0, edit_text_view_title.text.toString(), edit_text_view_text.text.toString(), categ))
        Toast.makeText(context, "Note Added", Toast.LENGTH_SHORT).show()
            savedOrNot = 1
        }
    }

//    fun updateToolbar() {
//        isEditing = !isEditing
//
//        val saveItem = viewBinding.myToolbar.menu.findItem(R.id.action_done)
//        saveItem.isVisible = isEditing
//
//    }


}