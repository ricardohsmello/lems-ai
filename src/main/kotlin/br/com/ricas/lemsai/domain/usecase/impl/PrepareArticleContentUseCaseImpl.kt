package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.PrepareArticleContentUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrepareArticleContentUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : PrepareArticleContentUseCase {

    private var mainContent: String = ""
    override fun exec(
        mainTheme: List<AIMessage>,
        totalSections: Int
    ): StringBuilder {
        val articleContent = StringBuilder()

        println("Article Structure \n\n ${mainTheme[0].content} ")

        prepareRequest(
            messages = mainTheme,
            totalSections = totalSections,
            currentIndex = 0,
            articleContent = articleContent
        )

        return articleContent
    }

    private fun prepareRequest(
        messages: List<AIMessage>,
        totalSections: Int,
        currentIndex: Int = 0,
        articleContent: StringBuilder
    ) {
        if (currentIndex >= totalSections) {
            return
        }

        if (currentIndex == 0) {
            mainContent = messages[0].content
        }

        sendRequest(
            currentIndex = currentIndex,
            articleContent = articleContent,
            totalSections = totalSections
        )
    }

    private fun sendRequest(
        currentIndex: Int,
        articleContent: StringBuilder,
        totalSections: Int
    ) {

        val messages = getFormattedMessage(
            sectionItem = (currentIndex + 1).toString()
        )

        val response = openAIRequestPort.requestChatGPT(
            openAIConfig.apiModel(),
            openAIConfig.apiKey(),
            openAIConfig.apiURL(),
            messages
        )

        buildStringBuilderResponse(currentIndex, response, articleContent)
        prepareRequest(messages, totalSections, currentIndex + 1, articleContent)
    }

    private fun buildStringBuilderResponse(
        sectionNumber: Int,
        response: OpenAIResponse,
        articleBuilder: StringBuilder
    ) {
        println("Starting creation of Section: $sectionNumber ..")
        articleBuilder.append("\n\n\n")
        articleBuilder.append(response.choices[0].message.content)
        print(articleBuilder)
        println("Section $sectionNumber finished!")
    }

    private fun getFormattedMessage(
        sectionItem: String
    ): List<AIMessage> {

        println(openAIConfig.messageDetail().replace("{sectionItem}", sectionItem))

        return listOf(
            AIMessage(
                role = "user",
                content = mainContent + openAIConfig.messageDetail().replace("{sectionItem}", sectionItem)
            )
        )

    }

}