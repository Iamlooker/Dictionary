package com.looker.dictionary.feature_dictonary.data.repository

import com.looker.dictionary.core.util.Resource
import com.looker.dictionary.feature_dictonary.data.local.WordInfoDao
import com.looker.dictionary.feature_dictonary.data.remote.DictionaryApi
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo
import com.looker.dictionary.feature_dictonary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(
	private val api: DictionaryApi,
	private val dao: WordInfoDao,
) : WordInfoRepository {
	override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
		emit(Resource.Loading())

		val wordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
		emit(Resource.Loading(wordInfos))

		try {
			val remoteWordInfo = api.getWordInfo(word)
			dao.deleteWordInfos(remoteWordInfo.map { it.word })
			dao.insertWordInfos(remoteWordInfo.map { it.toWordInfoEntity() })
		} catch (e: HttpException) {
			emit(
				Resource.Error(
					message = "Something Went Wrong",
					data = wordInfos
				)
			)
		} catch (e: IOException) {
			emit(
				Resource.Error(
					message = "Couldn't reach server",
					data = wordInfos
				)
			)
		}

		val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
		emit(Resource.Success(newWordInfos))
	}

	override fun getCachedWordInfo(): Flow<Resource<List<WordInfo>>> = flow {
		emit(Resource.Loading())

		val wordInfos = dao.getCachedWordInfos().map { it.toWordInfo() }
		emit(Resource.Success(wordInfos))
	}
}