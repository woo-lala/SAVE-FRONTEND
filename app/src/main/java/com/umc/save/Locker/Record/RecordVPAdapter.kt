package com.umc.save.Locker.Record

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.umc.save.Locker.CalendarChildLockerFragment
import com.umc.save.Locker.Recording.RecordLockerFragment

class RecordVPAdapter(val child: Int, fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val childIdx = child

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> RecordLockerFragment(childIdx)
            else -> CalendarChildLockerFragment(childIdx)
        }

    }

}