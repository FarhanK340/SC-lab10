package expressivo;

public class ExpressionParser {
    public static Expression parse(String input) {
        input = input.replaceAll("\\s+", ""); // Remove spaces

        // Check for numeric constant
        if (input.matches("\\d+(\\.\\d+)?")) {
            return new Constant(Double.parseDouble(input));
        }

        // Check for variable
        if (input.matches("[a-zA-Z]+")) {
            return new Variable(input);
        }

        // Handle addition
        int plusIndex = findTopLevelOperator(input, '+');
        if (plusIndex != -1) {
            String left = input.substring(0, plusIndex);
            String right = input.substring(plusIndex + 1);
            return new Sum(parse(left), parse(right));
        }

        // Handle multiplication
        int starIndex = findTopLevelOperator(input, '*');
        if (starIndex != -1) {
            String left = input.substring(0, starIndex);
            String right = input.substring(starIndex + 1);
            return new Product(parse(left), parse(right));
        }

        throw new IllegalArgumentException("Invalid expression: " + input);
    }

    private static int findTopLevelOperator(String input, char operator) {
        int depth = 0;
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '(') depth++;
            if (c == ')') depth--;
            if (depth == 0 && c == operator) return i;
        }
        return -1;
    }
}
