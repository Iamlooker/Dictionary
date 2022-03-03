package com.looker.dictionary.data.local.entity

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.looker.dictionary.domain.model.Meaning
import com.looker.dictionary.domain.model.WordInfo

@Keep
@Entity
data class WordInfoEntity(
	val word: String,
	val phonetic: String?,
	val origin: String?,
	val meaning: List<Meaning>,
	@PrimaryKey val id: Int? = null,
) {
	fun toWordInfo() = WordInfo(meaning, origin, phonetic, word)
}