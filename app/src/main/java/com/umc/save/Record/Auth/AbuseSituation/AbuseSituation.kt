package com.umc.save.Record.Auth.AbuseSituation

import com.google.gson.annotations.SerializedName

data class AbuseSituation(
    @SerializedName(value = "childIdx") var childIdx: Int,
    @SerializedName(value = "suspectIdx") var suspectIdx: Int,
    @SerializedName(value = "date") var date: String,
    @SerializedName(value = "time") var time: String,
    @SerializedName(value = "place") var place: String,
    @SerializedName(value = "detail") var detail: String,
    @SerializedName(value = "etc") var etc: String,
    @SerializedName(value = "type") var type: String
    )
