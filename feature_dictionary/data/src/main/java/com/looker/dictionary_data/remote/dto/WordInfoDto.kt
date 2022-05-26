package com.looker.dictionary_data.remote.dto

import androidx.annotation.Keep
import com.looker.dictionary_data.local.entity.WordInfoEntity

@Keep
data class WordInfoDto(
	val meanings: List<MeaningDto>,
	val origin: String?,
	val phonetic: String,
	val phonetics: List<PhoneticDto>,
	val word: String,
) {
	fun toWordInfoEntity() = WordInfoEntity(
		word, phonetic, origin, meanings.map { it.toMeaning() }
	)
}