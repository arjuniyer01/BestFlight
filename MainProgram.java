import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class implements the user interface of GA Flights application
 * 
 */
public class MainProgram {

  private Scanner scan;
  protected ProgramMethods gaFunction;
  private MyGraph<Location>.Vertex travelDestination;
  private MyGraph<Location>.Vertex departureCity;
  private MyGraph<Location>.Vertex arrivalCity;

  static String menu =
      "*********************************************************************************\n"
          + "                                                                                *\n"
          + "USER MENU                                                                       *\n"
          + "                                                                                *\n"
          + "--> To find shortest and cheapest flights                      [Press 1]        *\n"
          + "--> To find hotels at your travel destination                  [Press 2]        *\n"
          + "--> To quit the application                                    [Press 3]        *\n"
          + "                                                                                *\n"
          + "*********************************************************************************";

  static String cities =
      "\n*********************************************************************************\n"
          + "*                    CITIES                  *                                  *\n"
          + "*                                            *                 _                *\n"
          + "* --> New York City     --> Los Angeles      *               -=\\`\\              *\n"
          + "* --> Madison           --> Orlando          *           |\\ ____\\_\\__           *\n"
          + "* --> Chicago           --> Seattle          *         -=\\c`\"\"\"\"\"\"\" \"`)         *\n"
          + "* --> Dallas            --> Austin           *            `~~~~~/ /~~`          *\n"
          + "* --> San Jose          --> Atlanta          *              -==/ /              *\n"
          + "* --> Philadelphia      --> Baltimore        *                '-'               *\n"
          + "*                                            *                                  *\n"
          + "*********************************************************************************\n";

  public MainProgram() throws FileNotFoundException {
    scan = new Scanner(System.in);
    gaFunction = new ProgramMethods();
    travelDestination = null;
    departureCity = null;
    arrivalCity = null;
  }

  public void userInterface() {
    boolean start = true;
    String input = "";

    System.out.println(
    		"This flight navigation app is designed to help you find the shortest            *\n"
            + "and cheapest flights between two locations. Moreover, you can use               *\n"
            + "this app to search for hotels at your travel destination!                       *\n"
            + "                                                                                *");

    while (start) {
      System.out.println(menu);
      System.out.print("\n>> ");
      input = scan.nextLine().trim().toLowerCase();

      if (input.equals("1")) {
        getFlightInfo();
      } else if (input.equals("2")) {
        getHotelInfo();
      } else if (input.equals("3")) {
        start = false;
      } else {
        System.out.println("\nInvalid input, please try again.\n");
      }
    }
  }


  private void getFlightInfo() {
    boolean running = true;
    String city = "";

    while (running) {
      System.out.println(cities);
      System.out.println("Enter city of departure from the list above\n");
      System.out.print(">> ");
      city = scan.nextLine().trim().toLowerCase();

      for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
        if (city.equals(location.data.getName().toLowerCase())) {
          departureCity = location;
          break;
        }
      }
      if (departureCity == null) {
        System.out.println("\nInvalid city of departure, please enter valid city name");
        continue;
      } else {
        boolean arrivalInput = (arrivalCity == null) ? false : true;
        while (!arrivalInput) {
          System.out.println(cities);
          System.out.println("Enter city of arrival from the list above\n");
          System.out.print(">> ");
          city = scan.nextLine().trim().toLowerCase();
          for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
            if (city.equals(location.data.getName().toLowerCase())
                && !city.equals(departureCity.data.getName().toLowerCase())) {
              arrivalCity = location;
              arrivalInput = true;
              break;
            }
          }
          if (arrivalInput == false) {
            System.out.println("\nInvalid city of arrival, please enter valid city name");
          }
        }
      }
      System.out.println(
          "\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
      gaFunction.printBothPaths(departureCity.data, arrivalCity.data);
      System.out.println(
          "+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
      System.out.println(
          "*********************************************************************************\n"
              + "                                                                                *\n"
              + "--> To go back to the main menu                              [Press 0]          *\n"
              + "--> To search for hotels at your travel destination          [Press 1]          *\n"
              + "--> To search for other flights                              [Press any key]    *\n"
              + "                                                                                *\n"
              + "*********************************************************************************\n");
      System.out.print(">> ");
      String userInput = scan.nextLine();
      if (userInput.trim().equals("1")) {
        travelDestination = arrivalCity;
        getHotelInfo();
        running = false;
      } else if (userInput.trim().equals("0")) {
        System.out.println();
        departureCity = null;
        arrivalCity = null;
        running = false;
      } else {
        departureCity = null;
        arrivalCity = null;
      }
    }
  }

  private void getHotelInfo() {
    // TODO: hotels at travel destination
    boolean running = true;
    String city = "";
    while (running) {
      if (travelDestination == null) {
        System.out.println(cities);
        System.out.println("Select a travel destination from the list above\n");
        System.out.print(">> ");
        city = scan.nextLine().trim().toLowerCase();
        for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
          if (city.equals(location.data.getName().toLowerCase())) {
            travelDestination = location;
            break;
          }
        }
        if (travelDestination == null) {
          System.out.println("\nInvalid travel destination, please enter valid city name");
          continue;
        }
      } else {
        System.out.println(
            "\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+");
        System.out
            .println("Here are a list of hotels in " + travelDestination.data.getName() + ":\n");
        travelDestination.data.printHotelList();
        System.out
            .print("\nBest Hotel for the price in " + travelDestination.data.getName() + ":\t");
        System.out.println(travelDestination.data.bestHotel().getName()
            + "\n+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+\n");
        System.out.println(
            "*********************************************************************************\n"
                + "                                                                                *\n"
                + "--> To go back to the main menu                              [Press 0]          *\n"
                + "--> To search for flights to your travel destination         [Press 1]          *\n"
                + "--> To search for other hotels                               [Press any key]    *\n"
                + "                                                                                *\n"
                + "*********************************************************************************\n");
        System.out.print(">> ");
        String userInput = scan.nextLine();
        if (userInput.trim().equals("1")) {
          arrivalCity = travelDestination;
          getFlightInfo();
          running = false;
        } else if (userInput.trim().equals("0")) {
          System.out.println();
          travelDestination = null;
          running = false;
        } else {
          travelDestination = null;
        }
      }
    }
  }



  public static void main(String[] args) throws FileNotFoundException {
    MainProgram flights = new MainProgram();
    System.out.println(
        "*********************************************************************************\n"
            + "*                                                                                  *\n"
            + "*        __|__                     WELCOME!                          __|__         *\n"
            + "*  *---o--(_)--o---*                                           *---o--(_)--o---*   *\n"
            + "*                                                                               *\n"
            + "*********************************************************************************\n"
            + "                                                                                *");
    flights.userInterface();
    System.out.println(
        "\n*********************************************************************************\n"
            + "*                          *                         *                          *\n"
            + "*          __|__           *      THANK YOU!         *           __|__          *\n"
            + "*    *---o--(_)--o---*     *                         *     *---o--(_)--o---*    *\n"
            + "*                          *                         *                          *\n"
            + "*********************************************************************************\n");
  }
}