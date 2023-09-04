package br.com.ricas.lemsai.domain

import br.com.ricas.lemsai.resouces.OpenAI
import br.com.ricas.lemsai.resouces.OpenAIResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OpenAIServiceImpl(

) : OpenAIService {

    override fun exec(question: String): OpenAIResponse {

        val openAI = OpenAI()
        openAI.makeQuestion(question)
    }

}