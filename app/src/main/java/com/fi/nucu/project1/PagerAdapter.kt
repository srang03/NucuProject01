package com.fi.nucu.project1

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PagerAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    val PAGE_MAX_CNT = 3

    override fun getCount(): Int {
        return PAGE_MAX_CNT
    }

    override fun getItem(position: Int): Fragment {
        val fragment = when(position)
        {
            1 -> StartType2().newInstance()
            2 -> StartType3().newInstance()
            else -> StartType1().newInstance()
        }
        return fragment
    }

    override fun getPageTitle(position: Int): CharSequence? {
        val title = when(position)
        {
            1 -> "one"
            2 -> "tow"
            3 -> "three"
            else -> "main"
        }
        return title
    }
}