package br.com.ricas.lemsai.application

import br.com.ricas.lemsai.domain.service.OpenAIService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("openai")
class OpenAIController(
    val openAIService: OpenAIService
) {
    @PostMapping("/request")
    fun request(@RequestParam question: String) {
        val openAiResponse = openAIService.requestChatGPT(question)
        println(openAiResponse)
    }
}