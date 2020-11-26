package com.example.mynote.fragment.fragmentTabLayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.mynote.fragment.fragmentRecyclerViewPrivateNotes.FragmentPrivateNotes
import com.example.mynote.fragment.fragmentRecyclerViewNote.FragmentBasicNotes

class ViewPagerAdapterForTabLayout(fm : FragmentManager, lf : Lifecycle) : FragmentStateAdapter(fm, lf) {

    private val TAB_TITLES = arrayListOf<String>(
        "basic",
        "private"
    )

    private val LIST_FRAGMENTS = arrayListOf<Fragment>(
        FragmentBasicNotes(),
        FragmentPrivateNotes()
    )

    override fun getItemCount(): Int = LIST_FRAGMENTS.size

    override fun createFragment(position: Int): Fragment {
        return LIST_FRAGMENTS[position]
    }

    fun getPageTitle(position: Int) : CharSequence =TAB_TITLES[position]
}