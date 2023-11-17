package dev.mathewsmobile.runes.data.adapter

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.net.URL

class UrlAdapter {

    @FromJson fun fromJson(url: String): URL {
        return URL(url)
    }

    @ToJson fun toJson(url: URL): String {
        return url.toString()
    }
}