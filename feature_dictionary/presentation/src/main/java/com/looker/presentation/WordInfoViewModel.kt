package com.looker.presentation

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.looker.core.Resource
import com.looker.dictionary_domain.use_case.GetWordInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInfoViewModel @Inject constructor(
	private val getWordInfo: GetWordInfo,
) : ViewModel() {

	private val _searchQuery = mutableStateOf("")
	val searchQuery: State<String> = _searchQuery

	private val _state = mutableStateOf(WordInfoState())
	val state: State<WordInfoState> = _state

	private val _eventFlow = MutableSharedFlow<UIEvents>()
	val eventFlow = _eventFlow.asSharedFlow()

	private var searchJob: Job? = null

	fun onSearch(query: String) {
		_searchQuery.value = query
		searchJob?.cancel()
		searchJob = viewModelScope.launch {
			getWordInfo(query)
				.onEach { result ->
					when (result) {
						is Resource.Error -> {
							_state.value = state.value.copy(
								wordInfoItems = result.data ?: emptyList(),
								isLoading = false
							)
							_eventFlow.emit(
								UIEvents.ShowSnackBar(
									result.message ?: "Unknown Error",
									Resource.Error("", true)
								)
							)
						}
						is Resource.Loading -> {
							_state.value = state.value.copy(
								wordInfoItems = result.data ?: emptyList(),
								isLoading = true
							)
							_eventFlow.emit(
								UIEvents.ShowSnackBar(
									"Loading",
									Resource.Loading(true)
								)
							)
						}
						is Resource.Success -> {
							_state.value = state.value.copy(
								wordInfoItems = result.data ?: emptyList(),
								isLoading = false
							)
							_eventFlow.emit(
								UIEvents.ShowSnackBar(
									"Success",
									Resource.Success(false)
								)
							)
						}
					}
				}
				.launchIn(this)
		}
	}

	sealed class UIEvents {
		data class ShowSnackBar(
			val message: String = "",
			val showError: Resource<Boolean> = Resource.Loading(false)
		) :
			UIEvents()
	}
}