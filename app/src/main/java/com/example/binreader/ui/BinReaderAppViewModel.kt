package com.example.binreader.ui

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.binreader.AbstractApplication
import com.example.binreader.data.BinInfoRepository
import com.example.binreader.data.BinSearchHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BinReaderAppViewModel(
    private val binSearchHistoryRepository: BinSearchHistoryRepository,
    private val binInfoRepository: BinInfoRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(BinReaderAppUiState())
    private val _searchHistoryFlow = binSearchHistoryRepository.getBinSearchHistoryFlow()
    val uiState = _uiState.asStateFlow()


    fun fetchSearchHistory() {
        viewModelScope.launch {
            _searchHistoryFlow.collectLatest {
                latest -> _uiState.update { it.copy(searchHistory = latest, historyScreenLoadingState = ScreenLoadingState.Successful) }
            }
        }
    }


    fun loadInfo(bin: String) {
        viewModelScope.launch {
            try {
                val result = binInfoRepository.getBinInfo(bin.filter { it != ' ' })?.copy(bin)
                _uiState.update {
                    it.copy(
                        searchResult = result,
                        resultsScreenLoadingState = ScreenLoadingState.Successful
                    )
                }
                if (result != null) {
                    _uiState.update { it.copy(historyScreenLoadingState = ScreenLoadingState.Pending) }
                    binSearchHistoryRepository.addBinSearchToHistory(result)
                }
            } catch(e: Exception) {
                _uiState.update {
                    it.copy(resultsScreenLoadingState = ScreenLoadingState.Error)
                }
            }
        }
    }

    companion object {
        // TODO: Dagger DI
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as AbstractApplication
                BinReaderAppViewModel(application.container.binSearchHistoryRepository, application.container.binInfoRepository)
            }
        }
    }
}