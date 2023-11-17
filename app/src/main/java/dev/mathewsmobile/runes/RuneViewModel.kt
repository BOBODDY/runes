package dev.mathewsmobile.runes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.runes.data.RuneRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.net.URL
import javax.inject.Inject

data class UiState(
    val rune: Char,
    val name: String,
    val description: String,
    val keywords: List<String>,
    val link: URL
)

@HiltViewModel
class RuneViewModel @Inject constructor(
    private val repository: RuneRepository
): ViewModel() {

    private val _uiState = MutableStateFlow<UiState?>(null)
    val uiState = _uiState.asStateFlow()

    fun setRune(runeId: Int) {
        viewModelScope.launch {
            if (runeId == 0) {
                _uiState.emit(null)
            }
            val rune = repository.getRune(runeId)
            rune?.let {
                _uiState.value = UiState(
                    rune.rune,
                    rune.name,
                    rune.description,
                    rune.keywords,
                    URL(rune.link)
                )
            }
        }
    }
}