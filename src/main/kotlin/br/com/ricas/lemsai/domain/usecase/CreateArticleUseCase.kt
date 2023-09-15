package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.entity.Article

interface CreateArticleUseCase {
    fun exec(
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int
    ): Article
}