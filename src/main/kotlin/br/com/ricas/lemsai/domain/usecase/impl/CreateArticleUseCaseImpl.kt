package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.*
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.stereotype.Service

@Service
class CreateArticleUseCaseImpl(
    val timeExecutionControl: TimeExecutionControl,
    val prepareArticleIntroductionUseCase: PrepareArticleIntroductionUseCase,
    val prepareArticleSectionUseCase: PrepareArticleSectionUseCase,
    val prepareArticleConclusionUseCase: PrepareArticleConclusionUseCase
) : CreateArticleUseCase {

    var sections: MutableList<Section> = mutableListOf()

    override fun exec(
        articleTheme: String,
        sectionNumber: Int,
        subSectionNumber: Int
    ): Article {

        println(
            "Article settings: " +
                    "\n Name $articleTheme" +
                    "\n Total sections: $sectionNumber" +
                    "\n Total subSections: $subSectionNumber")

        val sections = mutableListOf<Section>()

        val elapsedTime = timeExecutionControl.start {

//           sections.add(createIntroduction(articleTheme = articleTheme))


            startCreation(
                articleTheme = articleTheme,
                sectionNumber = sectionNumber,
                subSectionNumber = subSectionNumber
            )

        }

        val formattedTime = timeExecutionControl.formatElapsedTime(elapsedTime)

        println("\n\nTotal time for generation: $formattedTime")

        return Article(
            durationTime = formattedTime,
            title = "",
            sections = sections
        )
    }

    private fun startCreation(articleTheme: String, sectionNumber: Int, subSectionNumber: Int) {
        createIntroduction(
            articleTheme = articleTheme
        )

        createSections(
            articleTheme = articleTheme,
            index = 1,
            sectionNumber = sectionNumber,
            subSectionNumber = subSectionNumber
        )

        createConclusion(
            articleTheme = articleTheme
        )
    }

    private fun createIntroduction(articleTheme: String): Section =
        prepareArticleIntroductionUseCase.exec(articleTheme)



    private fun createSections(
        articleTheme: String,
        index: Int,
        sectionNumber: Int,
        subSectionNumber: Int
    ) {
        if (index > sectionNumber){
            return
        }

        val section = prepareArticleSectionUseCase.exec(
            isSubSection = false,
            sectionTitle = "Seção $index",
            articleTheme = articleTheme,
            titleAlreadyCreated = StringBuilder(titlesAlreadyCreated()))


        sections.add(
            section
        )

        createSubSections(
            theme = section.title.toString(),
            totalSubSections = subSectionNumber
        )

        createSections(articleTheme,index +1, sectionNumber, subSectionNumber)
    }

    private fun createSubSections(
        theme: String,
        totalSubSections: Int
    ) {

        for (i in 1 .. totalSubSections) {

            val subSection = prepareArticleSectionUseCase.exec(
                isSubSection = true,
                sectionTitle = "Subseção $i",
                articleTheme = theme,
                titleAlreadyCreated = StringBuilder(titlesAlreadyCreated()))

            sections.add(
                subSection
            )
        }
    }

    private fun createConclusion(articleTheme: String) {
        sections.add(prepareArticleConclusionUseCase.exec(
            title = articleTheme,
            context = StringBuilder(titlesAlreadyCreated())
        ))
    }

    private fun titlesAlreadyCreated(): String {
        return sections.joinToString(" ") { it.title }
    }

}