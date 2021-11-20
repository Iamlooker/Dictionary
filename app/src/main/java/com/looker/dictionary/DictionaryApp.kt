package com.looker.dictionary

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.looker.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application()

@Composable
fun DictionaryApp(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (PaddingValues) -> Unit,
) {
    DictionaryTheme {
        Scaffold(
            modifier = modifier.background(MaterialTheme.colors.background),
            scaffoldState = scaffoldState,
            content = content
        )
    }
}