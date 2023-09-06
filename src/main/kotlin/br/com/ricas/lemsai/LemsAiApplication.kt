package br.com.ricas.lemsai

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["br.com.ricas.lemsai"])
class LemsAiApplication

fun main(args: Array<String>) {
	runApplication<LemsAiApplication>(*args)
}
