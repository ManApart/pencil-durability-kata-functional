import kotlin.test.Test
import kotlin.test.assertEquals


class PencilDurabilityTest {

    @Test
    fun writeSingleCharacter() {
        val actual = "".write("a")
        assertEquals("a", actual.paper)
    }

    @Test
    fun writeWord() {
        val actual = "".write("apple")
        assertEquals("apple", actual.paper)
    }

    @Test
    fun pointDegradation() {
        val actual = Writer(pointDurability = 3).write("apple")
        assertEquals("app", actual.paper)
    }

    @Test
    fun pointDegradationDoesNotCountWhitespace() {
        val actual = Writer(pointDurability = 11).write("apple cider\n and")
        assertEquals("apple cider\n a", actual.paper)
    }

    @Test
    fun incrementalDegradation() {
        val actual = Writer(pointDurability = 7)
            .write("apple")
            .write("sauce")
        assertEquals("applesa", actual.paper)
    }

    @Test
    fun appendText() {
        val actual = "apple".write(" tree")
        assertEquals("apple tree", actual.paper)
    }

    @Test
    fun eraseText() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase("trees")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck      ?", actual.paper)
    }

    @Test
    fun eraseTextOfDifferentLength() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase("tree")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck     s?", actual.paper)
    }

    @Test
    fun eraseLastInstance() {
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?".erase("chuck")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", actual.paper)
    }

    @Test
    fun eraseDurability() {
        val actual = Writer("Buffalo Bill", eraserDurability = 3).erase("Bill")
        assertEquals("Buffalo B   ", actual.paper)
    }

    @Test
    fun incrementalEraseDurability() {
        val actual = Writer("Buffalo Bill", eraserDurability = 3)
            .erase("Bill")
            .erase("B")
        assertEquals("Buffalo B   ", actual.paper)
    }

    @Test
    fun editEmptySpace() {
        val actual = "An       a day keeps the doctor away".edit(3, "onion")
        assertEquals("An onion a day keeps the doctor away", actual.paper)
    }

    @Test
    fun editConflicts() {
        val actual = "An       a day keeps the doctor away".edit(3, "artichoke")
        assertEquals("An artich@k@ay keeps the doctor away", actual.paper)
    }

    @Test
    fun editPointDurability() {
        val actual = Writer("An       a day keeps the doctor away", pointDurability = 2).edit(3, "onion")
        assertEquals("An on    a day keeps the doctor away", actual.paper)
    }

    @Test
    fun incrementalEditDurability() {
        val actual = Writer("An       a day keeps the        away", pointDurability = 9)
            .edit(3, "onion")
            .edit(25, "doctor")
        assertEquals("An onion a day keeps the doct   away", actual.paper)
    }

}