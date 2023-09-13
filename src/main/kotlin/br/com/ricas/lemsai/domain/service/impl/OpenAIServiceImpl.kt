package br.com.ricas.lemsai.domain.service.impl

import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.infrastructure.openai.OpenAIRequestPortImpl
import org.springframework.stereotype.Service

@Service
class OpenAIServiceImpl(
    private val openAIRequestPortImpl: OpenAIRequestPortImpl

) : OpenAIService {
//
//    @Value("\${openai.api.message.first}")
//    private val firstMessage: String = ""

//    @Value("\${openai.api.message.next}")
//    private val nextMessage: String = ""
//
//    private var mainContent: String = ""
    override fun createArticle(input: String): StringBuilder {
//        val messages = getFormattedMessages(firstMessage, input)
////        val response = requestChatGPT(messages)
//        val articleBuilder = StringBuilder()
////
////        prepareRequest(
////            listOf(
////                AIMessage(
////                    role = "user", content = response.choices[0].message.content
////                )
////            ), getArticleSections(), 0, articleBuilder
////        )
//
//        return articleBuilder
//    }
//
//
//    private fun prepareRequest(
//        messages: List<AIMessage>,
//        articleSections: List<String> = emptyList(),
//        currentIndex: Int = 0,
//        articleBuilder: StringBuilder
//    ) {
//        if (currentIndex >= articleSections.size) {
//            return
//        }
//        val articleSection = articleSections[currentIndex]
//
//        if (currentIndex == 0) {
//            mainContent = messages[0].content
//        }
//
//        sendRequest(articleSection, articleSections, currentIndex, articleBuilder)
//
//    }
//
//    private fun sendRequest(
//        articleSection: String,
//        articleSections: List<String>,
//        currentIndex: Int,
//        articleBuilder: StringBuilder
//    ) {
//
////        val messages = getFormattedMessages(mainContent + nextMessage, articleSection)
////        val response = requestChatGPT(messages)
////
////        buildStringBuilderResponse(articleSection, response, articleBuilder)
////        prepareRequest(messages, articleSections, currentIndex + 1, articleBuilder)
//    }
//
//    private fun buildStringBuilderResponse(
//        articleSection: String,
//        response: OpenAIResponse,
//        articleBuilder: StringBuilder
//    ) {
//        println("Starting $articleSection creation ..")
//        articleBuilder.append("\n\n\n")
//        articleBuilder.append(response.choices[0].message.content)
//        println("$articleSection finished!")
//    }
//
////    private fun requestChatGPT(messages: List<AIMessage>) =
////        openAIRequestPortImpl.sendRequest(
////            messages
////        )
//
//    private fun getArticleSections(): List<String> {
//        return listOf(
//            ArticleSections.INTRODUCTION.description,
//            ArticleSections.SECTION_1.description,
//            ArticleSections.SECTION_2.description,
//            ArticleSections.SECTION_3.description,
//            ArticleSections.CONCLUSION.description
//        )
//    }
//    private fun getFormattedMessages(content: String, input: String): List<AIMessage> {
//        return listOf(
//            AIMessage(
//                role = "user",
//                content = content.replace("{input}", input)
//            )
//        )
//
//    }

    return StringBuilder("")
}
}


