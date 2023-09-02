package br.com.ricas.lemsai.resouces

import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.RequestEntity
import java.net.URI

class HttpRequestFactory {
    private lateinit var headers: HttpHeaders
    private lateinit var body: OpenAIRequest

    fun defineHeader(apiKey: String): HttpRequestFactory {
        headers = HttpHeaders()
        headers.contentType = MediaType.APPLICATION_JSON
        headers.set("Authorization", "Bearer $apiKey")
        return this
    }

    fun defineBody(question: String): HttpRequestFactory {
        body = OpenAIRequest(
            model = "gpt-3.5-turbo",
            messages = listOf(
                AIMessage(role = "system", content = "You are a chat assistant that speaks about anything."),
                AIMessage(role = "user", content = question)
            )
        )
        return this
    }

    fun callAPI(url: String): RequestEntity<OpenAIRequest> {
        return RequestEntity.post(URI("https://api.openai.com/v1/chat/completions"))
            .headers(headers)
            .body(body)
    }
}