import kotlin.math.min

data class Writer(
    val paper: String = "",
    val pointDurability: Int = 100,
    val initialPointDurability: Int = pointDurability,
    val length: Int = 2,
    val eraserDurability: Int = 100
)

fun String.write(text: String) = Writer(this).write(text)
fun Writer.write(textToWrite: String) = edit(paper.length, textToWrite)

fun String.erase(text: String) = Writer(this).erase(text)
fun Writer.erase(text: String): Writer {
    val start = paper.lastIndexOf(text)

    var durabilityLeft = eraserDurability
    val replacement = text.reversed().mapNotNull { c ->
        if (durabilityLeft > 0) {
            if (!c.isWhitespace()) durabilityLeft--
            ' '
        } else c
    }.reversed().joinToString("")

    val newPaper = paper.replace(start, text, replacement)
    return copy(paper = newPaper, eraserDurability = durabilityLeft)
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

    val newPaper = paper.replace(start, text, replacement)
    return copy(paper = newPaper, pointDurability = durabilityLeft)
}

fun Writer.sharpen(): Writer {
    return if (length > 0) {
        copy(length = length - 1, pointDurability = initialPointDurability)
    } else this
}

private fun String.replace(start: Int, text: String, replacement: String): String {
    val startText = substring(0, min(start, length))
    val end = if (length > start + text.length) substring(start + text.length) else ""
    return startText + replacement + end
}

