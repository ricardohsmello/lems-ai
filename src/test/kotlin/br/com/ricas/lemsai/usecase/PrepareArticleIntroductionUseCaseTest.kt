package br.com.ricas.lemsai.usecase

import br.com.ricas.lemsai.LemsAiApplication
import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.usecase.CreateSectionUseCase
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleIntroductionUseCase
import br.com.ricas.lemsai.domain.usecase.impl.PrepareArticleIntroductionUseCaseImpl
import br.com.ricas.lemsai.fakedata.fakeSection
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [LemsAiApplication::class])
class PrepareArticleIntroductionUseCaseTest {

    private lateinit var service: PrepareArticleIntroductionUseCase

    @MockK
    private lateinit var openAIConfig: OpenAIConfig

    @MockK
    private lateinit var createSectionUseCase: CreateSectionUseCase

    @MockK
    private lateinit var openAIRequestUseCase: OpenAIRequestUseCase


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        service = PrepareArticleIntroductionUseCaseImpl(
            openAIConfig = openAIConfig,
            createSectionUseCase = createSectionUseCase,
            openAIRequestUseCase = openAIRequestUseCase
        )
    }

    @Test
    fun `should create a new Section successfully`() {
        val title = "Corinthians"

        every { openAIConfig.articleIntroductionTitle() } returns "Crie um título sem aspas para um artigo sobre Corinthians"
        every { openAIConfig.articleSectionContent() } returns "Crie um texto entre 100 e 500" +
                " caracteres sobre o seguinte titulo: A historia do Timao. " +
                " Atencao, evite plagio"
        every { createSectionUseCase.exec(any(), any(), any()) } returns fakeSection
        every { openAIRequestUseCase.requestAnswer(any()) } returns StringBuilder("This is the fake content of the section")

        val result = assertDoesNotThrow {
            service.exec(
                title = title
            )
        }

        assertEquals("Fake Section Title", result.title.toString())
        assertEquals("This is the fake content of the section.", result.content.toString())
    }
}