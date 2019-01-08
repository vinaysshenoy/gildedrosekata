import org.junit.jupiter.params.provider.Arguments

fun arguments(vararg args: Any?): Arguments {
  return Arguments { args }
}
