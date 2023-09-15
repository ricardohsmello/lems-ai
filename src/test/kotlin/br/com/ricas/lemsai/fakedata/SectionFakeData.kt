package br.com.ricas.lemsai.fakedata

import br.com.ricas.lemsai.domain.entity.Section

val fakeSection = Section(
    title = StringBuilder("Fake Section Title"),
    isSubSection = false,
    content = StringBuilder("This is the fake content of the section.")
)
