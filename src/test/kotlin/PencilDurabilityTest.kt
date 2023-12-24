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
}