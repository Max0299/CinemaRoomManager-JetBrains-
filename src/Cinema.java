import java.util.Objects;

import java.util.Scanner;

public class Cinema {

    static int tickets = 0;
    static float percentage = 0;
    static int currentIncome = 0;
    static int totalIncome = 0;

    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = scanner.nextInt();

        String[][] array = createCinema(rows, seats);

        showMenu(array,rows,seats);
    }

    public static void showMenu(String[][] array, int rows, int seats){
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("\n1. Show the seats \n2. Buy a ticket \n3. Statistics \n0. Exit");
            switch (scanner.nextInt()) {
                case 1 -> printCinema(array);
                case 2 -> buyTicket(array, rows, seats);
                case 3 -> getStatistic();
                case 0 -> isTrue = false;
            }
        }
    }

    public static void buyTicket(String[][] array, int a, int b) {
        Scanner scanner = new Scanner(System.in);
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Enter a row number:");
            int n = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int k = scanner.nextInt();
            if (n > array.length - 1 || k > array[0].length - 1 || n < 0 || k < 0) {
                System.out.println("Wrong input1");
            } else {
                if (Objects.equals(array[n][k], "B")) {
                    System.out.println("That ticket has already been purchased!");
                } else {
                    int ticketCost;
                    if (a * b <= 60) {
                        ticketCost = 10;
                    } else if (n <= a / 2) {
                        ticketCost = 10;
                    } else {
                        ticketCost = 8;
                    }

                    System.out.println("Ticket price: $" + ticketCost);
                    array[n][k] = "B";
                    tickets++;
                    currentIncome += ticketCost;
                    percentage = ((float) tickets / (a * b) * 100);
                    isTrue = false;
                }
            }
        }
    }

    public static String[][] createCinema(int rows, int seats) {
        int countI = 1;
        int countJ = 1;

        String[][] array = new String[rows + 1][seats + 1];
        for (int i = 0; i < rows + 1; i++) {
            for (int j = 0; j < seats + 1; j++) {
                if (i == 0 && j != 0) {
                    array[i][j] = String.valueOf(countI++);
                } else if (j == 0 && i != 0) {
                    array[i][j] = String.valueOf(countJ++);
                } else if (i == 0) {
                    array[i][j] = " ";
                } else {
                    array[i][j] = "S";
                }
            }
        }
        if (rows * seats <= 60) {
            totalIncome = (rows * seats) * 10;
        } else {
            totalIncome = (((rows / 2) * seats * 10) + ((rows - rows / 2) * seats * 8));
        }
        return array;
    }

    public static void printCinema(String[][] arr) {
        System.out.println("Cinema:");
        for (String[] strings : arr) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    public static void getStatistic() {
        System.out.printf("Number of purchased tickets: %d", tickets);
        System.out.println();
        System.out.printf("Percentage: %.2f", percentage);
        System.out.println("%");
        System.out.printf("Current income: $%d", currentIncome);
        System.out.println();
        System.out.printf("Total income: $%d", totalIncome);
        System.out.println();
    }
}
