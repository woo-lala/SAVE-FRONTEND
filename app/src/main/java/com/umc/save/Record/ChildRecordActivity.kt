package com.umc.save.Record

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.umc.save.R
import com.umc.save.databinding.ActivityChildRecordBinding
//import com.umc.save.databinding.ActivityRecordMainBinding

class ChildRecordActivity : AppCompatActivity() {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0

    lateinit var binding: ActivityChildRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nameNotSureBtn.setOnClickListener{
            name_num++
            if(name_num % 2 != 0 ) {
                binding.nameNotSureBtn.isSelected = true
            } else{
                binding.nameNotSureBtn.isSelected = false
            }
        }

        binding.ageNotSureBtn.setOnClickListener{
            age_num++
            if(age_num % 2 != 0 ) {
                binding.ageNotSureBtn.isSelected = true
                binding.recordChildAgeNS.isEnabled = true
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordChildAgeNS.isEnabled = false
                binding.recordChildAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }

        // 아이 성별
        binding.childMale.setOnClickListener{
            male++
            if(male % 2 != 0 ) {
                binding.childMale.isSelected = true
            } else{
                binding.childMale.isSelected = false
            }
        }

        binding.childFemale.setOnClickListener{
            female++
            if(female % 2 != 0 ) {
                binding.childFemale.isSelected = true
            } else{
                binding.childFemale.isSelected = false
            }
        }
        binding.childDontKnow.setOnClickListener{
            dontK++
            if(dontK % 2 != 0 ) {
                binding.childDontKnow.isSelected = true
            } else{
                binding.childDontKnow.isSelected = false
            }
        }



        binding.recordDone.setOnClickListener{
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

    }
}