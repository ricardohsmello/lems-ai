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

    @Value("\${openai.api.message.articleSectionTitle}")
    private val articleSectionTitle: String = ""


    @Value("\${openai.api.message.articleSubSectionTitle}")
    private val articleSubSectionTitle: String = ""


    @Value("\${openai.api.message.articleSectionContent}")
    private val articleSectionContent: String = ""

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
    fun articleSectionTitle(): String {
        return articleSectionTitle
    }

    @Bean
    fun articleSubSectionTitle(): String {
        return articleSubSectionTitle
    }
    @Bean
    fun articleSectionContent(): String {
        return articleSectionContent
    }
}
