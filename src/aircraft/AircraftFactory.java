package aircraft;

public class AircraftFactory {

	public static Flyable newAicraftFactory(String type, String name, int longitude, int latitude, int height) {
		if (type.toUpperCase().equals("JETPLANE")) {
			return new JetPlane(name, new Coordinates(longitude, latitude, height));
		}
		if (type.toUpperCase().equals("BALOON")) {
			return new Baloon(name, new Coordinates(longitude, latitude, height));
		}
		if (type.toUpperCase().equals("HELICOPTER")) {
			return new Helicopter(name, new Coordinates(longitude, latitude, height));
		}
		return null;
	}
}
