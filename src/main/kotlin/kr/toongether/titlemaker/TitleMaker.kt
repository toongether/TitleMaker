package kr.toongether.titlemaker

import javax.script.ScriptEngineManager
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngine

class TitleMaker(private val title: String, private val font: Font) {
    private val scriptEngineManager = ScriptEngineManager()
    private val scriptEngine: ScriptEngine = scriptEngineManager.getEngineByName("nashorn")

    private fun executeScript(scriptName: String) {
        val scriptPath = Paths.get("src", "main", "javascript", scriptName)
        val scriptContent = String(Files.readAllBytes(scriptPath))
        scriptEngine.eval(scriptContent)
    }

    fun make(alignLeft: Boolean): String {
        executeScript("opentype.js")
        executeScript("main.js")
        val fontPath = Paths.get("src", "main", "fonts", font.fileName)
        val script = "main('$title', '$fontPath', $alignLeft);"
        return scriptEngine.eval(script) as String
    }
}