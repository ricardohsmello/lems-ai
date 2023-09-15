package br.com.ricas.lemsai.application.controller

import br.com.ricas.lemsai.application.config.ArticleConfig
import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.usecase.CreateArticleUseCase
import br.com.ricas.lemsai.domain.util.logger
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("article")
class ArticleController(
    val createArticleUseCase: CreateArticleUseCase

) {
    val logger = this.logger()

    @PostMapping("/create")
    fun request(
        @RequestParam theme: String,
        @RequestParam sectionNumbers: String,
        @RequestParam subSectionNumbers: String,
        @RequestParam minCharSection: String,
        @RequestParam maxCharSection: String
        ): Article {

        ArticleConfig.setCharSectionValues(
            min = Integer.parseInt(minCharSection),
            max = Integer.parseInt(maxCharSection)
        )

        return createArticleUseCase.exec(
            theme,
            Integer.parseInt(sectionNumbers),
            Integer.parseInt(subSectionNumbers)
        ).also {
            logger.info(it.toString())
        }
    }
}