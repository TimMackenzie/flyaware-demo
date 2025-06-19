package com.defenseunicorns.flyaware.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


@Composable
fun MainColumn(
    padding: PaddingValues,
    icaoList: List<String>,
    onDeleteConfirmed: (String) -> Unit
) {
    LazyColumn (
        modifier = Modifier.padding(padding)
    ){
        items(icaoList) { icao ->
            IcaoCard(
                icao = icao,
                onDeleteConfirmed
            )
        }
    }
}