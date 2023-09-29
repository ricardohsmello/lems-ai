package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.ArticleConfig
import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleConclusionUseCase
import br.com.ricas.lemsai.domain.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrepareArticleConclusionUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val openAIRequestUseCase: OpenAIRequestUseCase

) : PrepareArticleConclusionUseCase {

    val logger = this.logger()

    override fun exec(
        title: String,
        context: StringBuilder
    ): Section {

        logger.info("Starting Conclusion creation for theme $title")

        openAIConfig.articleConclusionContent()
            .replace(
                "{minChar}", ArticleConfig.getMinCharSection().toString()
            ).replace(
                "{maxChar}", ArticleConfig.getMaxCharSection().toString()
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
