import java.util.Scanner;

public class MortgageCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double principal;
        double amount;
        int years;
        int timescompounded;
        double rate;

        System.out.println("Simple Mortgage Calculator");
        System.out.print("Enter Principal Amount: ");
        principal = scanner.nextDouble();

        System.out.print("Enter the interest rate in %: ");
        rate = scanner.nextDouble() / 100;

        System.out.print("Enter number of times compounded per year: ");
        timescompounded = scanner.nextInt();

        System.out.print("Enter the loan term in years: ");
        years = scanner.nextInt();

        double monthlyRate = rate / timescompounded;
        int numberOfPayments = timescompounded * years;
        double monthlyPayment = principal * (monthlyRate * Math.pow(1 + monthlyRate, numberOfPayments)) / (Math.pow(1 + monthlyRate, numberOfPayments) - 1);
        amount = monthlyPayment * numberOfPayments;

        System.out.println("The total amount paid is Shs: " + amount);

        scanner.close();
    }
}
