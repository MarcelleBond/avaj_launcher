package aircraft;

public class AircraftFactory {

	public static Flyable newAicraftFactory(String type, String name, int longitude, int latitude, int hight) {
		if (type.toUpperCase() == "JETPLANE") {
			return new JetPlane(name, new Coordinates(longitude, latitude, hight));
		}
		if (type.toUpperCase() == "BALOON") {
			return new Baloon(name, new Coordinates(longitude, latitude, hight));
		}
		if (type.toUpperCase() == "HELICOPTER") {
			return new Helicopter(name, new Coordinates(longitude, latitude, hight));
		}
		return null;
	}
}
