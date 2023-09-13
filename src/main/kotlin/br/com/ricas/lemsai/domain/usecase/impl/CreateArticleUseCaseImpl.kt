package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionContentUseCase
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionTitleUseCase
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.stereotype.Service

@Service
class CreateArticleUseCaseImpl(
    val timeExecutionControl: TimeExecutionControl,
    val createArticleSectionTitleUseCase: CreateArticleSectionTitleUseCase,
    val createArticleSectionContentUseCase: CreateArticleSectionContentUseCase
) : CreateArticleUseCase {

    lateinit var article: Article
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

        val elapsedTime = timeExecutionControl.start {
            val introduction = createIntroduction(
                articleTheme = articleTheme
            )
            createSections(introduction, 1, sectionNumber, subSectionNumber)

            createConclusion()
        }

        val formattedTime = timeExecutionControl.formatElapsedTime(elapsedTime)

        article = Article(
            durationTime = formattedTime,
            title = "",
            sections = sections
        )
        println("\n\nTotal time for generation: $formattedTime")

        return article

    }

    private fun createConclusion() {
        var theme = ""
        sections.map {
            theme = theme + " " + it.title
        }

        println("Starting conclusion creation")

        val sectionTitle = createArticleSectionTitleUseCase.exec("Conclusão", theme)
        val sectionContent = createArticleSectionContentUseCase.exec(sectionTitle)

        sections.add(
            Section(
                order = sections.size,
                title = "Conclusão",
                isSubSection = false,
                content = sectionContent
            )
        )
    }
    private fun createIntroduction(articleTheme: String) : String {
        println("Starting introduction creation for theme $articleTheme")

        val sectionTitle = createArticleSectionTitleUseCase.exec("introdução", articleTheme)
        val sectionContent = createArticleSectionContentUseCase.exec(sectionTitle)

        sections.add(
            Section(
                order = 0,
                title = sectionTitle,
                isSubSection = false,
                content = sectionContent
            )
        )

        return sectionTitle
    }

    private fun createSections(
        introduction: String,
        index: Int,
        sectionNumber: Int,
        subSectionNumber: Int
    ) {
        if (index > sectionNumber){
            return
        }

        println("Starting section $index creation")

        val sectionTitle = createArticleSectionTitleUseCase.exec("Seção $index", introduction)
        val sectionContent = createArticleSectionContentUseCase.exec(sectionTitle)

        sections.add(
            Section(
                order = index,
                title = sectionTitle,
                isSubSection = false,
                content = sectionContent
            )
        )

        createSubSections(
            order = index + 1,
            theme = sectionTitle,
            totalSubSections = subSectionNumber
        )

        createSections(introduction,index +1, sectionNumber, subSectionNumber)
    }

    private fun createSubSections(
        order: Int,
        theme: String,
        totalSubSections: Int
    ) {

        var ordenation = order

        for (i in 1 .. totalSubSections) {
            println("Starting subSection $i creation")
            val sectionTitle = createArticleSectionTitleUseCase.exec("SubSeção $i", theme)
            val sectionContent = createArticleSectionContentUseCase.exec(sectionTitle)

            sections.add(
                Section(
                    order = ordenation,
                    title = sectionTitle,
                    isSubSection = true,
                    content = sectionContent
                )
            )

            ordenation++
        }
    }
}