package com.compose.placeholderlist.datasource.network

import com.compose.placeholderlist.datasource.models.PlaceHolderModel
import retrofit2.http.GET

interface PlaceHolderService {
    @GET("photos") suspend fun fetchPlaceHolderList(
    ): List<PlaceHolderModel>
}