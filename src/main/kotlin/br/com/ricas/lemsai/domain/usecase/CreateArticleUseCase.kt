package br.com.ricas.lemsai.domain.usecase

interface CreateArticleUseCase {
    fun exec(
        themeTitle: String,
        sectionsNumber: String,
        subSectionNumber: String
    ): StringBuilder
}