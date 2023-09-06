package br.com.ricas.lemsai.application

import br.com.ricas.lemsai.domain.service.OpenAIService
import br.com.ricas.lemsai.domain.util.TimeExecutionControl
import org.springframework.web.bind.annotation.*
import java.lang.StringBuilder
@RestController
@RequestMapping("openai")
class OpenAIController(
    val openAIService: OpenAIService,
    val timeExecutionControl: TimeExecutionControl
) {
    lateinit var openAiResponse: StringBuilder
    @PostMapping("/request")
    fun request(@RequestParam theme: String): StringBuilder? {
       println("Starting article creation ...")

        val elapsedTime = timeExecutionControl.start {

            openAiResponse = openAIService.createArticle(theme)
            println(openAiResponse)
        }

        val formattedTime = timeExecutionControl.formatElapsedTime(elapsedTime)

        println("\n\nTotal time for generation: $formattedTime")

        return openAiResponse
    }
}