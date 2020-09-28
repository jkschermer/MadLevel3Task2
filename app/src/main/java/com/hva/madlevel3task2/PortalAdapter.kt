package com.hva.madlevel3task2

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_portal.view.*

class PortalAdapter(private val portalItems: List<Portal>) :
    RecyclerView.Adapter<PortalAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

        fun databind(portal: Portal) {
            itemView.tvPortal.text = portal.title
            itemView.tvURL.text = portal.url
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_portal, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return portalItems.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(portalItems[position])

        // setting click listener for cardview and navigation to the appropriate page
        holder.itemView.setOnClickListener {
            val url = portalItems[position].url
            val builder = CustomTabsIntent.Builder()
            builder.addDefaultShareMenuItem()
            val intent = builder.build()

            intent.launchUrl(holder.itemView.context, Uri.parse(url))
        }
    }
}