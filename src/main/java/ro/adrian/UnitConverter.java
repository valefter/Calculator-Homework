package ro.adrian;

public class UnitConverter {

  public double getConversionFactor(String unit) {
    return switch (unit) {
      case "mm" -> 1.0;
      case "cm" -> 10.0;
      case "dm" -> 100.0;
      case "m" -> 1000.0;
      case "km" -> 1_000_000.0;
      default -> throw new IllegalArgumentException("Not an available unit");
    };
  }
}
