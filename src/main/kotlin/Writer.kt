
fun String.write(text: String) = this + text

fun String.erase(text: String): String {
    val start = lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }
    return edit(start, replacement)
}

fun String.edit(start: Int, text: String): String {
    return substring(0, start) + text + substring(start + text.length)
}
