package com.umc.save.Locker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.ChildRecordActivity
import com.umc.save.databinding.FragmentLockerBinding
import kotlin.collections.ArrayList

class LockerFragment : Fragment(), ChildrenView {

    var userIdx = 2
    lateinit var binding: FragmentLockerBinding
    var currentPosition = 0
    var childList = ArrayList<Child>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLockerBinding.inflate(inflater,container,false)

        binding.lockerChildEditTv.setOnClickListener {
            openDeleteActivity()
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        //지우고 나면 삭제되고 아동 리스트가 재로딩이 되야 한다 (뒤로 가기했을 때 재로딩을 위해)
        getChildren()

    }

    //서버에서 아동 리스트 가져오기
    private fun getChildren() {

        val childrenService = ChildrenService()

        childrenService.setChildrenView(this)
        childrenService.getChildren(userIdx)

    }

    //리사이클러뷰 불러오기
    private fun initRecyclerView(result : ArrayList<Child>) {
        val childRVAdapter = ChildRVAdapter(result)
//        requireContext(),result
        binding.childListRv.adapter = childRVAdapter
        binding.childListRv.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)

        childRVAdapter.setMyItemClickListener(object: ChildRVAdapter.MyItemClickListener {
            override fun onItemClick(child: Child) {
                changeRecordChildLockerFragment(child)
            }

            override fun onItemClickAdd() {
                openRecordActivity()
            }
        })

    }

    private fun changeRecordChildLockerFragment(child: Child) {
        (context as MainActivity).supportFragmentManager
            .beginTransaction()
            .replace(R.id.main_frm, InfoChildLockerFragment().apply {
                arguments = Bundle().apply {
                    val gson = Gson()
                    val childJson = gson.toJson(child)
                    putString("child",childJson)
                }
            })
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    private fun openRecordActivity() {
        val intent = Intent(context, ChildRecordActivity::class.java)
        startActivity(intent)
    }

    //delete에 arraylist 넘길지 check
    private fun openDeleteActivity() {
        val intent = Intent(context, ChildDeleteLockerActivity::class.java)
//        intent.putExtra("childList",childList)
        startActivity(intent)
    }


    override fun onGetChildSuccess(code: Int, result: ArrayList<Child>) {
        initRecyclerView(result)
        childList = result
        Log.d("GET-SUCCESS","요청에 성공하였습니다.")
        Log.d("check recieve", result.toString())
    }

    override fun childNotExist(code: Int, message : String) {
        Log.d("GET-NOT-EXIST",message)
    }

    override fun onGetChildFailure(code: Int, message : String) {
        Log.d("GET-FAILURE",message)
    }


}
