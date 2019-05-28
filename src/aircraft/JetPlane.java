package aircraft;

import filemanager.WriteFile;
import weather.WeatherTower;

import java.io.IOException;

public class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weathertower;

	public JetPlane(String name, Coordinates coordinates) {
		super(name, coordinates);
	}


	public void updateConditions() {
		String newWeather = weathertower.getWeather(coordinates);

		if (newWeather == "SNOW") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 7);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("JetPlane#" + this.name + "(" + this.id + "): " + "I hope this snow don't freeze up my engine");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "RAIN") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 5, coordinates.getHeight());
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("JetPlane#" + this.name + "(" + this.id + "): " + "At least I'm not out in that rain");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "FOG") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 1, coordinates.getHeight());
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("JetPlane#" + this.name + "(" + this.id + "): " + "The enemy wont see me coming now");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "SUN") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude() + 10, coordinates.getHeight() + 2);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("JetPlane#" + this.name + "(" + this.id + "): " + "Good day for flying");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		if (coordinates.getHeight() <= 0) {
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("JetPlane#" + this.name + "(" + this.id + ")" + " landing.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			weathertower.unregister(this);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public void registerTower(WeatherTower weatherTower) {
		this.weathertower = weatherTower;
		this.weathertower.register(this);
		try {
			WriteFile data = new WriteFile("simulation.txt", true);
			data.writeToFile("Tower says: JetPlane#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
