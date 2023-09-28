package br.com.ricas.lemsai.fakedata

import br.com.ricas.lemsai.domain.ai.Choice
import br.com.ricas.lemsai.domain.ai.Message
import br.com.ricas.lemsai.domain.ai.OpenAIResponse
import br.com.ricas.lemsai.domain.ai.Usage

val fakeOpenAIResponse = OpenAIResponse(
    id = "fake-id",
    `object` = "fake-object",
    created = System.currentTimeMillis(),
    model = "fake-model",
    choices = listOf(
        Choice(
            index = 0,
            message = Message(
                role = "assistant",
                content = "Chatbot response here."
            ),
            finish_reason = "fake-finish-reason"
        )
    ),
    usage = Usage(
        prompt_tokens = 10,
        completion_tokens = 20,
        total_tokens = 30
    )
)
