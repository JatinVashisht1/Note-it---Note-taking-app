package com.thetechguy.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_add_note.*
//import kotlinx.android.synthetic.main.fragment_add_note.btn_update_note
import kotlinx.android.synthetic.main.fragment_view_note.*


class ViewTextFragment : Fragment(R.layout.fragment_view_note){


    val args : ViewTextFragmentArgs by navArgs()
    val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val callback = requireActivity().onBackPressedDispatcher.addCallback(this){
            val action = ViewTextFragmentDirections.actionViewTextFragmentToHomeFragment()
            findNavController().navigate(action)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragment_view_text_toolbar.inflateMenu(R.menu.fragment_view_text_menu)
        fragment_view_text_toolbar.setNavigationIcon(R.drawable.ic_back)
        fragment_view_text_toolbar.setNavigationIconTint(resources.getColor(R.color.white))
        fragment_view_text_toolbar.setNavigationOnClickListener{
            val action = ViewTextFragmentDirections.actionViewTextFragmentToHomeFragment()
            findNavController().navigate(action)
        }
        edit_text_view_title_view.setText(args.currentNote.title)
        edit_text_view_text_view.setText(args.currentNote.author)
       fragment_view_text_toolbar.setOnMenuItemClickListener{

            var title = edit_text_view_title_view.text.toString()
            val text = edit_text_view_text_view.text.toString()
           if(title=="" && text!=""){
               title="Untitled"
               viewModel.updateNote(Note(args.currentNote.id, title, text, args.currentNote.category))
               Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
           }
           if(title==""&&text==""){
               Toast.makeText(context, "Enter title or note", Toast.LENGTH_LONG).show()
           }
           else if(title!="" || text !="")
           {
               viewModel.updateNote(Note(args.currentNote.id, title, text, args.currentNote.category))
               Toast.makeText(context, "Note Updated", Toast.LENGTH_LONG).show()
           }

           true
        }
    }





}