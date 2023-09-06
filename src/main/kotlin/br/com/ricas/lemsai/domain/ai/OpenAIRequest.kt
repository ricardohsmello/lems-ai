package br.com.ricas.lemsai.domain.ai

data class OpenAIRequest(
    val model: String,
    val messages: List<AIMessage>
)

data class AIMessage(
    val role: String,
    val content: String
)