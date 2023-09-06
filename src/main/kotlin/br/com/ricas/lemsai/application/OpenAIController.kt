package br.com.ricas.lemsai.application

import br.com.ricas.lemsai.domain.service.OpenAIService
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder

@RestController
@RequestMapping("openai")
class OpenAIController(
    val openAIService: OpenAIService
) {
    @PostMapping("/request")
    fun request(@RequestParam theme: String): StringBuilder? {
        val openAiResponse = openAIService.requestChatGPT(theme)
        println(openAiResponse)

        return openAiResponse
    }
}