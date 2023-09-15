package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.entity.Section

interface PrepareArticleSectionUseCase {
    fun exec(
        isSubSection: Boolean,
        sectionTitle: String,
        articleTheme: String,
        titleAlreadyCreated: StringBuilder,
        minChar: Int,
        maxChar: Int
    ): Section
}