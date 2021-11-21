package com.looker.dictionary

import android.app.Application
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.imePadding
import com.google.accompanist.insets.statusBarsPadding
import com.looker.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DictionaryApp : Application()

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DictionaryApp(
    modifier: Modifier = Modifier,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    content: @Composable (PaddingValues) -> Unit,
) {
    DictionaryTheme {
        ProvideWindowInsets {
            Scaffold(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.background)
                    .statusBarsPadding()
                    .imePadding(),
                scaffoldState = scaffoldState,
                content = content
            )
        }
    }
}