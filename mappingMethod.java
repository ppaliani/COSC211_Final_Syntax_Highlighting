import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class mappingMethod {

  private static final String KEYWORD = "keyword_style_class"; // keyword class assignment
  private static final String COMMENT = "comment_style_class"; // comment class assignment
  private static final String TYPE = "types_style_class"; // types class assignment
  private static final String LITERAL = "literals_style_class"; // types class assignment

  // CSS styling
  private static final String CSS_STYLE =
      "div{display: inline-block}" + ".none{font-weight: normal;color:black;}"
          + " .keyword_style_class {font-weight: bold;color: navy;}"
          + " .comment_style_class {font-weight: bold; color: green;}"
          + " .types_style_class {font-weight: bold; color: red;}"
          + " .literals_style_class {font-weight: bold; color: blue;}";

  // Array of keywords
  private static final String[] KEYWORDS = {"abstract", "assert", "boolean", "break", "byte",
      "case", "catch", "char", "class", "const", "continue", "default", "do", "double", "else",
      "enum", "extends", "for", "final", "finally", "float", "goto", "if", "implements", "import",
      "instanceof", "int", "interface", "long", "native", "new", "package", "private", "protected",
      "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized", "this",
      "throw", "throws", "transient", "try", "void", "volatile", "while", "true;", "false;",
      "null"};

  // Array of types
  private static final String[] TYPES =
      {"byte", "short", "int", "long", "float", "double", "char", "String", "boolean"};



  // Big O-Notation of convertToHTML method: O(n)*O(n)*O(n)+O(n)+O(n)+O(n)+O(n) = O(n^3)
  // Big O-Notation of convertToHTML method: O(n)*O(n)*O(n)*O(n)*O(n)*O(n)*O(n) = O(n^3)
  // Method writes and converts input java file to output html file
  public static ArrayList<String> convertToHTML(List<String> javaFileLines) {
    ArrayList<String> htmlTags = new ArrayList<>();
    htmlTags.add("<html lang=\\\"en\\\">");
    htmlTags.add("<head>");
    htmlTags.add("<style>");
    htmlTags.add(CSS_STYLE);
    htmlTags.add("</style>");
    htmlTags.add("<title>" + "COSC 211 Final Project" + "</title>");
    htmlTags.add("</head>");
    htmlTags.add("<body>");

    Set<String> keywordsSet = new HashSet<>(Arrays.asList(KEYWORDS)); // HashSet built from array of
                                                                      // keywords
    Set<String> typesSet = new HashSet<>(Arrays.asList(TYPES)); // HashSet build from array of types

    for (String javaFileLine : javaFileLines) { // Iterates through every line // O(n)
      StringBuilder sb = new StringBuilder();
      sb.append("<div>"); // Opening Tag (Start of html code line)
      String[] words = javaFileLine.split(" "); // Splits the string by every whitespace

      for (int i = 0; i < words.length; i++) { // Iterates through every word // O(n)

        if (keywordsSet.contains(words[i])) { // If the word is a keyword, put it in the keyword
                                              // class
          sb.append("<div class =\"" + KEYWORD + "\">"); // Starts an html line under the
                                                         // keyword class
          if (words[i].compareTo("true;") == 0 || words[i].compareTo("false;") == 0) {
            String[] bools = words[i].split("");
            for (int b = 0; b < bools.length - 1; b++) { // O(n)
              sb.append(bools[b]);
            }
            sb.append("</div>"); // Close keyword tag
            sb.append(bools[bools.length - 1]).append("&nbsp;");
            continue;
          } else {
            sb.append(words[i]).append("&nbsp;"); // Puts the single keyword into the html
                                                  // line
            sb.append(" </div>"); // Closing tag
            continue;
          }

        }

        if (words[i].startsWith("//")) { // If the line is a comment, put it in the comment class
          sb.append("<div class=\"" + COMMENT + "\">"); // Starts an html line under the
                                                        // comment class
          while (i < words.length) { // Put the rest of the line in the comment class // O(n)
            sb.append(words[i]).append("&nbsp;");
            i++;
          }
          sb.append(" </div>"); // Closing Tag
          continue;
        }

        if (typesSet.contains(words[i])) { // If the word is a type, put it in the type class
          sb.append("<div class =\"" + TYPE + "\">"); // Starts an html line under the
                                                      // type class
          sb.append(words[i]).append("&nbsp;"); // Puts the single keyword into the html
                                                // line
          sb.append(" </div>"); // Closing tag
          continue;
        }

        if (words[i].startsWith("\"")) {
          sb.append("<div class=\"" + LITERAL + "\">"); // Start of literal html tag
          String[] word = words[i].split("");
          if (words[i].charAt(words[i].length() - 2) == 34) {
            for (int j = 0; j < word.length - 1; j++) { // O(n)
              sb.append(word[j]);
            }
            sb.append("</div>"); // Close literal tag
            sb.append(word[word.length - 1]).append("&nbsp;");
          } else if (words[i].charAt(words[i].length() - 2) != 34) {
            sb.append(words[i]).append("&nbsp;");
          }
          continue;
        }

        if (words[i].endsWith("\";")) {
          String[] currWord = words[i].split("");
          for (int j = 0; j < currWord.length - 1; j++) { // O(n)
            sb.append(currWord[j]);
          }
          sb.append("</div>"); // Close literal tag
          sb.append(currWord[currWord.length - 1]).append("&nbsp;");
          continue;
        }

        if (words[i].startsWith("\'")) {
          String[] currChar = words[i].split("");
          sb.append("<div class=\"" + LITERAL + "\">"); // Start of literal html tag
          for (int c = 0; c < currChar.length - 1; c++) { // O(n)
            sb.append(currChar[c]);
          }
          sb.append("</div>"); // Close literal tag
          sb.append(currChar[currChar.length - 1]).append("&nbsp;");
          continue;
        }

        if (words[i].isEmpty()) {
          sb.append("&nbsp;");
        } else {
          sb.append(words[i]).append("&nbsp;"); // If not part of any classes, make it the
                                                // default styling
        }
      }
      htmlTags.add(sb + "</div><br>"); // closing tags
    }
    htmlTags.add("</body>"); // closing body tag
    htmlTags.add("</html>"); // closing html tag

    return htmlTags;
  }
}
