package weather;

import aircraft.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = new WeatherProvider();
	private static String[] weather = new String[]{"SNOW", "RAIN", "SUN", "FOG"};

	private WeatherProvider() {
	}

	public static WeatherProvider getProvider() {
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int place = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
		return weather[place % 4];
	}
}
