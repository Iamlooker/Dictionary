package com.looker.dictionary.feature_dictonary.domain.model

data class Meaning(
	val definitions: List<Definition>,
	val partOfSpeech: String,
)