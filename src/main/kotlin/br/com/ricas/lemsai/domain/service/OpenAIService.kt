package br.com.ricas.lemsai.domain.service

import br.com.ricas.lemsai.domain.ai.OpenAIResponse

interface OpenAIService {
    fun requestChatGPT(input: String): OpenAIResponse
}