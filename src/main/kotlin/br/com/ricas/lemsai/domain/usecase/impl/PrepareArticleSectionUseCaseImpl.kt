package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.ArticleConfig
import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateSectionUseCase
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleSectionUseCase
import br.com.ricas.lemsai.domain.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PrepareArticleSectionUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    private val createSectionUseCase: CreateSectionUseCase,
    private val openAIRequestUseCase: OpenAIRequestUseCase,
) : PrepareArticleSectionUseCase {

    val logger = this.logger()
    override fun exec(
        isSubSection: Boolean,
        sectionTitle: String,
        articleTheme: String,
        titleAlreadyCreated: StringBuilder
    ): Section {

        logger.info("Starting $sectionTitle creation")

        openAIConfig.articleSectionTitle().replace(
            "{articleSectionTitle}", sectionTitle
        ).replace(
            "{articleMainTheme}", articleTheme
        ).replace(
            "{titleAlreadyCreated}", titleAlreadyCreated.toString()
        ).also { titleMessage ->
            return createSectionUseCase.exec(
                titlePropertyMessage = titleMessage,
                contentPropertyMessage = openAIConfig.articleSectionContent()
                    .replace(
                        "{minChar}", ArticleConfig.getMinCharSection().toString()
                    ).replace(
                        "{maxChar}", ArticleConfig.getMaxCharSection().toString()
                    )
                    .replace(
                        "{content}", openAIRequestUseCase.requestAnswer(titleMessage).toString()
                    ),
                isSubSection = isSubSection
            )
        }
    }
}