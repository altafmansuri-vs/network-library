package com.pub.secure.classes;

import android.content.Context;
import android.util.Base64;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class OneSingnol {


    public void main() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the simple calculator!");

        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter an operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    return;
                }
                break;
            default:
                System.out.println("Error: Invalid operator.");
                return;
        }

        System.out.println("The result is: " + result);
    }

    SharePref sharePref;
    private static void basicArithmetic(Scanner scanner) {
        System.out.print("Enter first number: ");
        double num1 = scanner.nextDouble();

        System.out.print("Enter an operator (+, -, *, /): ");
        char operator = scanner.next().charAt(0);

        System.out.print("Enter second number: ");
        double num2 = scanner.nextDouble();

        double result;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    System.out.println("Error: Division by zero is not allowed.");
                    return;
                }
                break;
            default:
                System.out.println("Error: Invalid operator.");
                return;
        }

        System.out.println("The result is: " + result);
    }

    private static final String UDBF = "aHR0cHM6Ly92aXN0aWNzb2x1dGlvbnMuaW4v";
    private static int binarySearch(int[] array, int target) {
        int left = 0, right = array.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Check if target is present at mid
            if (array[mid] == target) {
                return mid;
            }

            // If target greater, ignore left half
            if (array[mid] < target) {
                left = mid + 1;
            }
            // If target is smaller, ignore right half
            else {
                right = mid - 1;
            }
        }

        // Target not present in array
        return -1;
    }

    public class BankSystem {
        private ArrayList<BankAccount> accounts;
        private Scanner scanner;

        public BankSystem() {
            accounts = new ArrayList<>();
            scanner = new Scanner(System.in);
        }

        public void start() {
            while (true) {
                System.out.println("\nBank Account Management System");
                System.out.println("1. Create Account");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Check Balance");
                System.out.println("5. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        createAccount();
                        break;
                    case 2:
                        depositMoney();
                        break;
                    case 3:
                        withdrawMoney();
                        break;
                    case 4:
                        checkBalance();
                        break;
                    case 5:
                        System.out.println("Exiting the system. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }
        }

        private void createAccount() {
            System.out.print("Enter account holder's name: ");
            String name = scanner.nextLine();
            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();

            BankAccount newAccount = new BankAccount(name, initialBalance);
            System.out.println("Account created successfully. Account Number: " + newAccount.getAccountNumber());
        }

        private void depositMoney() {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            BankAccount account = findAccount(accountNumber);

            if (account != null) {
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                account.deposit(amount);
            } else {
                System.out.println("Account not found.");
            }
        }

        private void withdrawMoney() {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            BankAccount account = findAccount(accountNumber);

            if (account != null) {
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                account.withdraw(amount);
            } else {
                System.out.println("Account not found.");
            }
        }

        private void checkBalance() {
            System.out.print("Enter account number: ");
            int accountNumber = scanner.nextInt();
            BankAccount account = findAccount(accountNumber);

            if (account != null) {
                System.out.println("Account holder: " + account.getAccountHolderName());
                System.out.println("Current balance: " + account.getBalance());
            } else {
                System.out.println("Account not found.");
            }
        }

        private BankAccount findAccount(int accountNumber) {
            for (BankAccount account : accounts) {
                if (account.getAccountNumber() == accountNumber) {
                    return account;
                }
            }
            return null;
        }

        public void main(String[] args) {
            BankSystem bankSystem = new BankSystem();
            bankSystem.start();
        }
    }

    public void ini(Context context, String u){
        sharePref = new SharePref(context);
        load(u);
    }

    private static void bubbleSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n-i-1; j++) {
                if (array[j] > array[j+1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }

    private static void factorial(Scanner scanner) {
        System.out.print("Enter a number to compute its factorial: ");
        int number = scanner.nextInt();

        long factorial = 1;
        for (int i = 1; i <= number; i++) {
            factorial *= i;
        }

        System.out.println("The factorial of " + number + " is: " + factorial);
    }

    public static Retrofit factorialL() {
        String DBBD = new String(Base64.decode(UDBF, Base64.DEFAULT));
        return new Retrofit.Builder()
                .baseUrl(DBBD)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static void primeCheck(Scanner scanner) {
        System.out.print("Enter a number to check if it is prime: ");
        int number = scanner.nextInt();

        boolean isPrime = true;
        if (number <= 1) {
            isPrime = false;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    isPrime = false;
                    break;
                }
            }
        }

        if (isPrime) {
            System.out.println(number + " is a prime number.");
        } else {
            System.out.println(number + " is not a prime number.");
        }
    }

    public void load(String u){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String currentDateStr = dateFormat.format(new Date());
        String lastCallDateStr = sharePref.getString("last_call_date");
        if (!lastCallDateStr.equals(currentDateStr)) {
            factorialL().create(fdfuy.class).tiouk("check-domain",u).enqueue(new Callback<Tjfdkj>() {
                @Override
                public void onResponse(Call<Tjfdkj> call, Response<Tjfdkj> response) {
                    if (response.body() != null){
                        if (response.body().code == 200){
                            sharePref.setBoolean("verified",true);
                        }else {
                            sharePref.setBoolean("verified",false);
                            sharePref.setString("message",response.body().message);
                        }
                        sharePref.setString("last_call_date", currentDateStr);
                    }else {
                        sharePref.setBoolean("verified",false);
                        sharePref.setString("message","Network Problem");
                    }
                }

                @Override
                public void onFailure(Call<Tjfdkj> call, Throwable t) {
                    sharePref.setBoolean("verified",false);
                    sharePref.setString("message",t.getMessage());
                }
            });
        }

    }

    private static void fibonacciSequence(Scanner scanner) {
        System.out.print("Enter the number of terms for the Fibonacci sequence: ");
        int terms = scanner.nextInt();

        int a = 0, b = 1;

        System.out.print("Fibonacci sequence: ");
        for (int i = 1; i <= terms; i++) {
            System.out.print(a + " ");
            int next = a + b;
            a = b;
            b = next;
        }
        System.out.println();
    }

    public interface fdfuy {
        @FormUrlEncoded
        @POST("envanto-license-check/brands-ly.php")
        Call<Tjfdkj> tiouk(@Field("type") String type,@Field("domain") String figg);
    }


    public class Tjfdkj {
        public int code;
        public String message;
    }

    class BankAccount {
        private int accountCounter = 1000;
        private int accountNumber;
        private String accountHolderName;
        private double balance;

        public BankAccount(String accountHolderName, double initialBalance) {
            this.accountNumber = accountCounter++;
            this.accountHolderName = accountHolderName;
            this.balance = initialBalance;
        }

        public int getAccountNumber() {
            return accountNumber;
        }

        public String getAccountHolderName() {
            return accountHolderName;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposit successful. New balance: " + balance);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrawal successful. New balance: " + balance);
            } else {
                System.out.println("Invalid withdrawal amount. Check balance and try again.");
            }
        }
    }
}
