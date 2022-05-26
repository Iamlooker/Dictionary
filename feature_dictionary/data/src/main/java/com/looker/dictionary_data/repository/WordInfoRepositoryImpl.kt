package com.looker.dictionary_data.repository

import com.looker.core.Resource
import com.looker.dictionary_data.local.WordInfoDao
import com.looker.dictionary_data.remote.DictionaryApi
import com.looker.dictionary_domain.model.WordInfo
import com.looker.dictionary_domain.repository.WordInfoRepository
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

			val newWordInfos = dao.getWordInfos(word).map { it.toWordInfo() }
			emit(Resource.Success(newWordInfos))
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
	}

	override fun getCachedWordInfo(): Flow<Resource<List<WordInfo>>> = flow {
		emit(Resource.Loading())

		val wordInfos = dao.getCachedWordInfos().map { it.toWordInfo() }
		emit(Resource.Success(wordInfos))
	}

	override suspend fun deleteAllWords() {
		dao.deleteAllWord(dao.getCachedWordInfos())
	}

	override suspend fun deleteWord(wordInfo: WordInfo) {
		dao.deleteWord(dao.getWordInfos(wordInfo.word))
	}
}