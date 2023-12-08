package kr.toongether.titlemaker

import javax.script.ScriptEngineManager

/**
 * TitleMaker Class
 *
 * @property title the title to display.
 * @property font the font of the title.
 */
class TitleMaker(private val title: String, private val font: Font) {
    private val scriptEngineManager = ScriptEngineManager()
    private val scriptEngine = scriptEngineManager.getEngineByName("nashorn")

    init {
        scriptEngine.put("fontByteArray", getByteArray(font.fileName))
        executeScript("opentype.js")
        executeScript("main.js")
    }

    /**
     * Returns ByteArray of File
     *
     * @param resourceName name of the resource.
     * @return bytearray of the resource
     */
    private fun getByteArray(resourceName: String): ByteArray {
        val classLoader = object {}.javaClass.classLoader
        val stream = classLoader.getResourceAsStream(resourceName)
        return stream?.readBytes() ?: ByteArray(0)
    }

    /**
     * Executes JavaScript
     *
     * @param scriptName name of the script.
     */
    private fun executeScript(scriptName: String) {
        val scriptContent = String(getByteArray(scriptName))
        scriptEngine.eval(scriptContent)
    }

    /**
     * Makes Title
     *
     * @param alignLeft aligns title to Left.
     * @return title SVG string.
     */
    fun make(alignLeft: Boolean): String {
        val script = "main('$title', $alignLeft);"
        return scriptEngine.eval(script) as String
    }
}