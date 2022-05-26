package com.looker.dictionary_data.remote.dto

import androidx.annotation.Keep
import com.looker.dictionary_domain.model.Meaning

@Keep
data class MeaningDto(
	val definitions: List<DefinitionDto>,
	val partOfSpeech: String,
) {
	fun toMeaning() = Meaning(definitions.map { it.toDefinition() }, partOfSpeech)
}