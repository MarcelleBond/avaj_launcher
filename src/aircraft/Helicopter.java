package aircraft;

import filemanager.WriteFile;
import weather.WeatherTower;

import java.io.IOException;

public class Helicopter extends Aircraft implements Flyable {
	private WeatherTower weathertower;

	protected Helicopter(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String newWeather = weathertower.getWeather(coordinates);

		if (newWeather == "SNOW") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 12);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Helicopter#" + this.name + "(" + this.id + "): " + "Well if we crash at least it will be a soft landing");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "RAIN") {
			coordinates = new Coordinates(coordinates.getLongitude() + 5, coordinates.getLatitude(), coordinates.getHeight());
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Helicopter#" + this.name + "(" + this.id + "): " + "There ain't no sunshine when she's gone");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "FOG") {
			coordinates = new Coordinates(coordinates.getLongitude() + 1, coordinates.getLatitude(), coordinates.getHeight());
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Helicopter#" + this.name + "(" + this.id + "): " + "Well ain't that a ...");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "SUN") {
			coordinates = new Coordinates(coordinates.getLongitude() + 10, coordinates.getLatitude(), coordinates.getHeight() + 2);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Helicopter#" + this.name + "(" + this.id + "): " + "I'll be flying high with the sun in my eyes");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		if (coordinates.getHeight() <= 0) {
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Helicopter#" + this.name + "(" + this.id + ")" + " landing.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			weathertower.unregister(this);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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
			data.writeToFile("Tower says: Helicopter#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
