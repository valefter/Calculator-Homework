package ro.adrian;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class ExpressionParserTest {

  @ParameterizedTest
  @MethodSource("invalidExpressions")
  public void testParseInvalidExpressions(String expression, String expectedErrorMessage) {
    // given
    ExpressionParser parser = new ExpressionParser();

    // when + then
    assertThatThrownBy(() -> parser.parse(expression))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage(expectedErrorMessage);
  }

  private static Stream<Arguments> invalidExpressions() {
    return Stream.of(
        Arguments.of("23 cm + 4", "The expression length is lower than 5"),
        Arguments.of("22 cm + 4 mm + ab km", "ab is not a number"),
        Arguments.of("20 kg + 40 m", "kg is not a supported unit format"),
        Arguments.of("17 mm * 40 cm ", "* is not a supported operator"),
        Arguments.of(
            "30 km + 7 mm + 10",
            "10 is not a supported unit format. The last value must be a unit"),
        Arguments.of(
            "30 km + 7 mm +", "+ is not a supported unit format. The last value must be a unit"),
        Arguments.of("15 cm + 2 dm 9 m", "9 is not a supported operator"));
  }

  @Test
  public void testParseValidExpression() {
    // given
    ExpressionParser parser = new ExpressionParser();

    // when
    String[] result = parser.parse("10 cm + 1 m - 10 mm");

    // then
    assertArrayEquals(new String[] {"10", "cm", "+", "1", "m", "-", "10", "mm"}, result);
  }
}
