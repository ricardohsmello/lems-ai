package br.com.ricas.lemsai.fakedata

import br.com.ricas.lemsai.domain.entity.Article
import br.com.ricas.lemsai.domain.entity.Section

val fakeArticle = Article(
    durationTime = "10 minutes",
    title = "Sample Article",
    sections = listOf(
        Section(
            title = StringBuilder("Introduction"),
            isSubSection = false,
            content = StringBuilder("This is the introduction of the article.")
        ),
        Section(
            title = StringBuilder("Section 1"),
            isSubSection = false,
            content = StringBuilder("This is the content of Section 1.")
        ),
        Section(
            title = StringBuilder("Subsection 1.1"),
            isSubSection = true,
            content = StringBuilder("This is the content of Subsection 1.1.")
        ),
        Section(
            title = StringBuilder("Conclusion"),
            isSubSection = false,
            content = StringBuilder("In conclusion, this is the end of the article.")
        )
    )
)
