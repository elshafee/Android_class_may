package com.elshafee.androidclassmay.fragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.elshafee.androidclassmay.R
import com.elshafee.androidclassmay.databinding.ActivityOurEventsBinding
import com.elshafee.androidclassmay.fragments.utils.ViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class OurEvents : Fragment() {
    private var _binding:ActivityOurEventsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ActivityOurEventsBinding.inflate(inflater,container,false)
        var view = binding.root
         var viewPager = view.findViewById<ViewPager2>(R.id.viewPagerOfEvents)
        var tablayout = view.findViewById<TabLayout>(R.id.tablayoutourEvents)
        val images = listOf(
            R.drawable.bo,
            R.drawable.eventone,
            R.drawable.eventtwo,
            R.drawable.onboarding
        )

        val myAdapter = ViewPagerAdapter(images)
        binding.viewPagerOfEvents.adapter = myAdapter

        TabLayoutMediator(tablayout,viewPager){tab,position ->
            tab.text = "Tab ${position +1}"
        }.attach()
        binding.tablayoutourEvents.addOnTabSelectedListener(object :
        TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                Toast.makeText(activity,"Selected ${tab?.text}",Toast.LENGTH_SHORT).show()
            }
            override fun onTabReselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity,"Reselected ${tab?.text}",Toast.LENGTH_SHORT).show()

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                Toast.makeText(activity,"Unselected ${tab?.text}",Toast.LENGTH_SHORT).show()


            }
        }
        )

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}