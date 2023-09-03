package br.com.ricas.lemsai.application

import br.com.ricas.lemsai.domain.OpenAIService
import org.springframework.web.bind.annotation.*

@RestController
class OpenAIController(
    val openAIService: OpenAIService
) {
    @PostMapping("/ai")
    fun call(@RequestParam question: String) {
        val exec = openAIService.exec(question)
        println(exec)

    }
}