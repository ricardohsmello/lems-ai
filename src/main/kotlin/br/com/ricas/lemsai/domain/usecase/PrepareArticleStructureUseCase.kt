package br.com.ricas.lemsai.domain.usecase

interface PrepareArticleStructureUseCase {
    fun exec(
        themeTitle: String,
        sectionsNumber: String,
        subSectionNumber: String
    ): StringBuilder

}