package com.compose.placeholderlist.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.compose.placeholderlist.datasource.models.PlaceHolderModel
import com.compose.placeholderlist.datasource.network.CoroutineDispatcherProvider
import com.compose.placeholderlist.datasource.repository.PlaceHolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlaceHolderViewModel @Inject constructor(private val placeHolderRepository: PlaceHolderRepository,
@ApplicationContext private val context: Context,
private val coroutineDispatcherProvider: CoroutineDispatcherProvider) : ViewModel(){

    private val _placeHolderResponseState = MutableStateFlow<PlaceHolderUiState>(PlaceHolderUiState.Empty)
    val placeHolderResponseState: StateFlow<PlaceHolderUiState> = _placeHolderResponseState

    init {
     fetchPlaceHolderPosts()
    }

   private fun fetchPlaceHolderPosts(){
       _placeHolderResponseState.value = PlaceHolderUiState.Loading
       viewModelScope.launch(coroutineDispatcherProvider.IO()) {
               try{
                   val response = placeHolderRepository.fetchPlaceHolderList()
                   _placeHolderResponseState.value = PlaceHolderUiState.Loaded(response)
               }
               catch (e:Exception){
                   onErrorOccurred()
               }
       }
   }

    private fun onErrorOccurred() {
        _placeHolderResponseState.value = PlaceHolderUiState.Error(
            "Some thing went wrong"
        )
    }

    sealed class PlaceHolderUiState {
        object Empty : PlaceHolderUiState()
        object Loading : PlaceHolderUiState()
        class Loaded(val data: List<PlaceHolderModel>) : PlaceHolderUiState()
        class Error(val message: String) : PlaceHolderUiState()
    }
}