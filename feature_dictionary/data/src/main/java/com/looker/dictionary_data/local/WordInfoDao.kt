package com.looker.dictionary_data.local

import androidx.room.*
import com.looker.dictionary_data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {

	@Insert(onConflict = OnConflictStrategy.REPLACE)
	suspend fun insertWordInfos(infos: List<WordInfoEntity>?)

	@Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
	suspend fun deleteWordInfos(words: List<String>)

	@Query("SELECT * FROM wordinfoentity WHERE word LIKE '%' || :word || '%'")
	suspend fun getWordInfos(word: String): List<WordInfoEntity>

	@Query("SELECT * FROM wordinfoentity")
	suspend fun getCachedWordInfos(): List<WordInfoEntity>

	@Delete
	suspend fun deleteWord(word: List<WordInfoEntity>)

	@Delete
	suspend fun deleteAllWord(words: List<WordInfoEntity>)
}