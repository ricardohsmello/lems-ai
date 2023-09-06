package br.com.ricas.lemsai.domain.service

interface OpenAIService {
    fun createArticle(input: String): StringBuilder
}