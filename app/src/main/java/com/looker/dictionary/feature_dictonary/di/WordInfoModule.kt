package com.looker.dictionary.feature_dictonary.di

import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.looker.dictionary.feature_dictonary.data.local.Converters
import com.looker.dictionary.feature_dictonary.data.local.WordInfoDatabase
import com.looker.dictionary.feature_dictonary.data.remote.DictionaryApi
import com.looker.dictionary.feature_dictonary.data.repository.WordInfoRepositoryImpl
import com.looker.dictionary.feature_dictonary.data.util.GsonParser
import com.looker.dictionary.feature_dictonary.domain.repository.WordInfoRepository
import com.looker.dictionary.feature_dictonary.domain.use_case.GetWordInfo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object WordInfoModule {

	@Provides
	@Singleton
	fun provideGetWordInfoUseCase(repository: WordInfoRepository): GetWordInfo =
		GetWordInfo(repository)

	@Provides
	@Singleton
	fun provideWordInfoRepository(
		db: WordInfoDatabase,
		api: DictionaryApi,
	): WordInfoRepository = WordInfoRepositoryImpl(api, db.dao)

	@Provides
	@Singleton
	fun provideWordInfoDatabase(
		@ApplicationContext context: Context,
	): WordInfoDatabase =
		Room.databaseBuilder(context, WordInfoDatabase::class.java, "word_db")
			.addTypeConverter(Converters(GsonParser(Gson())))
			.build()

	@Provides
	@Singleton
	fun provideDictionaryApi(): DictionaryApi = Retrofit.Builder()
		.baseUrl(DictionaryApi.BASE_URL)
		.addConverterFactory(GsonConverterFactory.create())
		.build()
		.create(DictionaryApi::class.java)
}