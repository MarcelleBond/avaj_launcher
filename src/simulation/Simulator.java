package simulation;

import filemanager.FileFormat;
import filemanager.ReadFile;
import weather.WeatherTower;
import aircraft.AircraftFactory;

import java.io.IOException;



public class Simulator {
	public static void main(String[] args) {
		WeatherTower weatherTower = new WeatherTower();
		try {
			if (args.length == 0)
			{
				System.out.println("Please provide a file");
				System.exit(1);
			}
			ReadFile file = new ReadFile(args[0]);
			String[] info = file.OpenFile();
			FileFormat.fileCheck(info);
			int simulationTime = Integer.parseInt(info[0]);
			for (int n = 1; n < info.length; n++) {
				String[] aircraft = info[n].split(" ");
				if (aircraft[0].toLowerCase().equals("baloon") ||
						aircraft[0].toLowerCase().equals("helicopter") ||
						aircraft[0].toLowerCase().equals("jetplane")) {
					AircraftFactory.newAicraftFactory(aircraft[0], aircraft[1],
							Integer.parseInt(aircraft[2]),
							Integer.parseInt(aircraft[3]),
							Integer.parseInt(aircraft[4])).registerTower(weatherTower);
				}
				else{
					System.out.println("Error on line " + (n+1) + ": invalid aircraft");
					System.exit(1);
				}
			}
			for (int i = 0; i < simulationTime; i++) {
				weatherTower.changeWeather();
			}

		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}
