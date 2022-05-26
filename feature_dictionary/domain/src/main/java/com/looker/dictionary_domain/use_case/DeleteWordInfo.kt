package com.looker.dictionary_domain.use_case

import com.looker.dictionary_domain.model.WordInfo
import com.looker.dictionary_domain.repository.WordInfoRepository

class DeleteWordInfo(private val repository: WordInfoRepository) {
	suspend operator fun invoke(word: WordInfo) {
		repository.deleteWord(word)
	}

	suspend fun deleteAll() {
		repository.deleteAllWords()
	}
}