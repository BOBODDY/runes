package dev.mathewsmobile.runes

import dev.mathewsmobile.runes.data.SearchUseCase
import dev.mathewsmobile.runes.data.TestData
import org.junit.Test

class SearchUseCaseTest {

    val testObject: SearchUseCase = SearchUseCase()

    @Test
    fun `search finds matching runes by keyword`() {
        val keywords = listOf("water")

        val actual = testObject.search(TestData.runes, keywords)

        // TODO Set up assertions
    }
}