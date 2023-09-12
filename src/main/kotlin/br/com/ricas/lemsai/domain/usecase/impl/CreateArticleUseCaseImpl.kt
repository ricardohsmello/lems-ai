package br.com.ricas.lemsai.domain.usecase.impl

import br.com.ricas.lemsai.domain.ai.AIMessage
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleContentUseCase
import br.com.ricas.lemsai.domain.usecase.CreateArticleSectionsTitleUseCase
import br.com.ricas.lemsai.domain.usecase.PrepareArticleStructureUseCase
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.stereotype.Service

@Service
class CreateArticleUseCaseImpl(
    val timeExecutionControl: TimeExecutionControl,
    val createArticleSectionsTitleUseCase: CreateArticleSectionsTitleUseCase,
    val prepareArticleStructureUseCase: PrepareArticleStructureUseCase,
    val prepareArticleContentUseCase: PrepareArticleContentUseCase
) : CreateArticleUseCase {

    lateinit var completeArticle: StringBuilder

    override fun exec(
        themeTitle: String,
        sectionsNumber: String,
        subSectionNumber: String
    ): StringBuilder {

        println("Starting article creation ...")

        val elapsedTime = timeExecutionControl.start {
            completeArticle = prepareArticleContentUseCase.exec(
                getArticleStructureBase(
                    prepareArticleStructureUseCase.exec(
                        themeTitle,
                        sectionsNumber,
                        subSectionNumber
                    )
                ),
                totalSections = Integer.parseInt(sectionsNumber) + 2
            )
        }

        val formattedTime = timeExecutionControl.formatElapsedTime(elapsedTime)

        println("\n\nTotal time for generation: $formattedTime")

        return completeArticle

    }


    private fun getSectionTitle(index: Int, sectionsNumber: Int): String {
       return when (index) {
           0 -> "Introdução"
           sectionsNumber -> "Conclusão"
           else -> "Seção " + (index + 1)
       }
    }

    private fun getArticleStructureBase(articleStructure: StringBuilder) =
         listOf(
            AIMessage(
                role = "user",
                content = articleStructure.toString()
            )
        )
}