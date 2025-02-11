package com.umc.save.Record

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.umc.save.Home.option.HomeAlarmFragment
import com.umc.save.Locker.LockerFragment
import com.umc.save.MainActivity
import com.umc.save.R
import com.umc.save.Record.Auth.ChildRecord.childidx_var
import com.umc.save.Record.Auth.SuspectRecord.Result
import com.umc.save.Record.Auth.SuspectRecord.SuspectRecordResult
import com.umc.save.Record.Auth.SuspectRecord.SuspectRecordService
import com.umc.save.Record.Auth.SuspectRecord.suspectIdx_var
import com.umc.save.databinding.ActivityChildRecordBinding
import com.umc.save.databinding.ActivityMainBinding
import com.umc.save.databinding.ActivityOffenderRecordBinding
import com.umc.save.databinding.FragmentRecordMainBinding


var recordDoneFragment_call : Boolean = false

class suspectRecord_var {
    object abuse {
        var suspectName : String = ""
        var suspectAge_1 : String = ""
        var suspectAge_2 : String = ""
        var relation : String = ""
    }
}

class OffenderRecordActivity : AppCompatActivity(), SuspectRecordResult {
    var name_num = 0
    var age_num = 0

    var male = 0
    var female = 0
    var dontK = 0
    var father = 0
    var mother = 0


