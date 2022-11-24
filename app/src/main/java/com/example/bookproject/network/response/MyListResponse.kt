package com.example.bookproject.network.response

import com.google.gson.annotations.SerializedName

class MyListResponse<T> (@SerializedName("data")
val data : List<T>?) : MyResponse()