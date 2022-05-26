package com.looker.dictionary_data.remote

import com.looker.dictionary_data.remote.dto.WordInfoDto
import retrofit2.http.GET
import retrofit2.http.Path

interface DictionaryApi {

	companion object {
		const val BASE_URL = "https://api.dictionaryapi.dev/"
	}

	@GET("/api/v2/entries/en/{word}")
	suspend fun getWordInfo(@Path("word") wordInfo: String): List<WordInfoDto>
}