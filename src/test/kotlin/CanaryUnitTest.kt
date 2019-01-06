import org.junit.jupiter.api.Test
import strikt.api.*
import strikt.assertions.*

class CanaryUnitTest {

  @Test
  fun test() {
    expectThat(2 + 2).isEqualTo(4)
  }
}
