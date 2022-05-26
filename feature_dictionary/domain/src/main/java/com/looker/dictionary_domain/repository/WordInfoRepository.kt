package com.looker.dictionary_domain.repository

import com.looker.core.Resource
import com.looker.dictionary_domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

	fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

	fun getCachedWordInfo(): Flow<Resource<List<WordInfo>>>

	suspend fun deleteAllWords()

	suspend fun deleteWord(wordInfo: WordInfo)
}