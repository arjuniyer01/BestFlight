import org.junit.jupiter.api.Test;
import static org.junit.Assert.fail;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;

/**
 * This class implements test methods for GAFlights program.
 * 
 */
public class ProgramTester extends MyGraph<Location> {
  public MyGraph<Location> price;
  public MyGraph<Location> duration;
  public Data loader;
  public MyGraph<Location> cities;
  public MyGraph<Location>.Vertex departureCity;
  public MyGraph<Location>.Vertex arrivalCity;
  public ProgramMethods gaFunction;
  public MyGraph<Location>.Vertex travelDestination;

  @Test
  /**
   * This test checks if the path between two cities returns the correct price.
   * 
   * @throws FileNotFoundException
   */
  public void checkPathPrice() throws FileNotFoundException {

    gaFunction = new ProgramMethods();
    MyGraph<Location>.Vertex departureCity = null;
    MyGraph<Location>.Vertex arrivalCity = null;
    String city = "chicago";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city.equals(location.data.getName().toLowerCase())) {
        departureCity = location;
        break;
      }
    }
    String city2 = "orlando";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city2.equals(location.data.getName().toLowerCase())
          && !city2.equals(departureCity.data.getName().toLowerCase())) {
        arrivalCity = location;

        break;
      }
    }

    Path pricePath = gaFunction.price.dijkstrasShortestPath(departureCity.data, arrivalCity.data);

    int temp = pricePath.distance;


    if (temp != 114) {
      fail();
    }



  }

  @Test
  /**
   * This test checks if the program returns the cheapest hotel in a given city.
   * 
   * @throws FileNotFoundException
   */
  public void getHotelInfoTest() throws FileNotFoundException {

    gaFunction = new ProgramMethods();
    String city = "chicago";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city.equals(location.data.getName().toLowerCase())) {
        travelDestination = location;
        break;
      }
    }
    if (travelDestination.data.bestHotel().getName() != "Godfrey") {
      fail();
    }

  }

  @Test
  /**
   * This test checks if the path between two cities returns the correct distance.
   * 
   * @throws FileNotFoundException
   */
  public void getFlightInfoTest() throws FileNotFoundException {

    gaFunction = new ProgramMethods();
    MyGraph<Location>.Vertex departureCity = null;
    MyGraph<Location>.Vertex arrivalCity = null;
    String city = "chicago";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city.equals(location.data.getName().toLowerCase())) {
        departureCity = location;
        break;
      }
    }
    String city2 = "orlando";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city2.equals(location.data.getName().toLowerCase())
          && !city2.equals(departureCity.data.getName().toLowerCase())) {
        arrivalCity = location;

        break;
      }
    }

    Path durationPath =
        gaFunction.duration.dijkstrasShortestPath(departureCity.data, arrivalCity.data);

    int temp = durationPath.distance;


    if (temp != 159) {
      fail();
    }


  }

  @Test
  /**
   * This test checks if the user interface runs correctly given inputs.
   * 
   * @throws FileNotFoundException
   */
  public void userInterfaceTest() {
    String userInput = "1" + System.getProperty("line.separator") + "Chicago"
        + System.getProperty("line.separator") + "Orlando" + System.getProperty("line.separator")
        + "0" + System.getProperty("line.separator") + "3";

    System.setIn(new ByteArrayInputStream(userInput.getBytes()));
    try {
      // run the application and check program flow
      MainProgram flights = new MainProgram();
      flights.userInterface();
    } catch (Exception e) {
      e.printStackTrace();
      fail("The display interface interface is incorrect");
    }
  }

  @Test
  /**
   * This test checks if the path between two cities returns the correct score when path has
   * stopover/no stopover.
   * 
   * @throws FileNotFoundException
   */
  public void getScoreTest() throws FileNotFoundException {

    gaFunction = new ProgramMethods();
    MyGraph<Location>.Vertex departureCity = null;
    MyGraph<Location>.Vertex arrivalCity = null;
    String city = "chicago";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city.equals(location.data.getName().toLowerCase())) {
        departureCity = location;
        break;
      }
    }
    String city2 = "orlando";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city2.equals(location.data.getName().toLowerCase())
          && !city2.equals(departureCity.data.getName().toLowerCase())) {
        arrivalCity = location;

        break;
      }
    }

    Path durationPath =
        gaFunction.duration.dijkstrasShortestPath(departureCity.data, arrivalCity.data);

    int temp = 11 - durationPath.dataSequence.size();


    if (temp != 9) {
      fail();
    }
  }

  @Test
  /**
   * This test checks if the data has been loaded in correctly by checking a vertex to see if it
   * contains the city.
   * 
   * @throws FileNotFoundException
   */
  public void loadDataTest() throws FileNotFoundException {

    gaFunction = new ProgramMethods();
    String city = "chicago";
    for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
      if (city.equals(location.data.getName().toLowerCase())) {
        travelDestination = location;
        break;
      }
    }
    if (!gaFunction.price.containsVertex(travelDestination.data)) {
      fail();
    }


  }

	@Test
  /**
   * This test checks if the printPath function runs correctly.
   * 
   * @throws FileNotFoundException
   */
  public void printPathsTest() throws FileNotFoundException {
    try {

      gaFunction = new ProgramMethods();
      MyGraph<Location>.Vertex departureCity = null;
      MyGraph<Location>.Vertex arrivalCity = null;
      String city = "chicago";
      for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
        if (city.equals(location.data.getName().toLowerCase())) {
          departureCity = location;
          break;
        }
      }
      String city2 = "orlando";
      for (MyGraph<Location>.Vertex location : gaFunction.price.vertices.values()) {
        if (city2.equals(location.data.getName().toLowerCase())
            && !city2.equals(departureCity.data.getName().toLowerCase())) {
          arrivalCity = location;

          break;
        }
      }
      gaFunction.printBothPaths(departureCity.data, arrivalCity.data);
    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

}