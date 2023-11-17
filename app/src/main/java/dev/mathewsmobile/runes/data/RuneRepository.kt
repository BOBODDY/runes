package dev.mathewsmobile.runes.data

import javax.inject.Inject

class RuneRepository @Inject constructor(
    private val runeDataSource: RuneDataSource,
) {

    private var runes: Runes? = null

    suspend fun getRunes(): Runes? {
        fetchRunes()
        return runes
    }

    suspend fun getRune(runeId: Int): Rune? {
        fetchRunes()
        return runes?.runes?.firstOrNull { it.id == runeId }
    }

    private suspend fun fetchRunes() {
        if (runes == null) {
            runes = runeDataSource.getRunes()
        }
    }
}