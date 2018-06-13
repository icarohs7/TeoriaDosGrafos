import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import teoriadosgrafos.extensoes.power

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
private class DoubleExtTest {
	
	@Test
	fun powerTestInt() {
		assertEquals(100.toDouble(), 10 power 2, "10 ^ 2")
		assertEquals(1024.toDouble(), 2 power 10, "2 ^ 10")
	}
	
	@Test
	fun powerTestDouble() {
		assertEquals(6.25, 2.5 power 2, "2.5 ^ 2")
		assertEquals(201.0, 3.25 power 4.5, 1.0, "3.25 ^ 4.5")
	}
}