package br.com.ricas.lemsai.application.config

object ArticleConfig {
    private var minCharSection: Int = 100
    private var maxCharSection: Int = 200

    fun setCharSectionValues(min: Int, max: Int) {
        minCharSection = min
        maxCharSection = max
    }
    fun getMinCharSection(): Int {
        return minCharSection
    }
    fun getMaxCharSection(): Int {
        return maxCharSection
    }
}
