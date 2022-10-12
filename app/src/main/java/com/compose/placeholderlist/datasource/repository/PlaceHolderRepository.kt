package com.compose.placeholderlist.datasource.repository

import com.compose.placeholderlist.datasource.network.PlaceHolderService
import javax.inject.Inject

class PlaceHolderRepository @Inject constructor(private val placeHolderService: PlaceHolderService)  {
    suspend fun fetchPlaceHolderList() = placeHolderService.fetchPlaceHolderList()
}
