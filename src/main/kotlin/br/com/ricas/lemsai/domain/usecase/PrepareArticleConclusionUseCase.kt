package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.entity.Section
import java.lang.StringBuilder

interface PrepareArticleConclusionUseCase {
    fun exec(
        title: String,
        context: StringBuilder
    ): Section
}

