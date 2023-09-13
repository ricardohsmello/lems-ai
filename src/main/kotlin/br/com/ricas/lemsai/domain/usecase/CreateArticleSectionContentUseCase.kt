package br.com.ricas.lemsai.domain.usecase

import java.lang.StringBuilder

interface CreateArticleSectionContentUseCase {
    fun exec(title: String): StringBuilder
}