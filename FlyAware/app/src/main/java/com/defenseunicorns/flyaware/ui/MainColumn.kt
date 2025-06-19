package com.defenseunicorns.flyaware.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.font.FontWeight
import com.defenseunicorns.flyaware.R


@Composable
fun MainColumn(
    padding: PaddingValues,
    icaoList: List<String>,
    onDeleteConfirmed: (String) -> Unit
) {
    if (icaoList.isEmpty()) {
        Text(
            text = stringResource(R.string.start_message_empty),
            modifier = Modifier.fillMaxWidth().padding(padding),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    }

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