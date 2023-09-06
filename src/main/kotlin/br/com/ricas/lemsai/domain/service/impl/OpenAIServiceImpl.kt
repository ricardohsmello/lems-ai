package br.com.ricas.lemsai.domain.service.impl

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.enum.ArticleSections
import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.resouces.HttpClientRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.*

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

    @Value("\${openai.api.message.first}")
    private val firstMessage: String = ""

    @Value("\${openai.api.message.next}")
    private val nextMessage: String = ""

    private var mainContent: String = ""
    override fun createArticle(input: String): StringBuilder {
        val messages = getFormattedMessages(firstMessage, input)
        val response = requestChatGPT(messages)
        val articleBuilder = StringBuilder()

        prepareRequest(
            listOf(
                AIMessage(
                    role = "user", content = response.choices[0].message.content
                )
            ), getArticleSections(), 0, articleBuilder
        )

        return articleBuilder
    }


    private fun prepareRequest(
        messages: List<AIMessage>,
        articleSections: List<String> = emptyList(),
        currentIndex: Int = 0,
        articleBuilder: StringBuilder
    ) {
        if (currentIndex >= articleSections.size) {
            return
        }
        val articleSection = articleSections[currentIndex]

        if (currentIndex == 0) {
            mainContent = messages[0].content
        }

        sendRequest(articleSection, articleSections, currentIndex, articleBuilder)

    }

    private fun sendRequest(
        articleSection: String,
        articleSections: List<String>,
        currentIndex: Int,
        articleBuilder: StringBuilder
    ) {

        val messages = getFormattedMessages(mainContent + nextMessage, articleSection)
        val response = requestChatGPT(messages)

        buildStringBuilderResponse(articleSection, response, articleBuilder)
        prepareRequest(messages, articleSections, currentIndex + 1, articleBuilder)
    }

    private fun buildStringBuilderResponse(
        articleSection: String,
        response: OpenAIResponse,
        articleBuilder: StringBuilder
    ) {
        println("Starting $articleSection creation ..")
        articleBuilder.append("\n\n\n")
        articleBuilder.append(response.choices[0].message.content)
        println("$articleSection finished!")
    }

    private fun requestChatGPT(messages: List<AIMessage>) =
        httpClientRequest.sendRequest(
            apiKey = apiKey,
            apiModel = apiModel,
            apiURL = apiURL,
            messages
        )

    private fun getArticleSections(): List<String> {
        return listOf(
            ArticleSections.INTRODUCTION.name,
            ArticleSections.SECTION_1.name,
            ArticleSections.SECTION_2.name,
            ArticleSections.SECTION_3.name,
            ArticleSections.CONCLUSION.name
        )
    }
    private fun getFormattedMessages(content: String, input: String): List<AIMessage> {
        return listOf(
            AIMessage(
                role = "user",
                content = content.replace("{input}", input)
            )
        )

    }
}


