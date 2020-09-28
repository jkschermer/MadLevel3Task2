package com.hva.madlevel3task2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_overview.*
import kotlinx.android.synthetic.main.item_portal.*
import kotlinx.android.synthetic.main.item_portal.view.*
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class OverviewFragment : Fragment() {

    private val portalItems = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portalItems)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_overview, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initViews()
    }

    private fun initViews() {
        rvOverview.layoutManager = GridLayoutManager(this.activity, 2)
        rvOverview.adapter = portalAdapter
        observeAddPortalResult()
//        onCustomTab()
        createItemTouchHelper().attachToRecyclerView(rvOverview)
    }



    private fun onCustomTab() {

        tvURL.setOnClickListener{
            val url = tvURL.text.toString()
            val builder = CustomTabsIntent.Builder()
            builder.setToolbarColor(resources.getColor(R.color.colorPrimaryDark))
            builder.addDefaultShareMenuItem()
            val intent = builder.build()

            intent.launchUrl(this.requireContext(), Uri.parse(url))
        }
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                val portal = Portal(it.title, it.url)

                portalItems.add(portal)
                portalAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        // Callback which is used to create the ItemTouch helper. Only enables left swipe.
        // Use ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) to also enable right swipe.
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                portalItems.removeAt(position)
                portalAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }
}
