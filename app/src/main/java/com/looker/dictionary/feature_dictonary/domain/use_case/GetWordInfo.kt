package com.looker.dictionary.feature_dictonary.domain.use_case

import com.looker.dictionary.core.util.Resource
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo
import com.looker.dictionary.feature_dictonary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow

class GetWordInfo(private val repository: WordInfoRepository) {

	operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
		if (word.isBlank()) {
			return cachedWords()
		}
		return repository.getWordInfo(word)
	}

	fun cachedWords(): Flow<Resource<List<WordInfo>>> = repository.getCachedWordInfo()
}