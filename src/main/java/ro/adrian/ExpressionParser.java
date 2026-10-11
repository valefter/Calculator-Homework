package ro.adrian;

public class ExpressionParser {

  public String[] parse(String expression) {
    String[] parts = expression.split("\\s+");

    validateParts(parts);
    return parts;
  }

  private void validateParts(String[] arr) {
    if (arr.length < 5) {
      throw new IllegalArgumentException("The expression length is lower than 5");
    }

    for (int i = 0; i < arr.length; i++) {
      if (i % 3 == 0) {
        if (!isNumber(arr[i])) {
          throw new IllegalArgumentException(arr[i] + " is not a number");
        }
      }

      if (i % 3 == 1) {
        if (!isSupportedUnitFormat(arr[i])) {
          throw new IllegalArgumentException(arr[i] + " is not a supported unit format");
        }
      }

      if (i % 3 == 2) {
        if (!isSupportedOperator(arr[i])) {
          throw new IllegalArgumentException(arr[i] + " is not a supported operator");
        }
      }

      if (i == arr.length - 1) {
        if (!isSupportedUnitFormat(arr[i])) {
          throw new IllegalArgumentException(
              arr[i] + " is not a supported unit format. The last value must be a unit");
        }
      }
    }
  }

  private boolean isSupportedUnitFormat(String format) {
    return switch (format) {
      case "mm", "cm", "dm", "m", "km" -> true;
      default -> false;
    };
  }

  private boolean isSupportedOperator(String operator) {
    return switch (operator) {
      case "+", "-" -> true;
      default -> false;
    };
  }

  private boolean isNumber(String stringNumber) {
    try {
      Double.parseDouble(stringNumber);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
