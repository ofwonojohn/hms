import java.util.Scanner;

public class Calculator {

    public static void main(String[] args) {
        double num1;
        double num2;
        double results;
        char operator;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Simple Calculator.");
        System.out.print("Enter first number: ");
        num1 = scanner.nextDouble();

        System.out.print("Enter operator(+, -, *, /, ^): ");
        operator = scanner.next().charAt(0);

        System.out.print("Enter the second number: ");
        num2 = scanner.nextDouble();

        switch (operator) {
            case '+':
                results = num1 + num2;
                break;
            case '-':
                results = num1 - num2;
                break;
            case '*':
                results = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    results = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero.");
                    return;
                }
                break;
            case '^':
                results = Math.pow(num1, num2);
                break;
            default:
                System.out.println("Error: Invalid operator.");
                return;
        }

        System.out.println("Result: " + results);
    }
}
