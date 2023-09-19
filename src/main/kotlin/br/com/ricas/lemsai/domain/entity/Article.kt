package br.com.ricas.lemsai.domain.entity

data class Article(
    val durationTime: String,
    val title: String,
    val sections: List<Section>?
)
data class Section(
    val title: StringBuilder,
    val isSubSection: Boolean,
    val content: StringBuilder
)
