package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.PrepareArticleStructureUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrepareArticleStructureUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : PrepareArticleStructureUseCase {
    override fun exec(
        themeTitle: String,
        sectionsNumber: String,
        subSectionNumber: String
    ): StringBuilder {

        val articleStructureContent = openAIConfig.baseMessage().replace(
            "{themeTitle}", themeTitle
        ).replace(
            "{sectionNumber}", sectionsNumber
        ).replace(
            "{subSectionNumber}", subSectionNumber
        )

        val messages = listOf(
            AIMessage(
                role = "user",
                content = articleStructureContent
            )
        )

        val requestChatGPT = openAIRequestPort.requestChatGPT(
            openAIConfig.apiModel(),
            openAIConfig.apiKey(),
            openAIConfig.apiURL(),
            messages)

        return StringBuilder(requestChatGPT.choices[0].message.content)
    }
}