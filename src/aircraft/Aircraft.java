package aircraft;

public abstract class Aircraft {
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	static private long idCounter;

	protected Aircraft(String name, Coordinates coordinates) {
		this.name = name;
		this.coordinates = coordinates;
		this.id = nextId();
	}

	private long nextId() {
		return ++idCounter;
	}
}
