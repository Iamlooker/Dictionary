package com.looker.dictionary.data.remote.dto

import androidx.annotation.Keep
import com.looker.dictionary.domain.model.Meaning

@Keep
data class MeaningDto(
	val definitions: List<DefinitionDto>,
	val partOfSpeech: String,
) {
	fun toMeaning() = Meaning(definitions.map { it.toDefinition() }, partOfSpeech)
}