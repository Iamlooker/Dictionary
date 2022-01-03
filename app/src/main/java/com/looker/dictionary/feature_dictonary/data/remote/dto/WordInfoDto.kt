package com.looker.dictionary.feature_dictonary.data.remote.dto

import com.looker.dictionary.feature_dictonary.data.local.entity.WordInfoEntity

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