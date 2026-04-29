import java.util.*;

public class ExpressionConverter {

    private static int precedence(char c) {
        switch (c) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
                return 3;
        }
        return -1;
    }

    // INFIX → POSTFIX
    public static String infixToPostfix(String exp) {
        StringBuilder result = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                result.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(')
                    result.append(stack.pop());
                stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(c) <= precedence(stack.peek()))
                    result.append(stack.pop());
                stack.push(c);
            }
        }

        while (!stack.isEmpty())
            result.append(stack.pop());

        return result.toString();
    }

    // INFIX → PREFIX
    public static String infixToPrefix(String exp) {
        StringBuilder input = new StringBuilder(exp);
        input.reverse();

        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '(')
                input.setCharAt(i, ')');
            else if (input.charAt(i) == ')')
                input.setCharAt(i, '(');
        }

        String postfix = infixToPostfix(input.toString());
        return new StringBuilder(postfix).reverse().toString();
    }

    // POSTFIX → INFIX
    public static String postfixToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String b = stack.pop();
                String a = stack.pop();
                stack.push("(" + a + c + b + ")");
            }
        }

        return stack.pop();
    }

    // PREFIX → INFIX
    public static String prefixToInfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String a = stack.pop();
                String b = stack.pop();
                stack.push("(" + a + c + b + ")");
            }
        }

        return stack.pop();
    }

    // POSTFIX → PREFIX
    public static String postfixToPrefix(String exp) {
        Stack<String> stack = new Stack<>();

        for (char c : exp.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String b = stack.pop();
                String a = stack.pop();
                stack.push(c + a + b);
            }
        }

        return stack.pop();
    }

    // PREFIX → POSTFIX
    public static String prefixToPostfix(String exp) {
        Stack<String> stack = new Stack<>();

        for (int i = exp.length() - 1; i >= 0; i--) {
            char c = exp.charAt(i);

            if (Character.isLetterOrDigit(c)) {
                stack.push(c + "");
            } else {
                String a = stack.pop();
                String b = stack.pop();
                stack.push(a + b + c);
            }
        }

        return stack.pop();
    }

    // MAIN PROCESSOR
    public static String process(String type, String expr) {
        switch (type) {
            case "infix_to_postfix":
                return infixToPostfix(expr);
            case "infix_to_prefix":
                return infixToPrefix(expr);
            case "postfix_to_infix":
                return postfixToInfix(expr);
            case "prefix_to_infix":
                return prefixToInfix(expr);
            case "postfix_to_prefix":
                return postfixToPrefix(expr);
            case "prefix_to_postfix":
                return prefixToPostfix(expr);
            default:
                return "Invalid Type";
        }
    }
}