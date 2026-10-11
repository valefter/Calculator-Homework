package ro.adrian;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

  @ParameterizedTest
  @MethodSource("differentOutputUnits")
  public void testCalculateDistanceInDifferentUnits(String output, double expected) {
    // given
    Calculator calculator = new Calculator();

    // when
    double result = calculator.calculateDistance("10 cm + 1 m - 10 mm", output);

    // then
    assertEquals(expected, result, 0.000001);
  }

  private static Stream<Arguments> differentOutputUnits() {
    return Stream.of(
        Arguments.of("mm", 1090),
        Arguments.of("cm", 109),
        Arguments.of("dm", 10.9),
        Arguments.of("m", 1.09),
        Arguments.of("km", 0.00109));
  }

  @Test
  public void testAddition() {
    // given
    Calculator calculator = new Calculator();

    // when
    double result = calculator.calculateDistance("10 mm + 1 mm", "mm");

    // then
    assertEquals(11, result, 0.000001);
  }

  @Test
  public void testSubtraction() {
    // given
    Calculator calculator = new Calculator();

    // when
    double result = calculator.calculateDistance("10 mm - 1 mm", "mm");

    // then
    assertEquals(9, result, 0.000001);
  }

  @Test
  public void testCalculateDistanceWithInvalidOutput() {
    // given
    Calculator calculator = new Calculator();

    // when + then
    assertThrows(
        IllegalArgumentException.class, () -> calculator.calculateDistance("10 cm + 1 m", "abc"));
  }
}
