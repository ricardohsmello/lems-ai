package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.entity.Section

interface PrepareArticleIntroductionUseCase {
    fun exec(
        title: String
    ): Section
}
