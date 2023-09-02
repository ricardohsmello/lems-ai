package br.com.ricas.lemsai.resouces

data class OpenAIResponse(
    val id: String,
    val `object`: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage
)

data class Usage(
    val prompt_tokens: Long,
    val completion_tokens: Long,
    val total_tokens: Long
)

data class Choice(
    val index: Int,
    val message: Message,
    val finish_reason: String
)

data class Message(
    val role: String,
    val content: String
)
