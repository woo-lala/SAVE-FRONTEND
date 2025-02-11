package com.umc.save.Sign.User


import android.util.Log
import com.umc.save.Sign.Auth.AuthResponse
import com.umc.save.getRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

//회원가입
// view, api 관리

class userIdx {
    object userIdx {
        var userIdx : Int = 0
    }
}

class UserService {
    private lateinit var signUpView: SignUpView

    fun setSignUpView(signUpView: SignUpView) {
        this.signUpView = signUpView
    }

    fun signUp(user: User) {
        val signUpService = getRetrofit().create(UserRetrofitInterface::class.java)

        signUpService.signUp(user).enqueue(object : Callback<AuthResponse> {
            //정상적인 통신이 성공
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {

                Log.d("SIGNUP/RESPONSE", response.toString())

                if (response.isSuccessful && response.code() == 200) {
                    val signUpResponse: AuthResponse = response.body()!!

                    when (val code = signUpResponse.code) {
                        1000 -> {signUpView.onSignUpSuccess()
                            Log.d("SIGNUP-SUCCESS", signUpResponse.toString())
                        }
                        else -> {
                            Log.d("SIGNUP-ERROR", signUpResponse.toString())
                            signUpView.onSignUpFailure(code, signUpResponse.message)
                        }
                    }
                }
            }

            //네트워크 연결 실패처리
            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                Log.d("SIGNUP/FAILUARE", t.message.toString())
            }
        })

        //함수가 잘 실행되었는지 확인하는 log
        Log.d("SIGNUP", "HELLO")
    }
}
