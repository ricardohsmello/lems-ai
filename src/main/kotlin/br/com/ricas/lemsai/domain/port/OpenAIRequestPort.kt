package br.com.ricas.lemsai.domain.port

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIResponse

interface OpenAIRequestPort {
    fun requestChatGPT(
        apiModel: String,
        apiKey: String,
        apiURL: String,
        messages: List<AIMessage>
    ): OpenAIResponse
}
