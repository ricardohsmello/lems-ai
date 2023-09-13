package br.com.ricas.lemsai.application.controller

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder

@RestController
@RequestMapping("article")
class ArticleController(
    val openAIService: OpenAIService,
    val createArticleUseCase: CreateArticleUseCase

) {
    lateinit var openAiResponse: StringBuilder
    @PostMapping("/create")
    fun request(
        @RequestParam theme: String,
        @RequestParam sectionNumbers: String,
        @RequestParam subSectionNumbers: String): Article {

        return createArticleUseCase.exec(
            theme,
            Integer.parseInt(sectionNumbers),
            Integer.parseInt(subSectionNumbers)
        ).also {
            println(it)
        }
    }

}