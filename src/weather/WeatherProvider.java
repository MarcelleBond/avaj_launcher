package weather;

import aircraft.Coordinates;

public class WeatherProvider {
	private static WeatherProvider weatherProvider = null;
	private static String[] weather = new String[]{"SNOW", "RAIN", "SUN", "FOG"};

	private WeatherProvider() {
	}

	public static WeatherProvider getProvider() {
		if (weatherProvider == null)
			weatherProvider = new WeatherProvider();
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) {
		int place = coordinates.getLongitude() + coordinates.getLatitude() + coordinates.getHeight();
		return weather[place % 4];
	}
}
