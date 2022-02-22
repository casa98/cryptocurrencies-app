package com.casa98.currencies.data.remote.dto


import com.google.gson.annotations.SerializedName

data class CoinTweetDto(
    @SerializedName("date")
    val date: String,
    @SerializedName("is_retweet")
    val isRetweet: Boolean,
    @SerializedName("like_count")
    val likeCount: Int,
    @SerializedName("retweet_count")
    val retweetCount: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_id")
    val statusId: String,
    @SerializedName("status_link")
    val statusLink: String,
    @SerializedName("user_image_link")
    val userImageLink: String,
    @SerializedName("user_name")
    val userName: String
)