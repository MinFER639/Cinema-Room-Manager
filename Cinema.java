package cinema;


import java.util.Scanner;

public class Cinema {
    final static Scanner scanner = new Scanner(System.in);
    private static int currentIncome = 0;

    public static void main(String[] args) {
        System.out.println("Enter the number of rows:");
        int numRows = scanner.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int numSeatsInRow = scanner.nextInt();

        String[][] cinema = spaceInCinema(numRows, numSeatsInRow);

        displayMenu();
        int var = scanner.nextInt();
        boolean flag = true;

        while (flag) {

            switch (var) {
                case 1:
                    displayCinema(cinema);
                    displayMenu();
                    var = scanner.nextInt();
                    break;
                case 2:
                    cinema = buyTicket(numRows, numSeatsInRow, cinema);
                    displayMenu();
                    var = scanner.nextInt();
                    break;
                case 3:
                    displayStatistics(numRows, numSeatsInRow, cinema);
                    displayMenu();
                    var = scanner.nextInt();
                    break;
                case 0:
                    flag = false;
                    break;
                default:
                    System.out.println("Wrong number picked!");
                    break;
            }
        }

    }

    public static void displayStatistics(int numRows, int numSeatsInRow, String[][] cinema) {
        int totalIncome = totalIncome(numRows, numSeatsInRow);

        int seatsSold = 0;
        int numSeats = numRows * numSeatsInRow;
        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numSeatsInRow; j++) {
                if (cinema[i][j] == "B") {
                    seatsSold++;
                }
            }
        }
        double proc = (seatsSold * 100.0) / numSeats;
        String proc1 = String.format("\nPercentage: %.2f", proc);

        System.out.printf("\nNumber of purchased tickets: %d", seatsSold);
        System.out.println(proc1 + "%");
        System.out.printf("Current income: $%d\n", currentIncome);
        System.out.printf("Total income: $%d\n", totalIncome);
    }

    public static void displayMenu() {
        System.out.println("\n1. Show the seats");
        System.out.println("2. Buy a ticket");
        System.out.println("3. Statistics");
        System.out.println("0. Exit");
    }

    static String[][] spaceInCinema(int numRows, int numSeatsInRow) {
        String[][] cinema = new String[numRows + 1][numSeatsInRow + 1];
        cinema[0][0] = " ";

        for (int i = 1; i <= numRows; i++) {
            cinema[i][0] = String.valueOf(i);
            for (int j = 1; j <= numSeatsInRow; j++) {
                cinema[0][j] = String.valueOf(j);
            }
        }

        for (int i = 1; i <= numRows; i++) {
            for (int j = 1; j <= numSeatsInRow; j++) {
                cinema[i][j] = "S";
            }
        }
        return cinema;
    }

    public static String[][] buyTicket(int numRows, int numSeatsInRow, String[][] cinema) {
        boolean flag = true;
        int price = 0;
        while (flag) {
            System.out.println("\nEnter a row number:");
            int rowNumber = scanner.nextInt();
            System.out.println("Enter a seat number in that row:");
            int seatNumber = scanner.nextInt();

            boolean statement1 = rowNumber > cinema.length - 1 || rowNumber <= 0;
            boolean statement2 = seatNumber > cinema[1].length - 1 || seatNumber <= 0;

            if (statement1 || statement2) {
                System.out.println("\nWrong input!");
            } else if (cinema[rowNumber][seatNumber] == "B") {
                System.out.println("\nThat ticket has already been purchased!");
            } else {
                int numSeats = numRows * numSeatsInRow;


                if (numSeats <= 60) {
                    price = 10;
                } else {
                    if (numRows % 2 == 0) {
                        int half = numRows / 2;
                        if (rowNumber <= half) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    } else {
                        int half = (numRows - 1) / 2;
                        if (rowNumber <= half) {
                            price = 10;
                        } else {
                            price = 8;
                        }
                    }
                }
                System.out.println("\nTicket price: $" + price);

                cinema[rowNumber][seatNumber] = "B";

                flag = false;
            }
        }
        currentIncome += price;
        return cinema;
    }

    public static void displayCinema(String[][] cinema) {
        System.out.println("\nCinema:");

        for (String[] strings : cinema) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
        }
    }

    static int totalIncome(int numRows, int numSeatsInRow) {

        int numSeats = numRows * numSeatsInRow;
        int totalIncome = 0;

        if (numSeats <= 60) {
            totalIncome = numSeats * 10;
        } else {
            if (numSeats % 2 == 0) {
                int firstHalfIncome = numSeats / 2 * 10;
                int secondHalfIncome = numSeats / 2 * 8;
                totalIncome = firstHalfIncome + secondHalfIncome;
            } else {
                int frontRowsIncome = (numRows - 1) / 2 * numSeatsInRow * 10;
                int backRowsIncome = (numRows + 1) / 2 * numSeatsInRow * 8;
                totalIncome = frontRowsIncome + backRowsIncome;
            }
        }
        return totalIncome;
    }

}

