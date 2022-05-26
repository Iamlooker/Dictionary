package com.looker.dictionary_data.remote.dto

import androidx.annotation.Keep
import com.looker.dictionary_domain.model.Definition

@Keep
data class DefinitionDto(
	val antonyms: List<String>,
	val definition: String,
	val example: String?,
	val synonyms: List<String>,
) {
	fun toDefinition() = Definition(antonyms, definition, example, synonyms)
}