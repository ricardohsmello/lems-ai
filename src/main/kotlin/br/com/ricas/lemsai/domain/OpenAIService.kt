package br.com.ricas.lemsai.domain

import br.com.ricas.lemsai.resouces.OpenAIResponse

interface OpenAIService {
    fun exec(question: String): OpenAIResponse
}