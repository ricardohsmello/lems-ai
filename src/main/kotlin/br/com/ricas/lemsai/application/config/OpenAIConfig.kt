package br.com.ricas.lemsai.application.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OpenAIConfig {
    @Value("\${openai.api.key}")
    private lateinit var apiKey: String

    @Value("\${openai.api.model}")
    private lateinit var apiModel: String

    @Value("\${openai.api.url}")
    private lateinit var apiURL: String

    @Value("\${openai.api.message.base}")
    private val baseMessage: String = ""

    @Value("\${openai.api.message.next}")
    private val nextMessage: String = ""

    @Value("\${openai.api.message.detail}")
    private val messageDetail: String = ""


    @Value("\${openai.api.message.main}")
    private val messageMain: String = ""

    @Bean
    fun messageMain(): String {
        return messageMain
    }

    @Bean
    fun apiKey(): String {
        return apiKey
    }

    @Bean
    fun apiModel(): String {
        return apiModel
    }

    @Bean
    fun apiURL(): String {
        return apiURL
    }

    @Bean
    fun baseMessage(): String {
        return baseMessage
    }

    @Bean
    fun nextMessage(): String {
        return nextMessage
    }

    @Bean
    fun messageDetail(): String {
        return messageDetail
    }


}
