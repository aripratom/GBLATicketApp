package com.aripratom.gblaticketingapp.ui.notifikasi


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.aripratom.gblaticketingapp.R
import com.aripratom.gblaticketingapp.ui.pemesanan.PemesananFragment
import com.aripratom.gblaticketingapp.ui.tiketku.TiketkuFragment
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.fragment_notifikasi.*


/**
 * A simple [Fragment] subclass.
 */
class NotifikasiFragment : Fragment() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notifikasi, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        mSectionsPagerAdapter = SectionsPagerAdapter(childFragmentManager)

        viewpager.adapter = mSectionsPagerAdapter

        viewpager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(viewpager) as TabLayout.BaseOnTabSelectedListener<*>)
    }

    inner class SectionsPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return PemesananFragment()
                1 -> return TiketkuFragment()
            }
            return PemesananFragment()
        }

        override fun getCount(): Int {
            return 2
        }
    }

}
