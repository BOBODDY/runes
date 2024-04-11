package dev.mathewsmobile.runes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mathewsmobile.runes.data.Rune
import dev.mathewsmobile.runes.data.RuneRepository
import dev.mathewsmobile.runes.data.Runes
import dev.mathewsmobile.runes.data.SearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class RuneListUiState(
    val runes: Runes = Runes(emptyList()),
)

@HiltViewModel
class RunesListViewModel @Inject constructor(
    private val runeRepository: RuneRepository,
    private val searchUseCase: SearchUseCase,
) : ViewModel() {

    private var _uiStateFlow = MutableStateFlow(RuneListUiState())
    val uiStateFlow = _uiStateFlow.asStateFlow()

    private var _runeStateFlow = MutableStateFlow(Runes(emptyList()))
    val stateFlow = _runeStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val runes = runeRepository.getRunes()
            runes?.let {
                _runeStateFlow.emit(runes)
                _uiStateFlow.emit(_uiStateFlow.value.copy(runes = it))
            }
        }
    }

    fun search(searchPhrase: String) {
        viewModelScope.launch {
            val runes = runeRepository.getRunes()
            if (searchPhrase.isEmpty()) {
                runes?.let { _runeStateFlow.emit(it) }
                return@launch
            }
            val keywords = searchPhrase.split(" ")

            val filteredRunes = searchUseCase.search(runes?.runes ?: emptyList(), keywords)
            _runeStateFlow.emit(Runes(filteredRunes))
        }
    }

    suspend fun getRandomRune(): Rune? {
        val runes = runeRepository.getRunes()
        return runes?.runes?.random()
    }
}