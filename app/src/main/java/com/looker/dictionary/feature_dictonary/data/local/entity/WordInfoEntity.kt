package com.looker.dictionary.feature_dictonary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.looker.dictionary.feature_dictonary.domain.model.Meaning
import com.looker.dictionary.feature_dictonary.domain.model.WordInfo

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