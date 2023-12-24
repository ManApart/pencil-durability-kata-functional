
fun String.write(text: String) = this + text

fun String.erase(text: String) = replace(text, text.indices.joinToString("") { " " })