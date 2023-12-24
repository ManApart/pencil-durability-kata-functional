import kotlin.math.min

data class Pencil(var eraserDurability: Int = 100)


fun String.write(text: String) = this + text

fun String.erase(pencil: Pencil, textToErase: String): String {
    val durabilityUsed = min(pencil.eraserDurability, textToErase.length)
    val amountWeCantErase = if (textToErase.length < pencil.eraserDurability) 0 else textToErase.length - pencil.eraserDurability
    val text = textToErase.drop(amountWeCantErase)
    val start = lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }

    pencil.eraserDurability -= durabilityUsed
    return edit(start, replacement, true)
}

fun String.edit(start: Int, text: String, ignoreConflicts: Boolean = false): String {
    val replacement = if (ignoreConflicts) text else {
        text.mapIndexed { i, c ->
            val replacedChar = this[start + i]
            if (replacedChar == ' ') c else '@'
        }.joinToString("")
    }

    return substring(0, start) + replacement + substring(start + text.length)
}
