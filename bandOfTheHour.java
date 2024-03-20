import java.util.Scanner;
public class bandOfTheHour {
    private static final Scanner keyboard = new Scanner(System.in);
    private static final int MAX_ROWS = 10;
    private static final int MAX_POSITIONS = 8;
    private static final int MINIMUM = 1;
    private static final double MAX_WEIGHT = 200.0;
    private static final double MIN_WEIGHT = 45.0;
    public static void printAssignment(double[][] musicianAssignment, int numRows){
        for(int row = 0; row < numRows; row++) {
            double total = 0.0;
            System.out.print((char) (row + 'A') + " : ");
            for (int position = 0; position < musicianAssignment[row].length; position++) {
                if (musicianAssignment[row][position] >= 100.00) {
                    System.out.print(musicianAssignment[row][position] + " ");
                } else if (musicianAssignment[row][position] >= 10.00) {
                    System.out.print(musicianAssignment[row][position] + "  ");
                } else {
                    System.out.print(musicianAssignment[row][position] + "   ");
                }
                total += musicianAssignment[row][position];
            }
            for (int emptySpace = 0; emptySpace < 8 - musicianAssignment[emptySpace].length - 1; emptySpace++) {
                System.out.print("      ");
            }
            //first part of brackets format
            if(total > 100.0){
                System.out.print("[" + String.format("%.1f", total));
            }
            else if(total > 10.0){
                System.out.print("[ " + String.format("%.1f", total));
            }
            else {
                System.out.print("[  " + String.format("%.1f", total));
            }

            //second part
            if(total / numRows > 100.0){
                System.out.print("," + String.format("%.1f", total / numRows) + "]");
            }
            else if(total / numRows > 10.0){
                System.out.print(", " + String.format("%.1f", total / numRows) + "]");
            }
            else {
                System.out.print(",  " + String.format("%.1f", total / numRows) + "]");
            }
            System.out.println();
        }
    }
    public static double[][] addMusician(double[][] musicianAssignment, int numRows) {
        double musicianWeight;
        System.out.print("\nPlease enter row letter : ");
        char currentRowLetter = Character.toUpperCase(keyboard.next().charAt(0));
        int rowIndex = currentRowLetter - 'A';

        while (rowIndex < 0 || rowIndex >= numRows) {
            System.out.print("\nERROR: Out of range, try again             : ");
            currentRowLetter = Character.toUpperCase(keyboard.next().charAt(0));
            rowIndex = currentRowLetter - 'A';
        } // end of while

        int numPositions = musicianAssignment[rowIndex].length;

        System.out.print("\nPlease enter a position number 1 to ( " +  musicianAssignment[rowIndex].length + ")  : ");
        int positionSpot = keyboard.nextInt();

        while(positionSpot < MINIMUM || positionSpot > musicianAssignment[rowIndex].length)
        {
            System.out.println("\nERROR: Out of range, try again             : ");
            positionSpot = keyboard.nextInt();
        }

        if(musicianAssignment[rowIndex][positionSpot-1] != 0.0){
            System.out.println("ERROR: There is already a musician there");
            return musicianAssignment;
        }

        System.out.print("\nPlease enter weight (45.0 to 200.0): ");
        musicianWeight = keyboard.nextDouble();

        double totalWeight = 0.0;
        for (int currentRow = 0; currentRow < numPositions; currentRow++) {
            totalWeight += musicianAssignment[rowIndex][currentRow];
        } // end of for

        while(musicianWeight < MIN_WEIGHT || musicianWeight > MAX_WEIGHT) {
            System.out.print("\n ERROR: Out of range, try again: ");
            musicianWeight = keyboard.nextDouble();
        } //end of while
        if (musicianWeight + totalWeight > (numPositions) * 100) {
            System.out.print("\nERROR: That would exceed the average weight limit: ");
            return musicianAssignment;
        } //end of if


        musicianAssignment[rowIndex][positionSpot-1] = musicianWeight;
        System.out.println("****** Musician added.");

        return musicianAssignment;
    } // end of addMusician
    public static double[][] removeMusician(double[][] musicianAssignment, int numRows) {
        int positionSpot;
        System.out.print("\nPlease enter row letter : ");
        char currentRowLetter = Character.toUpperCase(keyboard.next().charAt(0));
        int rowIndex = currentRowLetter - 'A';
        while (rowIndex < 0 || rowIndex >= numRows) {
            System.out.print("\nERROR: Out of range, try again             : ");
            currentRowLetter = Character.toUpperCase(keyboard.next().charAt(0));
            rowIndex = currentRowLetter - 'A';
        }
        System.out.print("\nPlease enter a position number 1 to ( " +  musicianAssignment[rowIndex].length + ")  : ");
        positionSpot = keyboard.nextInt();

        while(positionSpot < MINIMUM || positionSpot > musicianAssignment[rowIndex].length)
        {
            System.out.println("\nERROR: Out of range, try again             : ");
            positionSpot = keyboard.nextInt();
        }

        if(musicianAssignment[rowIndex][positionSpot-1] == 0.0){
            System.out.println("ERROR: That position is already vacant");
            return musicianAssignment;
        }
        musicianAssignment[rowIndex][positionSpot] = 0.0;
        System.out.println("****** Musician removed.");
        return musicianAssignment;
    }

    public static void main(String[] args) {
        char userChoice;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Band of the Hour");
        System.out.println("-------------------------------");

        System.out.print("Please enter number of rows (1 to 10)      : ");
        int numRows = keyboard.nextInt();
        while (numRows < MINIMUM || numRows > MAX_ROWS) {
            System.out.print("\nERROR: Out of range, try again             : ");
            numRows = keyboard.nextInt();
        } // end of while for get rows

        double[][] musicianAssignment = new double[numRows][];

        for (int currentRow = 0; currentRow < numRows; currentRow++) {
            System.out.print("\nPlease enter number of positions in row " + (char) ((currentRow + 65)) + ": ");
            int numPositions = keyboard.nextInt();
            while (numPositions < MINIMUM || numPositions > MAX_POSITIONS) {
                System.out.print("\nERROR: Out of range, try again             : ");
                numPositions = keyboard.nextInt();
            }//end of while for get positions
            musicianAssignment[currentRow] = new double[numPositions];

        }// end of for

        System.out.print("\n(A)dd, (R)emove, (P)rint,        e(X)it : ");
        System.out.println();
        userChoice = keyboard.next().charAt(0);
        while(!(userChoice == 'x' || userChoice == 'x')){
            switch(userChoice) {
                case 'A':
                case 'a':
                    addMusician(musicianAssignment, numRows);
                    break;
                case 'R':
                case 'r':
                    removeMusician(musicianAssignment, numRows);
                    break;
                case 'P':
                case 'p':
                    printAssignment(musicianAssignment, numRows);
                    break;
                case 'X':
                case 'x':
                    break;
                default:
                    System.out.print("ERROR: Invalid option, try again");
                    break;
            } // end of switch
            System.out.print("\n(A)dd, (R)emove, (P)rint,        e(X)it : ");
            System.out.println();
            userChoice = keyboard.next().charAt(0);
        }//end of while
    } // end of main
} //end of class




