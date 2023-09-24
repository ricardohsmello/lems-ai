package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.port.OpenAIRequestPort
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class OpenAIRequestUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestPort: OpenAIRequestPort
) : OpenAIRequestUseCase{
    override fun requestAnswer(content: String): StringBuilder {
        val messages = listOf(
            AIMessage(
                role = "user",
                content = content
            )
        )


        openAIRequestPort.requestChatGPT(
            openAIConfig.apiModel(),
            openAIConfig.apiKey(),
            openAIConfig.apiURL(),
            messages
        ).also {
           return StringBuilder(it.choices[0].message.content)
        }
    }
}