import kotlin.math.min

data class Writer(
    val paper: String = "",
    val pointDurability: Int = 100,
    val eraserDurability: Int = 100
)

fun String.write(text: String) = Writer(this).write(text)
fun Writer.write(textToWrite: String) = edit(paper.length, textToWrite)

fun String.erase(textToErase: String) = Writer(this).erase(textToErase)
fun Writer.erase(textToErase: String): Writer {
    val durabilityUsed = min(eraserDurability, textToErase.length)
    val text = textToErase.takeLast(durabilityUsed)
    val start = paper.lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }

    val newPaper = paper.edit(start, text, replacement)
    return copy(paper = newPaper, eraserDurability = eraserDurability - durabilityUsed)
}

fun String.edit(start: Int, text: String) = Writer(this).edit(start, text)
fun Writer.edit(start: Int, textToWrite: String): Writer {
    var durabilityLeft = pointDurability

    val text = textToWrite.mapNotNull { c ->
        if (durabilityLeft > 0) {
            if (!c.isWhitespace()) durabilityLeft--
            c
        } else null
    }.joinToString("")

    val replacement = text.mapIndexed { i, c ->
        val replacedChar = paper.getOrNull(start + i) ?: ' '
        if (replacedChar == ' ') c else '@'
    }.joinToString("")

    val newPaper = paper.edit(start, text, replacement)
    return copy(paper = newPaper, pointDurability = durabilityLeft)
}

private fun String.edit(start: Int, text: String, replacement: String): String {
    val startText = substring(0, min(start, length))
    val end = if (length > start + text.length) substring(start + text.length) else ""
    return startText + replacement + end
}

