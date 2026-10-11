package ro.adrian;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class UnitConverterTest {

  @ParameterizedTest
  @MethodSource("conversionFactors")
  public void testGetConversionFactor(String unit, double expected) {
    // given
    UnitConverter unitConverter = new UnitConverter();

    // when
    double result = unitConverter.getConversionFactor(unit);

    // then
    assertEquals(expected, result, 0.000001);
  }

  private static Stream<Arguments> conversionFactors() {
    return Stream.of(
        Arguments.of("mm", 1.0),
        Arguments.of("cm", 10.0),
        Arguments.of("dm", 100.0),
        Arguments.of("m", 1000.0),
        Arguments.of("km", 1_000_000.0));
  }

  @Test
  public void testGetConversionFactorWithInvalidUnit() {
    // given
    UnitConverter unitConverter = new UnitConverter();

    // when + then
    assertThrows(IllegalArgumentException.class, () -> unitConverter.getConversionFactor("ab"));
  }
}
