package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateSectionUseCase
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleConclusionUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrepareArticleConclusionUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestUseCase: OpenAIRequestUseCase

) : PrepareArticleConclusionUseCase {
    override fun exec(
        title: String,
        context: StringBuilder,
        minChar: Int,
        maxChar: Int
    ): Section {
        println("Starting Conclusion creation for theme $title")

        openAIConfig.articleConclusionContent()
            .replace(
                "{minChar}", minChar.toString()
            ).replace(
                "{maxChar}", maxChar.toString()
            )
            .replace(
                "{articleMainTheme}", title
            ).replace(
                "{context}", context.toString()
            ).also { titleMessage ->

                return Section(
                    title = StringBuilder("Conclusão"),
                    isSubSection = false,
                    content = openAIRequestUseCase.requestAnswer(titleMessage)
                )
            }
    }
}