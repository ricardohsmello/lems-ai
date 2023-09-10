package br.com.ricas.lemsai.infrastructure.openai

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIRequest
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI

@Component
class OpenAIRequestPortImpl(
    private val restTemplate: RestTemplate
) : OpenAIRequestPort {

    override fun requestChatGPT(
        apiModel: String,
        apiKey: String,
        apiURL: String,
        messages: List<AIMessage>
    ): OpenAIResponse {
        val headers = createHeaders(apiKey)
        val body = createRequestBody(headers, apiURL, apiModel, messages)

        val responseEntity = sendHttpRequest(body)
        return getResponseBody(responseEntity)
    }

    private fun createHeaders(apiKey: String): HttpHeaders {
        val headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authorization", "Bearer $apiKey")
        return headers
    }

    private fun createRequestBody(
        headers: HttpHeaders,
        apiURL: String,
        apiModel: String,
        messages: List<AIMessage>
    ): RequestEntity<OpenAIRequest> {
        val body = OpenAIRequest(model = apiModel, messages = messages)
        return RequestEntity.post(URI(apiURL)).headers(headers).body(body)
    }

    private fun sendHttpRequest(body: RequestEntity<OpenAIRequest>): ResponseEntity<OpenAIResponse> {
        return restTemplate.exchange(body, OpenAIResponse::class.java)
    }

    private fun getResponseBody(responseEntity: ResponseEntity<OpenAIResponse>): OpenAIResponse {
        return responseEntity.body ?: throw RuntimeException("Failed to get OpenAI response")
    }

}
