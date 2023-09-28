package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.application.config.OpenAIConfig
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateSectionUseCase
import br.com.ricas.lemsai.domain.usecase.OpenAIRequestUseCase
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CreateSectionUseCaseImpl(
    @Autowired private val openAIConfig: OpenAIConfig,
    val openAIRequestUseCase: OpenAIRequestUseCase
) : CreateSectionUseCase {

    override fun exec(
        titlePropertyMessage: String,
        contentPropertyMessage: String,
        isSubSection: Boolean

    ): Section {

        return Section(
            title = openAIRequestUseCase.requestAnswer(
                content = titlePropertyMessage
            ),
            isSubSection = isSubSection,
            content = openAIRequestUseCase.requestAnswer(
                content = contentPropertyMessage
            )
        )

    }
}
