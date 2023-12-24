import kotlin.math.min

data class Writer(val paper: String = "", val pointDurability: Int = 100, val eraserDurability: Int = 100)

fun String.write(text: String) = Writer(this).write(text)
fun Writer.write(textToWrite: String) : Writer {
    val amountToWrite = min(textToWrite.length, pointDurability)
    val text = textToWrite.take(amountToWrite)
    return copy(paper = paper + text)
}

fun String.erase(textToErase: String) = Writer(this).erase(textToErase)
fun Writer.erase(textToErase: String): Writer {
    val durabilityUsed = min(eraserDurability, textToErase.length)
    val text = textToErase.takeLast(durabilityUsed)
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
