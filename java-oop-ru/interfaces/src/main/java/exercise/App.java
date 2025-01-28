package exercise;

import java.util.Comparator;
import java.util.List;

// BEGIN
public class App {
    public static List<String> buildApartmentsList(List<Home> realEstate, int count) {
        return realEstate.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .map(Object::toString)
                .limit(count)
                .toList();
    }
}
// END
