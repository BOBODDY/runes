package dev.mathewsmobile.runes.data

import android.content.Context
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.mathewsmobile.runes.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStreamReader
import javax.inject.Inject

class RuneDataSource @Inject constructor(
    @ApplicationContext private val context: Context,
) {

    suspend fun getRunes(): Runes? {
        val runes = withContext(Dispatchers.IO) {
            val runesResource = context.resources.openRawResource(R.raw.runes)
            val reader = InputStreamReader(runesResource)
            val buffer = CharArray(1024)
            var length = reader.read(buffer)
            val stringBuilder = StringBuilder()
            while (length != -1) {
                stringBuilder.appendRange(buffer, 0, length)
                length = reader.read(buffer)
            }
            val runesString = stringBuilder.toString()

            val moshi: Moshi = Moshi.Builder()
                .addLast(KotlinJsonAdapterFactory())
                .build()

            val jsonAdapter: JsonAdapter<Runes> = moshi.adapter(Runes::class.java)

            return@withContext jsonAdapter.fromJson(runesString)
        }

        return runes
    }
}