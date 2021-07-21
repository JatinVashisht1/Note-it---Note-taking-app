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
import kotlinx.android.synthetic.main.fragment_privacy_policy.*

class PrivacyPolicyFragment : Fragment(R.layout.fragment_privacy_policy){



    var savedOrNot = 0

    val viewModel: MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity as AppCompatActivity).supportActionBar?.hide()


    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_privacy_policy_toolbar.setNavigationIcon(R.drawable.ic_back)
        fragment_privacy_policy_toolbar.setNavigationOnClickListener{
            val action = PrivacyPolicyFragmentDirections.actionPrivacyPolicyFragmentToHomeFragment2()
            findNavController().navigate(action)
        }

        text_view_privacy_policy.text = "This document describes data collected and processed by the \"Notepad Free\" app and third party software. By using the app, user consents to the privacy policy.\n" +
                "\n" +
                "1. Notes\n" +
                "\n" +
                "All of the notes that user creates are stored in the app's internal storage. The notes can't be accessed by any other app on the phone. The only way to access the notes is to use the app. To be clear: only user of the app can see notes stored on the phone. Although this document may seem long, that (in the author's opinion) is the most important information.\n" +
                "Since the 1.7.0 version, the app uses Google device backup copy, if it's turned on in the device's and the app's settings. In that case an encrypted copy of notes is stored on Google Drive. It can be changed by a user at any time. More information is available on Google'"

            /*
            This document describes data collected and processed by the "Notepad Free" app and third party software. By using the app, user consents to the privacy policy.

1. Notes

All of the notes that user creates are stored in the app's internal storage. The notes can't be accessed by any other app on the phone. The only way to access the notes is to use the app. To be clear: only user of the app can see notes stored on the phone. Although this document may seem long, that (in the author's opinion) is the most important information.
Since the 1.7.0 version, the app uses Google device backup copy, if it's turned on in the device's and the app's settings. In that case an encrypted copy of notes is stored on Google Drive. It can be changed by a user at any time. More information is available on Google'
             */

    }




}