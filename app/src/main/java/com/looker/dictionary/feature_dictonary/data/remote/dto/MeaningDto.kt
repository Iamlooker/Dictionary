package com.looker.dictionary.feature_dictonary.data.remote.dto

import com.looker.dictionary.feature_dictonary.domain.model.Meaning

data class MeaningDto(
	val definitions: List<DefinitionDto>,
	val partOfSpeech: String,
) {
	fun toMeaning() = Meaning(definitions.map { it.toDefinition() }, partOfSpeech)
}