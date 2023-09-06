package br.com.ricas.lemsai.domain.util

import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class TimeExecutionControl {

    fun start(action: () -> Unit) =
        measureExecutionTimeMillis(action)

    private inline fun measureExecutionTimeMillis(action: () -> Unit): Long {
        val startTime = System.currentTimeMillis()
        action()
        val endTime = System.currentTimeMillis()
        return endTime - startTime
    }

    fun formatElapsedTime(elapsedTimeMillis: Long): String {
        val minutes = TimeUnit.MILLISECONDS.toMinutes(elapsedTimeMillis)
        val seconds = TimeUnit.MILLISECONDS.toSeconds(elapsedTimeMillis) % 60
        return String.format("%d minutes %d seconds", minutes, seconds)
    }
}