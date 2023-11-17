package dev.mathewsmobile.runes.data

object TestData {
    val runes = listOf(
        Rune(
            id = 1,
            rune = 'ᚠ',
            name = "Fehu",
            description = "ᚠ Fehu marks the beginning of the runic alphabet and represents wealth, prosperity, and abundance. Its shape resembles cattle, symbolizing the importance of material possessions in the Germanic cultures where the runes originated. In runelore, Fehu signifies the pursuit of material wealth, the importance of hard work and perseverance, and the generosity that comes with abundance. It encourages us to manage our resources wisely, to share our wealth with others, and to appreciate the abundance that surrounds us.",
            keywords = listOf("wealth", "livestock", "fehu", "younger", "futhark", "freya"),
            link = "https://en.wikipedia.org/wiki/Fehu"
        ),
        Rune(
            id = 2,
            rune = 'ᚢ',
            name = "Uruz",
            description = "The reconstructed Proto-Germanic name of the Elder Futhark u rune ᚢ is *Ūruz meaning \"wild ox\"[1] or *Ūrą \"water\". It may have been derived from the Raetic alphabet character u as it is similar in both shape and sound value.",
            keywords = listOf("wild ox", "water", "uruz", "ur", "younger", "futhark", "freya"),
            link = "https://en.wikipedia.org/wiki/Ur_(rune)"
        )
    )
}