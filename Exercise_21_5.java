import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class Exercise_21_5 {

  public static void main(String[] args) {

    if (args.length < 2) {
      System.out.println("Usage: java Exercise_21_5 input.java output.html"); // Will trigger when
                                                                              // insufficient amount
                                                                              // of files are
                                                                              // provided
      System.exit(1);
    }

    String javaFile = args[0]; // Sets the input file name into String javaFile
    String htmlFile = args[1]; // Sets the output file name into String htmlFile

    System.out.println("Reading file: " + javaFile);

    try {
      List<String> fileLines = Files.readAllLines(Paths.get(javaFile)); // Creates a List fileLines
                                                                        // built from each line from
                                                                        // input file (java)
      System.out.println("reading complete. \nMapping input file to HTML file...");

      ArrayList<String> htmlFileLines = mappingMethod.convertToHTML(fileLines); // Calls
                                                                                // convertToHTML
                                                                                // method with input
                                                                                // file as the
                                                                                // argument

      System.out.println("Mapping complete...");
      System.out.println("Writing HTML file: " + htmlFile);

      Files.write(Paths.get(htmlFile), htmlFileLines, StandardOpenOption.CREATE); // Writes the
                                                                                  // converted text
                                                                                  // into the output
                                                                                  // file (html)
      System.out.println("Writing HTML file complete.");

    } catch (IOException e) {
      System.out.println("Something went wrong during read/convert/write."); // Will catch if
                                                                             // exception is thrown
                                                                             // from Files
      System.exit(0);
    }

  }

}
