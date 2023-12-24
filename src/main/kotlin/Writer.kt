
fun String.write(text: String) = this + text

fun String.erase(textToErase: String, durability: Int = 100): String {
    val amountWeCantErase = if (textToErase.length < durability) 0 else textToErase.length - durability
    val text = textToErase.drop(amountWeCantErase)
    val start = lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }
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
