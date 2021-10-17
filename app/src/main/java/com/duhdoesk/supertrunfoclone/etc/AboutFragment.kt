package com.duhdoesk.supertrunfoclone.etc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.duhdoesk.supertrunfoclone.R
import mehdi.sakout.aboutpage.AboutPage
import java.util.*

class AboutFragment : Fragment() {
    //Inflating and Returning the View with DataBindingUtil
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var aboutPage = AboutPage(context)
            .isRTL(false)
            .setImage(R.drawable.about_header)
            .setDescription(getString(R.string.about_description))
            .addGroup("Other Projects")
            .addGitHub("duhdoesk", "GitHub")
            .addGroup("Contact me")
            .addEmail("dev.edusc@gmail.com", "E-mail")
            .addInstagram("duscaranari", "Instagram")
            .addFacebook("du.scaranari", "Facebook")
            .addWebsite("https://wa.me/5511994760191", "WhatsApp")

            .create()

        return aboutPage
    }
}