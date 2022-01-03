package com.looker.dictionary.feature_dictonary.data.remote.dto

import com.looker.dictionary.feature_dictonary.domain.model.Definition

data class DefinitionDto(
	val antonyms: List<String>,
	val definition: String,
	val example: String?,
	val synonyms: List<String>,
) {
	fun toDefinition() = Definition(antonyms, definition, example, synonyms)
}