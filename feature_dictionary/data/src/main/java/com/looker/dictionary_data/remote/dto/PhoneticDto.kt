package com.looker.dictionary_data.remote.dto

import androidx.annotation.Keep

@Keep
data class PhoneticDto(
	val audio: String,
	val text: String,
)