package br.com.ricas.lemsai.domain.usecase

import br.com.ricas.lemsai.domain.entity.Section

interface CreateSectionUseCase {
    fun exec(
        titlePropertyMessage: String,
        contentPropertyMessage: String,
        isSubSection: Boolean
    ): Section
}