package br.com.ricas.lemsai.domain.service.impl

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.resouces.HttpClientRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.lang.StringBuilder
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

    private val article: StringBuilder? = null

    private var mainContent: String = ""
    override fun requestChatGPT(input: String): StringBuilder? {

        val messages = listOf(
            AIMessage(role = "system", content = "You are a chat assistant that speaks about anything."),
            AIMessage(role = "user", content = "" +
                    "Por favor, crie um artigo abordando o tópico sobre $input." +
                    " \t Este artigo incluirá uma introdução, três seções distintas e uma conclusão. " +
                    " \t  IMPORTANTE: Cada secao do artigo deve ter no máximo 250  caracteres!")
        )

        val detailContentList = listOf(
            "Introdução",
            "Seção 1",
            "Seção 2",
            "Seção 3",
            "Conclusão"
        )

        val articleBuilder = StringBuilder()

        sendRequest(messages, detailContentList, 0, articleBuilder)

        return articleBuilder
    }


    private fun sendRequest(messages: List<AIMessage>, detailContentList: List<String> = emptyList(), currentIndex: Int = 0, articleBuilder: StringBuilder) {
        if (currentIndex > detailContentList.size) {
            return
        }
        val detailContent = detailContentList[currentIndex]

        println("iniciando criacao de $detailContent ..")

        val response = httpClientRequest.sendRequest(
            apiKey = apiKey,
            apiModel = apiModel,
            apiURL = apiURL,
            messages
        )

        articleBuilder.append("\n\n\n")
        println("$detailContent finalizada!")

        if (currentIndex == 0) {
            mainContent = response.choices[0].message.content
            prepareAndRequest(detailContent, detailContentList, currentIndex, articleBuilder)
        } else {
            articleBuilder.append(response.choices[0].message.content)
            prepareAndRequest(detailContent, detailContentList, currentIndex, articleBuilder)
        }

    }

    private fun prepareAndRequest(
        detailContent: String,
        detailContentList: List<String>,
        currentIndex: Int,
        articleBuilder: StringBuilder
    ) {
        val messagesNew = listOf(
            AIMessage(
                role = "user", content = "$mainContent \n\n" +
                        "No texto acima, quero um detalhamento melhor sobre a $detailContent" +
                        " com o titulo comecando com $detailContent" +
                        " \t Importante: Todo conteúdo deve ter 2800 a 3000 caracteres e falar somente sobre $detailContent."
            )
        )

        sendRequest(messagesNew, detailContentList, currentIndex + 1, articleBuilder)
    }


}


