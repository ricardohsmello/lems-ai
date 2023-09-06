package br.com.ricas.lemsai.domain.service

import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import java.lang.StringBuilder

interface OpenAIService {
    fun requestChatGPT(input: String): StringBuilder?
}