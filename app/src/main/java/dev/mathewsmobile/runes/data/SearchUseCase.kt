package dev.mathewsmobile.runes.data

import javax.inject.Inject

class SearchUseCase @Inject constructor() {

    fun search(runes: List<Rune>, searchTerms: List<String>): List<Rune> {
        return runes.filter { rune ->
            rune.keywords.any { keyword ->
                searchTerms.any { searchTerm ->
                    keyword.contains(searchTerm, ignoreCase = true)
                }
            }
        }
    }
}