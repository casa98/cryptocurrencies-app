package com.casa98.currencies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class TagInfoDto(
    @SerializedName("coin_counter")
    val coinCounter: Int,
    @SerializedName("description")
    val description: String,
    @SerializedName("ico_counter")
    val icoCounter: Int,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String
)