    lateinit var binding: ActivityOffenderRecordBinding
    lateinit var binding2: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOffenderRecordBinding.inflate(layoutInflater)
        binding2 = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

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
                binding.recordOffenderAgeNS.isEnabled = true
                binding.txtSae.setTextColor(Color.parseColor("#FF7F61"))
                binding.txtFlow.setTextColor(Color.parseColor("#FF7F61"))
                binding.recordOffenderAgeNS.setHintTextColor(Color.parseColor("#FF7F61"))
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_red)
            } else{
                binding.ageNotSureBtn.isSelected = false
                binding.recordOffenderAgeNS.isEnabled = false
                binding.txtSae.setTextColor(Color.parseColor("#B5B5B5"))
                binding.txtFlow.setTextColor(Color.parseColor("#B5B5B5"))
                binding.recordOffenderAgeNS.setHintTextColor(Color.parseColor("#B5B5B5"))
                binding.recordOffenderAgeNS.backgroundTintList = ContextCompat.getColorStateList(applicationContext, R.color.dark_gray)
            }
        }

        binding.recordAdd.setOnClickListener{
            save()
            startActivity(Intent(this, OffenderRecordActivity::class.java))
        }

        // 학대행위자 성별
        binding.offenderMale.setOnClickListener{
            male++
            Log.d("받아온 childIdx값 ==================================== ", childidx_var.childIdx.childIdx.toString())

            if(male % 2 != 0 ) {
                if(binding.offenderFemale.isSelected) {
                    binding.offenderFemale.isSelected = false
                    female++
                }
                if(binding.offenderDontKnow.isSelected) {
                    binding.offenderDontKnow.isSelected = false
                    dontK++
                }

                binding.offenderMale.isSelected = true
            } else{
                binding.offenderMale.isSelected = false
            }
        }

        binding.offenderFemale.setOnClickListener{
            female++
            if(female % 2 != 0 ) {
                if(binding.offenderMale.isSelected) {
                    binding.offenderMale.isSelected = false
                    male++
                }
                if(binding.offenderDontKnow.isSelected) {
                    binding.offenderDontKnow.isSelected = false
                    dontK++
                }

                binding.offenderFemale.isSelected = true
            } else{
                binding.offenderFemale.isSelected = false
            }
        }
        binding.offenderDontKnow.setOnClickListener{
            dontK++
            if(dontK % 2 != 0 ) {
                if(binding.offenderMale.isSelected) {
                    binding.offenderMale.isSelected = false
                    male++
                }
                if(binding.offenderFemale.isSelected) {
                    binding.offenderFemale.isSelected = false
                    female++
                }
                binding.offenderDontKnow.isSelected = true
            } else{
                binding.offenderDontKnow.isSelected = false
            }
        }


        binding.offenderFather.setOnClickListener{
            father++
            if(father % 2 != 0 ) {
                if(binding.offenderMother.isSelected) {
                    binding.offenderMother.isSelected = false
                    mother++
                }

                binding.offenderFather.isSelected = true
            } else{
                binding.offenderFather.isSelected = false
            }

            if(childidx_var.childIdx.childIdx != null
                && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                && !binding.recordOffenderAge.text.equals("")
                && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || !binding.recordRelation.text.toString().trim().isEmpty())) {
                binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
            }
            else{
                binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }
        binding.offenderMother.setOnClickListener{
            mother++
            if(mother % 2 != 0 ) {
                if(binding.offenderFather.isSelected) {
                    binding.offenderFather.isSelected = false
                    father++
                }
                binding.offenderMother.isSelected = true
            } else{
                binding.offenderMother.isSelected = false
            }

            if(childidx_var.childIdx.childIdx != null
                && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                && !binding.recordOffenderAge.text.equals("")
                && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || binding.recordRelation.text.toString().trim().isEmpty())) {
                binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
            }
            else{
                binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
            }
        }


        binding.recordOffenderName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                suspectRecord_var.abuse.suspectName = binding.recordOffenderName.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(childidx_var.childIdx.childIdx != null
                    && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                    && !binding.recordOffenderAge.text.equals("")
                    && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || binding.recordRelation.text.toString().trim().isEmpty())) {
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
                }
                else{
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }
        })

        binding.recordOffenderAge.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                suspectRecord_var.abuse.suspectAge_1 = binding.recordOffenderAge.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(childidx_var.childIdx.childIdx != null
                    && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                    && !binding.recordOffenderAge.text.equals("")
                    && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || binding.recordRelation.text.toString().trim().isEmpty())) {
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
                }
                else{
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }
        })

        binding.recordOffenderAgeNS.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                suspectRecord_var.abuse.suspectAge_2 = binding.recordOffenderAgeNS.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(childidx_var.childIdx.childIdx != null
                    && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                    && !binding.recordOffenderAge.text.equals("")
                    && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || binding.recordRelation.text.toString().trim().isEmpty())) {
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
                }
                else{
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }
        })

        binding.recordRelation.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                suspectRecord_var.abuse.relation = binding.recordRelation.text.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                if(childidx_var.childIdx.childIdx != null
                    && (binding.offenderMale.isSelected|| binding.offenderFemale.isSelected || binding.offenderDontKnow.isSelected)
                    && !binding.recordOffenderAge.text.equals("")
                    && (binding.offenderFather.isSelected || binding.offenderMother.isSelected || !binding.recordRelation.text.toString().trim().isEmpty())) {
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_red_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_red_background)
                }
                else{
                    binding.recordAdd.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                    binding.recordNext.setBackgroundResource(R.drawable.fragment_dark_gray_background)
                }
            }
        })



        binding.recordNext.setOnClickListener{
            if(!binding.offenderMale.isSelected && !binding.offenderFemale.isSelected && !binding.offenderDontKnow.isSelected)
                Toast.makeText(this, "학대 행위자 성별을 기록해주세요", Toast.LENGTH_SHORT).show()
            else{
                if(binding.recordOffenderAge.text.toString().trim().isEmpty())
                    Toast.makeText(this, "아동 성별을 입력해주세요", Toast.LENGTH_SHORT).show()
                else {
                    if (!binding.offenderMother.isSelected && !binding.offenderFather.isSelected && binding.recordRelation.text.toString().trim().isEmpty())
                        Toast.makeText(this, "아동과의 관계를 입력해주세요", Toast.LENGTH_SHORT).show()
                    else{
                        save()




//                        val recordDoneFragment = RecordDoneFragment()
//                        val fragment : Fragment? = supportFragmentManager.findFragmentByTag(RecordDoneFragment::class.java.simpleName)
//                        if(fragment !is RecordDoneFragment){
//                            supportFragmentManager.beginTransaction()
//                                .add(R.id.layout_content, recordDoneFragment, recordDoneFragment::class.java.simpleName)
//                                .commit()
//                        }
//                        binding.content.visibility = View.GONE
                    }
                }
            }
        }

        binding.recordAdd.setOnClickListener {

            if(!binding.offenderMale.isSelected && !binding.offenderFemale.isSelected && !binding.offenderDontKnow.isSelected)
                Toast.makeText(this, "학대 행위자 성별을 기록해주세요", Toast.LENGTH_SHORT).show()
            else{
                if(binding.recordOffenderAge.text.toString().trim().isEmpty())
                    Toast.makeText(this, "아동 성별을 입력해주세요", Toast.LENGTH_SHORT).show()
                else {
                    if (!binding.offenderMother.isSelected && !binding.offenderFather.isSelected && binding.recordRelation.text.toString().trim().isEmpty())
                        Toast.makeText(this, "아동과의 관계를 입력해주세요", Toast.LENGTH_SHORT).show()
                    else{
                        save()
                        startActivity(Intent(this, OffenderRecordActivity::class.java))
                    }
                }
            }
        }

    }


    private fun save(){
        val suspectRecordService = SuspectRecordService()
        suspectRecordService.setRecordResult(this)
        suspectRecordService.record(getSuspect())


    }

    private fun getSuspect() : Suspect {

        var childIdx : Int = childidx_var.childIdx.childIdx


        val suspectName : String = binding.recordOffenderName.text.toString()

        var suspectGender : String = "여"
        if(binding.offenderMale.isSelected)
            suspectGender = "male"
        if(binding.offenderFemale.isSelected)
            suspectGender = "female"
        if(binding.offenderDontKnow.isSelected)
            suspectGender = "unknown"

        var suspectAge : String = binding.recordOffenderAge.text.toString()
        if(binding.ageNotSureBtn.isSelected)
            suspectAge = suspectAge + "세 ~ " + binding.recordOffenderAge.text.toString() + "세 추정"

        val suspectAdress : String = binding.recordOffenderAddressBase.text.toString()
        val suspectDetailAdress : String = binding.recordOffenderAddressDetail.text.toString()

        var relationWithChild = ""
        if(binding.offenderFather.isSelected)
            relationWithChild = "부"
        else if (binding.offenderMother.isSelected)
            relationWithChild = "모"
        if(!binding.offenderMother.isSelected && !binding.offenderFather.isSelected)
            relationWithChild = binding.recordRelation.text.toString()

        val suspectEtc : String = binding.recordOffenderEtc.text.toString()

        Log.d("아동과의 관계 ==================================", relationWithChild)



        return Suspect(childIdx, suspectName, suspectGender, suspectAge, suspectAdress, suspectDetailAdress, relationWithChild, suspectEtc )
    }

    fun initActionBar() {
        val appBartext = findViewById<TextView>(R.id.appbar_page_name_tv)
        val appBarBtn = findViewById<ImageView>(R.id.appbar_back_btn)
        val appBarComplete = findViewById<TextView>(R.id.appbar_complete_tv)

        appBartext.text= "학대 행위자"
        appBartext.visibility= View.VISIBLE
        appBarComplete.text= "완료"
        appBarComplete.visibility= View.INVISIBLE
        appBarBtn.setOnClickListener{onBackPressed()}
    }

    override fun recordSuccess(result : Result) {
        suspectIdx_var.suspectIdx.suspectIdx = result.suspectIdx
        Toast.makeText(this, "학대 행위자 기록 성공.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 행위자 기록 성공.")

        recordDoneFragment_call = true

        finish()

    }

    override fun NeedChildIdx(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildGender(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedChildAge(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun NeedRelation(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun ChildDontExist(code: Int, message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", message)
    }

    override fun recordFailure() {
        Toast.makeText(this, "학대 행위자 기록 실패.", Toast.LENGTH_SHORT).show()
        Log.d("RECORD/FAILURE", "학대 행위자 기록 실패.")

    }
}