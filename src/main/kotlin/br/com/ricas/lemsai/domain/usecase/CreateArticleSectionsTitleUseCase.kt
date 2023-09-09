package br.com.ricas.lemsai.domain.usecase

interface CreateArticleSectionsTitleUseCase {
    fun exec(
        articleTheme: String,
        sectionTitle: String,
        context: StringBuilder
    ): StringBuilder
}