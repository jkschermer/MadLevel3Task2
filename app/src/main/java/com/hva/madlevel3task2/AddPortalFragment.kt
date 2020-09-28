package com.hva.madlevel3task2

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_portal_add.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"


class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portal_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        add_portal_btn.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal() {
        val portalText = etTitle.text.toString()
        val urlText = etLink.text.toString()

        val portal = Portal(portalText, urlText)

        if (portal.title.isNotBlank() && portal.url.isNotBlank()) {
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portal)))
            findNavController().popBackStack()
        }
        else {
            Toast.makeText(
                activity,
                R.string.not_valid_portal, Toast.LENGTH_SHORT).show()
        }


    }
}