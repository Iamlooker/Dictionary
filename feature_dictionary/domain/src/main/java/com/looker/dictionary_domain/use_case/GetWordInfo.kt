package com.looker.dictionary_domain.use_case

import com.looker.core.Resource
import com.looker.dictionary_domain.model.WordInfo
import com.looker.dictionary_domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow

class GetWordInfo(private val repository: WordInfoRepository) {

	operator fun invoke(word: String): Flow<Resource<List<WordInfo>>> {
		if (word.isBlank()) {
			return cachedWords()
		}
		return repository.getWordInfo(word)
	}

	private fun cachedWords(): Flow<Resource<List<WordInfo>>> = repository.getCachedWordInfo()
}