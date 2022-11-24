package com.example.bookproject.network

import com.example.bookproject.network.book.BookRequest
import com.example.bookproject.network.book.BookResponse
import com.example.bookproject.network.response.MyItemResponse
import com.example.bookproject.network.response.MyListResponse
import com.example.bookproject.network.response.MyResponse
import retrofit2.http.*

interface BookService {
    @GET("records/all")
    suspend fun getAllBooks(
        @Query("student_id") student_id: String
    ): MyListResponse<BookResponse>


    @GET("records/{record_id}")
    suspend fun getBookById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<BookResponse>

    @POST("records")
    suspend fun insertNewBook(
        @Query("student_id") student_id: String,
        @Body bookRequest: BookRequest
    ): MyResponse

    @PUT("records/{record_id}")
    suspend fun updateOneBookById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body bookRequest: BookRequest
    ): MyResponse

    @DELETE("records/{record_id}")
    suspend fun deleteOneBookById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyResponse

    @DELETE("records/all")
    suspend fun deleteAllBooks(
        @Query("student_id") student_id: String
    ): MyResponse
}
