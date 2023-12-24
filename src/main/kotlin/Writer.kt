import kotlin.math.min

data class Writer(val paper: String = "", val eraserDurability: Int = 100)

fun String.write(text: String) = this + text

fun String.erase(textToErase: String) = Writer(this).erase(textToErase)
fun Writer.erase(textToErase: String): Writer {
    val durabilityUsed = min(eraserDurability, textToErase.length)
    val amountWeCantErase = if (textToErase.length < eraserDurability) 0 else textToErase.length - eraserDurability
    val text = textToErase.drop(amountWeCantErase)
    val start = paper.lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }

    return this
        .copy(eraserDurability = eraserDurability - durabilityUsed)
        .edit(start, replacement, true)
}

fun String.edit(start: Int, text: String, ignoreConflicts: Boolean = false) =
    Writer(this).edit(start, text, ignoreConflicts)

fun Writer.edit(start: Int, text: String, ignoreConflicts: Boolean = false): Writer {
    val replacement = if (ignoreConflicts) text else {
        text.mapIndexed { i, c ->
            val replacedChar = paper[start + i]
            if (replacedChar == ' ') c else '@'
        }.joinToString("")
    }

    val newPaper = paper.substring(0, start) + replacement + paper.substring(start + text.length)
    return copy(paper = newPaper)
}
