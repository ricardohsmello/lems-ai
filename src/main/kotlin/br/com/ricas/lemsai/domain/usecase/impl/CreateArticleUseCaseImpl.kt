package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleConclusionUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleIntroductionUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleSectionUseCase
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.stereotype.Service
import kotlin.math.max
import kotlin.math.min

@Service
class CreateArticleUseCaseImpl(
    private val timeExecutionControl: TimeExecutionControl,
    private val prepareArticleIntroductionUseCase: PrepareArticleIntroductionUseCase,
    private val prepareArticleSectionUseCase: PrepareArticleSectionUseCase,
    private val prepareArticleConclusionUseCase: PrepareArticleConclusionUseCase
) : CreateArticleUseCase {

    override fun exec(
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int,
        minChar: Int,
        maxChar: Int
    ): Article {
        println(
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
                articleTheme = articleTheme,
                minChar = minChar,
                maxChar = maxChar
            )

            createSections(
                sections = sections,
                articleTheme = articleTheme,
                sectionNumber = sectionNumber,
                subSectionNumber = subSectionNumber,
                minChar = minChar,
                maxChar = maxChar
            )

            createConclusion(
                sections = sections,
                articleTheme = articleTheme,
                minChar = minChar,
                maxChar = maxChar
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
        articleTheme: String,
        minChar: Int,
        maxChar: Int
    ) {
        sections.add(prepareArticleIntroductionUseCase.exec(articleTheme, minChar, maxChar))
    }

    private fun createConclusion(
        sections: MutableList<Section>,
        articleTheme: String,
        minChar: Int,
        maxChar: Int
    ) {
        sections.add(prepareArticleConclusionUseCase.exec(
            title = articleTheme,
            context = StringBuilder(sections.joinToString(" ") { it.title }),
            minChar = minChar,
            maxChar = maxChar
        ))
    }


    private fun createSections(
        sections: MutableList<Section>,
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int,
        minChar: Int,
        maxChar: Int
    ) {
        (1..sectionNumber).forEach { index ->
            val section = prepareArticleSectionUseCase.exec(
                isSubSection = false,
                sectionTitle = "Seção $index",
                articleTheme = articleTheme,
                titleAlreadyCreated = StringBuilder(sections.joinToString(" ") { it.title }),
                minChar = minChar,
                maxChar = maxChar
            )

            sections.add(section)
            createSubSections(
                sections = sections,
                theme = section.title.toString(),
                totalSubSections = subSectionNumber,
                minChar = minChar,
                maxChar = maxChar
            )
        }
    }

    private fun createSubSections(
        sections: MutableList<Section>,
        theme: String,
        totalSubSections: Int,
        minChar: Int,
        maxChar: Int
    ) {
        (1..totalSubSections).forEach { i ->
            val subSection = prepareArticleSectionUseCase.exec(
                isSubSection = true,
                sectionTitle = "Subseção $i",
                articleTheme = theme,
                titleAlreadyCreated = StringBuilder(sections.joinToString(" ") { it.title }),
                minChar = minChar,
                maxChar = maxChar
            )

            sections.add(subSection)
        }
    }
}
