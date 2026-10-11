package ro.adrian;

public class Calculator {
  private final ExpressionParser parser = new ExpressionParser();
  private final UnitConverter converter = new UnitConverter();

  public double calculateDistance(String expression, String outputUnit) {
    String[] parts = parser.parse(expression);

    double result = calculateResult(parts) / converter.getConversionFactor(outputUnit);
    return result;
  }

  private double calculateResult(String[] arr) {
    double result = 0;
    String operator = "+";
    for (int i = 0; i < arr.length - 1; i++) {
      if (i % 3 == 0) {
        String unit = arr[i + 1];
        double value = Double.parseDouble(arr[i]);
        if (operator.equals("+")) {
          result += value * converter.getConversionFactor(unit);
        } else {
          result -= value * converter.getConversionFactor(unit);
        }
      }
      if (i % 3 == 2) {
        operator = arr[i];
      }
    }
    return result;
  }
}
