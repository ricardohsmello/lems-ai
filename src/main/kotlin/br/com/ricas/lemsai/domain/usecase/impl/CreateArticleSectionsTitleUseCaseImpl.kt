package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionsTitleUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateArticleSectionsTitleUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : CreateArticleSectionsTitleUseCase {
    override fun exec(
        articleTheme: String,
        sectionTitle: String,
        context: StringBuilder
    ): StringBuilder {

        val messages = listOf(
            AIMessage(
                role = "user",
                content = context.toString() + "\n\n"+ openAIConfig.messageMain()
                    .replace("{articleTheme}", articleTheme)
                    .replace("{sectionTitle}", sectionTitle)
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