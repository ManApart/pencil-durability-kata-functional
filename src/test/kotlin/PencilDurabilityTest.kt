import kotlin.test.Test
import kotlin.test.assertEquals


class PencilDurabilityTest {

    @Test
    fun writeSingleCharacter() {
        val actual = "".write("a")
        assertEquals("a", actual)
    }

    @Test
    fun writeWord() {
        val actual = "".write("apple")
        assertEquals("apple", actual)
    }

    @Test
    fun appendText() {
        val actual = "apple".write(" tree")
        assertEquals("apple tree", actual)
    }

    @Test
    fun eraseText() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase(Pencil(), "trees")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck      ?", actual)
    }

    @Test
    fun eraseTextOfDifferentLength() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase(Pencil(), "tree")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck     s?", actual)
    }

    @Test
    fun eraseLastInstance() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?".erase(Pencil(), "chuck")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", actual)
    }

    @Test
    fun eraseDurability() {
        val actual = "Buffalo Bill".erase(Pencil(eraserDurability = 3), "Bill")
        assertEquals("Buffalo B   ", actual)
    }

    @Test
    fun incrementalEraseDurability() {
        val pencil = Pencil(eraserDurability = 3)
        val actual = "Buffalo Bill"
            .erase(pencil, "Bill")
            .erase(pencil, "B")
        assertEquals("Buffalo B   ", actual)
    }

    @Test
    fun editEmptySpace() {
        val actual = "An       a day keeps the doctor away".edit(3, "onion")
        assertEquals("An onion a day keeps the doctor away", actual)
    }

    @Test
    fun editConflicts() {
        val actual = "An       a day keeps the doctor away".edit(3, "artichoke")
        assertEquals("An artich@k@ay keeps the doctor away", actual)
    }
}