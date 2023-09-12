package br.com.ricas.lemsai.domain.entity

data class Article(
    val title: String,
    val sections: List<Section>
) {
}

data class Section(
    val numeration: Int,
    val title: String,
    val subSection: List<SubSection>
)

data class SubSection(
    val numeration: Int,
    val title: String
)