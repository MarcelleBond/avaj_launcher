package simulation;

import filemanager.ReadFile;
import weather.WeatherTower;
import aircraft.AircraftFactory;

import java.io.IOException;


public class Simulation {
	public static void main(String[] argv) {
		WeatherTower weatherTower = new WeatherTower();
		try {
			ReadFile file = new ReadFile("scenario");
			String[] info = file.OpenFile();
			int simulationTime = Integer.parseInt(info[0]);
			for(int n = 1; n < info.length; n++)
			{
				String[] aircraft = info[n].split(" ");
				if (aircraft[0].toLowerCase() == "baloon" ||
						aircraft[0].toLowerCase() == "helicopter" ||
						aircraft[0].toLowerCase() == "jetplane")
					AircraftFactory.newAicraftFactory(aircraft[0],aircraft[1],
							Integer.parseInt(aircraft[2]),
							Integer.parseInt(aircraft[3]),
							Integer.parseInt(aircraft[4]));
 			}
			for (int i = 0; i < simulationTime; i++) {
				weatherTower.changeWeather();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
