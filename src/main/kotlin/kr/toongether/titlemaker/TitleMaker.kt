package kr.toongether.titlemaker

import javax.script.ScriptEngineManager
import java.nio.file.Files
import java.nio.file.Paths
import javax.script.ScriptEngine

/**
 * TitleMaker Class
 *
 * @property title the title to display.
 * @property font the font of the title.
 */
class TitleMaker(private val title: String, private val font: Font) {
    private val scriptEngineManager = ScriptEngineManager()
    private val scriptEngine: ScriptEngine = scriptEngineManager.getEngineByName("nashorn")

    /**
     * Executes Javascript
     * @param scriptName name of the script.
     */
    private fun executeScript(scriptName: String) {
        val scriptPath = Paths.get("src", "main", "javascript", scriptName)
        val scriptContent = String(Files.readAllBytes(scriptPath))
        scriptEngine.eval(scriptContent)
    }

    /**
     * Makes Title
     * @param alignLeft aligns title to Left.
     * @return title SVG string.
     */
    fun make(alignLeft: Boolean): String {
        executeScript("opentype.js")
        executeScript("main.js")
        val fontPath = Paths.get("src", "main", "fonts", font.fileName)
        val script = "main('$title', '$fontPath', $alignLeft);"
        return scriptEngine.eval(script) as String
    }
}