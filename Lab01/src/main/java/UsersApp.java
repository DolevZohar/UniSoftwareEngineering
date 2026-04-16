import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UsersApp {
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>(); // Defining an array list as we were instructed

        String inputFileName = "users.txt"; //Default values for input and output file names
        String outputFileName = "out.txt";

        if (args.length >= 1) { // Using the inputted input file name
            inputFileName = args[0];
        }
        if (args.length >= 2) { // Using the inputted output file name
            outputFileName = args[1];
        }

        try (
                Scanner reader = new Scanner(new File(inputFileName)); // Using Scanner in order to read the input file
                PrintWriter writer = new PrintWriter(outputFileName) // Setting up PrintWriter in order to write the output file
        ) {
            while (reader.hasNextLine()) { // Running as long as we didn't finish the file
                String line = reader.nextLine();
                String[] parts = line.trim().split("\\s+"); // Splitting the line. We assume we have exactly 2 parts
                String username = parts[0];
                String password = parts[1];

                try {
                    users.add(new User(username, password)); // Creating a user and adding to the array list
                } catch (IllegalArgumentException e) { // Catching the exceptions and writing the error as we were instructed
                    System.out.println(line);
                    System.out.println(e.getMessage());
                }
            }

            Collections.sort(users); // Sorting the ArrayList we created earlier

            for (User user : users) {
                writer.println(user); // Writing the sorted users into the output file
            }

        } catch (FileNotFoundException e) { // Catching file opening errors
            System.out.println("File error: " + e.getMessage());
        }
    }
}