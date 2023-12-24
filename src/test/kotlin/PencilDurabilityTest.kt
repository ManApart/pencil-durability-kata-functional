import kotlin.test.Test
import kotlin.test.assertEquals


class PencilDurabilityTest {

    @Test
    fun writeText(){
        val actual = "".write("a")
        assertEquals("a", actual)
    }
}