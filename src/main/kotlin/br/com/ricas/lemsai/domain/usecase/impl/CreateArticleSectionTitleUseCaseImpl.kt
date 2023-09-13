package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionTitleUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateArticleSectionTitleUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : CreateArticleSectionTitleUseCase {
    override fun exec(sectionTitle: String, articleTheme: String): String {
        val message = openAIConfig.articleSectionTitle().replace(
            "{articleSectionTitle}", sectionTitle
        ).replace(
            "{articleMainTheme}", articleTheme
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

        return requestChatGPT.choices[0].message.content
    }
}