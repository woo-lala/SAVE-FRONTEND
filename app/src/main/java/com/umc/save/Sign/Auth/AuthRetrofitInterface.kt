package com.umc.save.Sign.Auth



import retrofit2.Call
import retrofit2.http.*

//로그인
//method, url & 어떤 method를 실행시킬 것인지 표시해주는 interface
//<> 괄호 안 = response받을 값의 데이터클래스 형태로 정의된 것
interface AuthRetrofitInterface {
    @POST("/auth/login")
    fun login(@Body auth: Auth): Call<AuthResponse>

    @GET("/auth/token")
    fun getAutologin(@Header("X-ACCESS-TOKEN") jwt : String): Call<AutoLoginResponse>

    @GET("auth/logout/{userIdx}")
    fun getLogout(@Header("X-ACCESS-TOKEN")jwt: String, @Path("userIdx")userIdx: Int): Call<getLogoutResponse>
}