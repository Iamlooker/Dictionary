package com.looker.dictionary.domain.repository

import com.looker.dictionary.Resource
import com.looker.dictionary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

	fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>

	fun getCachedWordInfo(): Flow<Resource<List<WordInfo>>>
}