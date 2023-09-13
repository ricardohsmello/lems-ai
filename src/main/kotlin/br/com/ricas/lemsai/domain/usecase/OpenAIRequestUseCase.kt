package br.com.ricas.lemsai.domain.usecase

interface OpenAIRequestUseCase {
    fun requestAnswer(
        content: String
    ): StringBuilder
}