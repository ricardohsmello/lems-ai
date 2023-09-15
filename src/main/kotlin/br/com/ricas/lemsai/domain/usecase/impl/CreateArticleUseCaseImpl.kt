package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleConclusionUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleIntroductionUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleSectionUseCase
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import br.com.ricas.lemsai.domain.util.logger
import org.springframework.stereotype.Service

@Service
class CreateArticleUseCaseImpl(
    private val timeExecutionControl: TimeExecutionControl,
    private val prepareArticleIntroductionUseCase: PrepareArticleIntroductionUseCase,
    private val prepareArticleSectionUseCase: PrepareArticleSectionUseCase,
    private val prepareArticleConclusionUseCase: PrepareArticleConclusionUseCase
) : CreateArticleUseCase {

    val logger = this.logger()
    override fun exec(
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int
    ): Article {

        logger.info(
            """
            Article settings: 
                Name $articleTheme
                Total sections: $sectionNumber
                Total subSections: $subSectionNumber
            """.trimIndent()
        )

        val sections = mutableListOf<Section>()

         timeExecutionControl.start {
            createIntroduction(
                sections = sections,
                articleTheme = articleTheme
            )

            createSections(
                sections = sections,
                articleTheme = articleTheme,
                sectionNumber = sectionNumber,
                subSectionNumber = subSectionNumber
            )

            createConclusion(
                sections = sections,
                articleTheme = articleTheme
            )

        }.also {elapsedTime ->
            return Article(
                durationTime = timeExecutionControl.formatElapsedTime(elapsedTime),
                title = "",
                sections = sections
            )
        }
    }

    private fun createIntroduction(
        sections: MutableList<Section>,
        articleTheme: String
    ) {
        sections.add(prepareArticleIntroductionUseCase.exec(articleTheme))
    }

    private fun createConclusion(
        sections: MutableList<Section>,
        articleTheme: String
    ) {
        sections.add(prepareArticleConclusionUseCase.exec(
            title = articleTheme,
            context = StringBuilder(sections.joinToString(" ") { it.title })
        ))
    }


    private fun createSections(
        sections: MutableList<Section>,
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int
    ) {
        (1..sectionNumber).forEach { index ->
            val section = prepareArticleSectionUseCase.exec(
                isSubSection = false,
                sectionTitle = "Seção $index",
                articleTheme = articleTheme,
                titleAlreadyCreated = StringBuilder(sections.joinToString(" ") { it.title }),
            )

            sections.add(section)
            createSubSections(
                sections = sections,
                theme = section.title.toString(),
                totalSubSections = subSectionNumber
            )
        }
    }

    private fun createSubSections(
        sections: MutableList<Section>,
        theme: String,
        totalSubSections: Int
    ) {
        (1..totalSubSections).forEach { i ->
            val subSection = prepareArticleSectionUseCase.exec(
                isSubSection = true,
                sectionTitle = "Subseção $i",
                articleTheme = theme,
                titleAlreadyCreated = StringBuilder(sections.joinToString(" ") { it.title })
            )

            sections.add(subSection)
        }
    }
}
