package com.devepxerto.androidtvtraining.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.devepxerto.androidtvtraining.data.MoviesRepository
import com.devepxerto.androidtvtraining.domain.Movie
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MoviesRepository) : ViewModel() {

    private val _state = MutableStateFlow(UiState(emptyList()))
    val state = _state.asStateFlow()

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = UiState(repository.listPopularMovies())
        }
    }

    data class UiState(
        val movies: List<Movie>
    )
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MoviesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}