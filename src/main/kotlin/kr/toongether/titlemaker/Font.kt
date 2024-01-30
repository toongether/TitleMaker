package kr.toongether.titlemaker

/**
 * Font Enum Class
 *
 * @property fileName name of the font file.
 * @property fontName name of the font.
 */
enum class Font(val fileName: String, val fontName: String, val margin: Int) {
    CAFE24CLASSIC("Cafe24Classic.otf", "카페24클래식타입", 0),
    CAFE24DANJUNGHAE("Cafe24Danjunghae.otf", "카페24단정해", 0),
    CHOSUNNM("ChosunNm.otf", "조선일보명조", 5),
    GANGWONEDUPOWER("GangwonEduPower.otf", "강원교육튼튼", 7),
    GHANACHOCOLATE("Ghanachocolate.otf", "가나초콜릿", -5),
    HEIROFLIGHT("HeirofLight.otf", "빛의계승자", 5),
    JALNAN("Jalnan.otf", "여기어때 잘난체", 5),
    SANDBOX("Sandbox.otf", "어그로체", -5);

    /**
     * Returns preview SVG string of the font.
     *
     * @return font SVG string.
     */
    fun getSVG(): String {
        val titleMaker = TitleMaker(this.fontName, this)
        return titleMaker.make(false, wrap = false)
    }
}