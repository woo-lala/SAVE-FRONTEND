package com.umc.save.Record.Auth.SuspectRecord

import android.util.Log
import com.umc.save.Record.Auth.SuspectRecord.Result
import com.umc.save.Record.Suspect
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class suspectIdx_var {
    object suspectIdx {
        var suspectIdx : Int = 0
    }

}
class SuspectRecordService {

    private var result : Result = Result(suspectIdx = 1)

    private lateinit var suspectRecordResult : SuspectRecordResult

    fun setRecordResult(suspectRecordResult: SuspectRecordResult){
        this.suspectRecordResult = suspectRecordResult
    }

    fun record(record: Suspect){
        val authService = getRetrofit().create(SuspectRecordRetrofitInterface::class.java)
        authService.record(record).enqueue(object: Callback<SuspectRecordResponse> {
            override fun onResponse(call: Call<SuspectRecordResponse>, response: Response<SuspectRecordResponse>) {
                Log.d("RECORD/SUCCESS",response.toString())
                val resp: SuspectRecordResponse = response.body()!!
                result = resp.result!!
                when(resp.code){
                    1000 -> suspectRecordResult.recordSuccess(result)
                    else -> suspectRecordResult.recordFailure()
                }
            }

            override fun onFailure(call: Call<SuspectRecordResponse>, t: Throwable) {
                Log.d("RECORD/FAILURE",t.message.toString())
            }
        })

    }
}