
fun String.write(text: String) = this + text

fun String.erase(text: String): String {
    val start = lastIndexOf(text)
    val replacement = text.indices.joinToString("") { " " }
    return substring(0, start) + replacement + substring(start + text.length)
}