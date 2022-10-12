package com.compose.placeholderlist.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.items
import com.compose.placeholderlist.datasource.models.PlaceHolderModel
import com.compose.placeholderlist.viewmodel.PlaceHolderViewModel


@Composable
fun initPlaceHolderList(viewModel: PlaceHolderViewModel = androidx.lifecycle.viewmodel.compose.viewModel()){
    when(val state = viewModel.placeHolderResponseState.collectAsState().value){
       is PlaceHolderViewModel.PlaceHolderUiState.Loading -> {
           Column(
               modifier = Modifier.fillMaxSize(),
               verticalArrangement = Arrangement.Center,
               horizontalAlignment = Alignment.CenterHorizontally
           ) {
               CircularProgressIndicator()
           }
       }

       is PlaceHolderViewModel.PlaceHolderUiState.Loaded -> {
           showPlaceHoldeList(state.data)
       }

       is PlaceHolderViewModel.PlaceHolderUiState.Empty -> {
           Text(
               text = "No data available",
               modifier = Modifier.padding(16.dp)
           )
       }
    }
}

@Composable fun showPlaceHoldeList(
    placeHolderModelList: List<PlaceHolderModel>, modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
       items(items = placeHolderModelList, key = {placeHolderModel->placeHolderModel.id})
       { placeHolderModel ->
            PlaceHolderItem(placeHolderModel = placeHolderModel)
        }
    }
}

@Composable fun PlaceHolderItem(
    placeHolderModel: PlaceHolderModel, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(start = 16.dp), text = placeHolderModel.title
        )
    }
}
