package com.thetechguy.myapplication


import android.graphics.Color
import android.graphics.ColorFilter
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.BundleCompat
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.savedstate.SavedStateRegistry
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.fragment_view_note.*
import java.nio.channels.spi.SelectorProvider


const val KEY_SBT = "SBT key"



class HomeFragment : Fragment(R.layout.fragment_home), INotesRVAdapter{

    val viewModel: MainViewModel by activityViewModels()
     var checked = 0
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var navController: NavController
    lateinit var adapter : NoteRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        fragment_home_toolbar.inflateMenu(R.menu.fragment_home_menu)
        fragment_home_toolbar.title = "Note it"
        nav_view.setupWithNavController(NavController(requireContext()))
        fragment_home_toolbar.setNavigationIcon(R.drawable.ic_hamburger)
        fragment_home_toolbar.navigationIcon?.colorFilter = ColorFilter()
        fragment_home_toolbar.setNavigationOnClickListener{
            drawer_layout.openDrawer(Gravity.LEFT)
        }



        if(isSelectedModeOn){
            fragment_home_toolbar.menu.findItem(R.id.deleteSelected).isVisible = true
        }

        adapter = NoteRecyclerViewAdapter(requireContext(), this)
        recyclerView.adapter = adapter


        ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0 , ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val note = adapter.notes[viewHolder.adapterPosition]
                viewModel.onNoteSwiped(note)
            }

        }).attachToRecyclerView(recyclerView)

        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.homeFragment),
            drawer_layout
        )

        if(!grid_or_not)
        {
        recyclerView.layoutManager = LinearLayoutManager(context)

        }
        else if(grid_or_not){
            recyclerView.layoutManager = GridLayoutManager(context, 2)
        }


        addNote.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToAddTextFragment()
            findNavController().navigate(action)
        }

        fragment_home_toolbar.setOnMenuItemClickListener{
            when(it.itemId)
            {
                R.id.sort_by -> {
                    val arr = arrayOf("Date Created (Newest First)","Title (A-Z) ")
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Sort By")
                        // Single-choice items (initialized with checked item)
                        .setSingleChoiceItems(arr, checked) { dialog, which ->
                            // Respond to item chosen
                           checked = which
                            sbt = checked==1
                        }
                        .setNeutralButton("cancel") { _, _ ->
                            // Respond to neutral button press
                        }
                        .setPositiveButton("Sort") { dialog, which ->
                            // Respond to positive button press
                            if(checked == 1)
                            {
                                viewModel.allNotesByTitle.observe(viewLifecycleOwner, Observer {
                                    it?.let{
                                        adapter.updateList(it)
                                    }
                                })

                            }
                            if(checked == 0)
                            {
                                viewModel.allNotes.observe(viewLifecycleOwner, Observer {
                                    it?.let{

                                        adapter.updateList(it)

                                    }
                                })
                            }
                        }
                        .show()
                }

                R.id.linear_or_grid ->{
                    if(grid_or_not){
                        it.setIcon(R.drawable.ic_grid)
                        recyclerView.layoutManager = LinearLayoutManager(context)
                        grid_or_not = false
                    }
                    else if(!grid_or_not){
                        it.setIcon(R.drawable.ic_linear)
                        recyclerView.layoutManager = GridLayoutManager(context, 2)
                        grid_or_not = true
                    }
                }

                R.id.deleteSelected -> {
                    isSelectedModeOn = false
                    if(checkedNotesToDelete.isEmpty()){
                        Toast.makeText(context, "Select an item to delete", Toast.LENGTH_SHORT).show()
                    }
                    else if(checkedNotesToDelete.isNotEmpty()){
                    viewModel.deleteSelectedNotes(checkedNotesToDelete.toList())
                    }
                }

                R.id.delete_all_notes ->{
                    val builder = AlertDialog.Builder(requireContext())
                    //set title for alert dialog
                    builder.setTitle("Alert")
                    //set message for alert dialog
                    builder.setMessage("Are you sure you want to delete all notes ?")
                    builder.setIcon(android.R.drawable.ic_dialog_alert)

                    //performing positive action
                    builder.setPositiveButton("Yes") { dialogInterface, which ->
                        viewModel.deleteAll()
                        Toast.makeText(context, "Deleted all notes !", Toast.LENGTH_SHORT).show()
                    }
                    //performing cancel action
                    builder.setNeutralButton("Cancel") { dialogInterface, which ->

                    }
                    //performing negative action
                    builder.setNegativeButton("No") { dialogInterface, which ->

                    }
                    // Create the AlertDialog
                    val alertDialog: AlertDialog = builder.create()
                    // Set other dialog properties
                    alertDialog.setCancelable(true)
                    alertDialog.show()
                }

            }
            true
        }

        if(!sbt)
        {
            viewModel.allNotes.observe(viewLifecycleOwner, Observer{
                it?.let {
                    adapter.updateList(it) //? will check for whether the list is null or not
                }

            })

        }
        else if(sbt){
            viewModel.allNotesByTitle.observe(viewLifecycleOwner, Observer{

                it?.let {

                    adapter.updateList(it) //? will check for whether the list is null or not

                }

            })
        }




}

    override fun navigateToViewText(note: Note) {
        if(a.isSelected){

        }
            val action = HomeFragmentDirections.actionHomeFragmentToViewTextFragment(note)
            findNavController().navigate(action)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(KEY_SBT,  sbt)
    }
}
