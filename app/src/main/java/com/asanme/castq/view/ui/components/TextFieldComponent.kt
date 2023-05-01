package com.asanme.castq.view.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldComponent(
    value: String,
    onError: Boolean = false,
    onValueChange: (String) -> Unit,
    onErrorIcon: @Composable () -> Unit?,
    label: @Composable () -> Unit,
) {
    OutlinedTextField(
        value = value,
        modifier = Modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        label = label,
        isError = onError,
        onValueChange = onValueChange,
        trailingIcon = {
            if (onError) {
                onErrorIcon()
            }
        },
    )
}