package kr.toongether.titlemaker

/**
 * Font Enum Class
 *
 * @property fileName name of the font file.
 * @property fontName name of the font.
 */
enum class Font(val fileName: String, val fontName: String) {
    CAFE24CLASSIC("Cafe24Classic.otf", "카페24클래식타입"),
    CAFE24DANJUNGHAE("Cafe24Danjunghae.otf", "카페24단정해"),
    CHOSUNNM("ChosunNm.otf", "조선일보명조"),
    GANGWONEDUPOWER("GangwonEduPower.otf", "강원교육튼튼"),
    GHANACHOCOLATE("Ghanachocolate.otf", "가나초콜릿"),
    HEIROFLIGHT("HeirofLight.otf", "빛의계승자"),
    JALNAN("Jalnan.otf", "여기어때 잘난체"),
    SANDBOX("Sandbox.otf", "어그로체");

    /**
     * Returns preview SVG string of the font.
     *
     * @return font SVG string.
     */
    val svg: () -> String = {
        val titleMaker = TitleMaker(this.fontName, this)
        titleMaker.make(false)
    }
}