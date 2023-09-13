package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionContentUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateArticleSectionContentUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : CreateArticleSectionContentUseCase {
    override fun exec(title: String): StringBuilder {
        val message = openAIConfig.articleSectionContent().replace(
            "{title}", title
        )

        val messages = listOf(
            AIMessage(
                role = "user",
                content = message
            )
        )

        val requestChatGPT = openAIRequestPort.requestChatGPT(
            openAIConfig.apiModel(),
            openAIConfig.apiKey(),
            openAIConfig.apiURL(),
            messages
        )

        return StringBuilder(requestChatGPT.choices[0].message.content)
    }
}