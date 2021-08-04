package com.thetechguy.myapplication

import android.content.Intent
import android.content.Intent.getIntentOld
import android.graphics.ColorFilter
import android.net.Uri
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

        noteit_email.setOnClickListener {
            val mailTo = "mailto:bob@example.org" +
                    "?cc=" + "alice@example.com" +
                    "&subject="  + "" +
                    "&body=" + ""
            val intent: Intent = Intent(Intent.ACTION_SEND).apply {
                // The intent does not have a URI, so declare the "text/plain" MIME type
                type = "message/rfc822"
//                putExtra(Intent.CATEGORY_APP_EMAIL, "noteitnotepad@outlook.com")
                putExtra(Intent.EXTRA_EMAIL, arrayOf("noteitnotepad@outlook.com")) // recipients
//                putExtra(Intent.EXTRA_SUBJECT, "Email subject")
//                putExtra(Intent.EXTRA_TEXT, "Email message text")
                data = Uri.parse("mailto:")
                // You can also attach multiple items by passing an ArrayList of Uris

            }
//            intent.putExtra(Intent.EXTRA_EMAIL, "noteitnotepad@outlook.com")
//            intent.setData(Uri.parse(mailTo))
            intent.type = "message/rfc822"
            startActivity(intent)
        }
    }




}