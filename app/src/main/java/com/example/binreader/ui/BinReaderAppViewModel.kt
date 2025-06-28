package com.example.binreader.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.binreader.MockApplication
import com.example.binreader.data.BinInfoRepository
import com.example.binreader.data.BinSearchHistoryRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BinReaderAppViewModel(
    private val binSearchHistoryRepository: BinSearchHistoryRepository,
    private val binInfoRepository: BinInfoRepository,
): ViewModel() {

    private val _uiState = MutableStateFlow(BinReaderAppUiState())
    val uiState = _uiState.asStateFlow()

    // TODO: handle errors
    fun loadHistory() {
        viewModelScope.launch {
            val result = binSearchHistoryRepository.getBinSearchHistory()
            _uiState.update {
                it.copy(searchHistory = result)
            }
        }
    }

    // TODO: handle errors, write successful result in history
    fun loadInfo(binNumber: String) {
        viewModelScope.launch {
            val result = binInfoRepository.getBinInfo(binNumber)
            _uiState.update {
                it.copy(searchResult = result)
            }
        }
    }

    companion object {
        // TODO: Dagger DI
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MockApplication
                BinReaderAppViewModel(application.container.binSearchHistoryRepository, application.container.binInfoRepository)
            }
        }
    }
}