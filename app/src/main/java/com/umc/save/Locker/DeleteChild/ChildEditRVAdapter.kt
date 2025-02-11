package com.umc.save.Locker.DeleteChild

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.umc.save.Locker.data.Child
import com.umc.save.R
import com.umc.save.databinding.ItemAddBinding
import com.umc.save.databinding.ItemChildEditBinding
import java.text.SimpleDateFormat

class ChildEditRVAdapter (val childList : ArrayList<Child>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//    val context: Context,

    interface MyItemClickListener {
        fun onItemClick(child: Child)
        fun onItemClickAdd()
    }

    private lateinit var mItemClickListener: MyItemClickListener

    fun setMyItemClickListener(itemClickListener : MyItemClickListener) {
        mItemClickListener = itemClickListener
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == childList.size) {
            R.layout.item_add
        } else {
            R.layout.item_child_edit
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when (viewType) {
            R.layout.item_child_edit -> {
                val view = ItemChildEditBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderChild(view)
            }
            else -> {
                val view = ItemAddBinding.inflate(LayoutInflater.from(viewGroup.context),
                    viewGroup, false)
                ViewHolderAdd(view)
            }
        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        if (position == childList.size) {
            (holder as ViewHolderAdd).bind()
            (holder as ViewHolderAdd).itemView.setOnClickListener{  mItemClickListener.onItemClickAdd()}
            } else {
            (holder as ViewHolderChild).bind(childList[position])
            (holder as ViewHolderChild).itemView.setOnClickListener{
                mItemClickListener.onItemClick(childList[position])
                notifyItemChanged(position)

//                if(childList[position].isClicked) {
//                    holder.itemView.findViewById<ImageView>(R.id.item_child_info_iv).setBackgroundColor(Color.parseColor("#FF7F61"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_name_tv).setTextColor(Color.parseColor("#FFFFFF"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_date_tv).setTextColor(Color.parseColor("#FFFFFF"))
//                } else {
//                    holder.itemView.findViewById<ImageView>(R.id.item_child_info_iv).setBackgroundColor(Color.parseColor("#F5F5F5"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_name_tv).setTextColor(Color.parseColor("#FF7F61"))
//                    holder.itemView.findViewById<TextView>(R.id.item_child_info_date_tv).setTextColor(Color.parseColor("#FF7F61"))
//                }

            }
        }

    }

    override fun getItemCount(): Int = childList.size + 1

    inner class ViewHolderChild(val binding: ItemChildEditBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SimpleDateFormat")
        fun bind(child: Child) {

            var childInfo: String

            val gender: String = when (child.childGender) {
                "male" -> "남자"
                "female" -> "여자"
                else -> "성별 모름"
            }

            //이미지 랜덤으로 보여지게 하기 (다시 로딩 되어도 그 아동은 그 이미지로)
            if (gender == "남자") {
                val imageSelect = child.childIdx % 15

                when (imageSelect) {
                    0 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_05_green)
                    1 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_01_blue)
                    2 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_02_red)
                    3 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_03_green)
                    4 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_04_blue)
                    5 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_05_red)
                    6 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_01_green)
                    7 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_02_blue)
                    8 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_03_red)
                    9 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_04_green)
                    10 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_05_blue)
                    11 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_01_red)
                    12 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_02_green)
                    13 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_03_blue)
                    14 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_boy_04_red)
                }


            } else if (gender =="여자") {
                val imageSelect = child.childIdx % 15

                when (imageSelect) {
                    0 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_05_green)
                    1 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_01_blue)
                    2 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_02_red)
                    3 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_03_green)
                    4 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_04_blue)
                    5 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_05_red)
                    6 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_01_green)
                    7 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_02_blue)
                    8 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_03_red)
                    9 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_04_green)
                    10 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_05_blue)
                    11 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_01_red)
                    12 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_02_green)
                    13 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_03_blue)
                    14 -> binding.itemChildInfoImage.setImageResource(R.drawable.ilst_girl_04_red)
                }

            } else {
                binding.itemChildInfoImage.setImageResource(R.drawable.white_background_image)
            }

            childInfo = gender + "/" + child.childAge + "/" + child.childAddress

            if(child.childDetailAddress != "") {
                childInfo = childInfo + "/" + child.childDetailAddress
            }

            val sdf = SimpleDateFormat("yyyy.MM.dd")

            binding.itemChildInfoNameTv.text = child.childName
            binding.itemChildInfoSpecificTv.text = childInfo
            binding.itemChildInfoDateTv.text = sdf.format(child.createAt).toString()


            //선택했을 때 배경과 글씨 색 변화
            if(child.isClicked) {
                binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#FF7F61"))
                binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FFFFFF"))
                binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FFFFFF"))
            } else {
                binding.itemChildInfoIv.setBackgroundColor(Color.parseColor("#F5F5F5"))
                binding.itemChildInfoNameTv.setTextColor(Color.parseColor("#FF7F61"))
                binding.itemChildInfoDateTv.setTextColor(Color.parseColor("#FF7F61"))
            }


        }
    }

    inner class ViewHolderAdd(val binding: ItemAddBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind() {

        }
    }

}
