package com.looker.dictionary_domain.model

import androidx.annotation.Keep

@Keep
data class Meaning(
	val definitions: List<Definition>,
	val partOfSpeech: String,
)