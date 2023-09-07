package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.ai.AIMessage

interface PrepareArticleContentUseCase {
    fun exec(
        mainTheme: List<AIMessage>,
        totalSections: Int
    ): StringBuilder
}