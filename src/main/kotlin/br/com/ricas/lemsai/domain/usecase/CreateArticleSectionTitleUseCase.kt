package br.com.ricas.lemsai.domain.usecase

interface CreateArticleSectionTitleUseCase {
    fun exec(sectionTitle: String, articleTheme: String): String
}