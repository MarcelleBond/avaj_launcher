package aircraft;

import filemanager.WriteFile;
import weather.WeatherTower;

import java.io.IOException;

public class Baloon extends Aircraft implements Flyable {
	private WeatherTower weathertower;

	protected Baloon(String name, Coordinates coordinates) {
		super(name, coordinates);
	}

	public void updateConditions() {
		String newWeather = weathertower.getWeather(coordinates);

		if (newWeather == "SNOW") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 15);
			try {
				WriteFile data = new WriteFile("./simulation.txt", true);
				data.writeToFile("Baloon#" + this.name + "(" + this.id + "): " + "Well we might all just freeze to death up here");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "RAIN") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 5);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Baloon#" + this.name + "(" + this.id + "): " + "If this Baloon fills with water, everybody better start drinking!");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "FOG") {
			coordinates = new Coordinates(coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight() - 3);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Baloon#" + this.name + "(" + this.id + "): " + "Well I guess this what it's like to fly blind");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		} else if (newWeather == "SUN") {
			coordinates = new Coordinates(coordinates.getLongitude() + 2, coordinates.getLatitude(), coordinates.getHeight() + 4);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Baloon#" + this.name + "(" + this.id + "): " + "I hope everyone has their sunscreen");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}

		if (coordinates.getHeight() <= 0) {
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Baloon#" + this.name + "(" + this.id + ")" + " landing.");
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
			weathertower.unregister(this);
			try {
				WriteFile data = new WriteFile("simulation.txt", true);
				data.writeToFile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " unregistered from weather tower.");
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
			data.writeToFile("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");
			System.out.println("Tower says: Baloon#" + this.name + "(" + this.id + ")" + " registered to weather tower.");

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
