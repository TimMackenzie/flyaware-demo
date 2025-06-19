package com.defenseunicorns.flyaware.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.defenseunicorns.flyaware.R

@Composable
fun IcaoInputDialog(
    showDialog: Boolean,
    icaoInput: String,
    onInputChange: (String) -> Unit,
    onConfirm: (String) -> Unit,
    onDismiss: () -> Unit,
    errorMessage: Boolean = false
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = { Text(stringResource(R.string.dialog_label_enter_icao_code)) },
            text = {
                OutlinedTextField(
                    value = icaoInput,
                    onValueChange = { if (it.length <= 4) onInputChange(it) },
                    label = { Text(stringResource(R.string.dialog_prefill_icao)) },
                    singleLine = true,
                    isError = errorMessage
                )
                if (errorMessage) { // TBR, not currently shown
                    Text(
                        text = stringResource(R.string.error_invalid_icao_length),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = { onConfirm(icaoInput.uppercase()) },
                    enabled = icaoInput.length == 4
                ) {
                    Text(stringResource(R.string.dialog_confirm))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(stringResource(R.string.dialog_cancel))
                }
            }
        )
    }
}