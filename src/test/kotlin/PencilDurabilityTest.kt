import kotlin.test.Test
import kotlin.test.assertEquals


class PencilDurabilityTest {

    @Test
    fun writeSingleCharacter(){
        val actual = "".write("a")
        assertEquals("a", actual)
    }

    @Test
    fun writeWord(){
        val actual = "".write("apple")
        assertEquals("apple", actual)
    }

    @Test
    fun appendText(){
        val actual = "apple".write(" tree")
        assertEquals("apple tree", actual)
    }

    @Test
    fun eraseText(){
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase("trees")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck      ?", actual)
    }

    @Test
    fun eraseTextOfDifferentLength(){
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck trees?".erase("tree")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could chuck     s?", actual)
    }

    @Test
    fun eraseLastInstance(){
        val actual = "How much wood would a woodchuck chuck if a woodchuck could chuck wood?".erase("chuck")
        assertEquals("How much wood would a woodchuck chuck if a woodchuck could       wood?", actual)
    }
}