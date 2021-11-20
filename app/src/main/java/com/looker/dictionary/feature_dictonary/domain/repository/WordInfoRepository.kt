package com.looker.dictionary.feature_dictonary.domain.repository

import com.looker.dictionary.core.util.Resource
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo
import kotlinx.coroutines.flow.Flow

interface WordInfoRepository {

    fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>>
}