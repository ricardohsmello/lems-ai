package br.com.ricas.lemsai.domain.service.impl

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.resouces.HttpClientRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OpenAIServiceImpl(
    private val httpClientRequest: HttpClientRequest

) : OpenAIService {

    @Value("\${openai.api.key}")
    private val apiKey: String = ""

    @Value("\${openai.api.model}")
    private val apiModel: String = ""

    @Value("\${openai.api.url}")
    private val apiURL: String = ""

    override fun requestChatGPT(input: String): OpenAIResponse {

        val messages = listOf(
            AIMessage(role = "system", content = "You are a chat assistant that speaks about anything."),
            AIMessage(role = "user", content = input)
        )

        return httpClientRequest.sendRequest(
            apiKey = apiKey,
            apiModel = apiModel,
            apiURL = apiURL,
            messages
        )
    }

}