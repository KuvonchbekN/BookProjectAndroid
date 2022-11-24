package com.example.bookproject.network.response

import com.google.gson.annotations.SerializedName

class MyItemResponse<T>(
    @SerializedName("data")
    val data: T?
) : MyResponse() {
}