package com.looker.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import com.looker.dictionary.feature_dictonary.presentation.WordInfoViewModel
import com.looker.dictionary.ui.components.WordSearchPage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel: WordInfoViewModel = viewModel()
            val scaffoldState = rememberScaffoldState()
            LaunchedEffect(true) {
                viewModel.eventFlow.collectLatest { event ->
                    when (event) {
                        is WordInfoViewModel.UIEvents.ShowSnackBar -> {
                            scaffoldState.snackbarHostState.showSnackbar(event.message)
                        }
                    }
                }
            }
            DictionaryApp(scaffoldState = scaffoldState) { WordSearchPage() }
        }
    }
}