package myproject.model;

import java.util.List;

public final class Inventory {

	/*
	 * spawning instances of cars
	 */
	public static Car newCar() {
		return new Car(MP.getCarLength(), MP.getMaxVelocity(),
				MP.getBrakeDistance(), MP.getStopDistance());
	}

	/*
	 * spawning instances of Roads
	 */
	public static CarDirections newEWRoad() {
		return new RoadDirectionEastWest(MP.roadLength);
	}

	public static CarDirections newNSRoad() {
		return new RoadDirectionNorthSouth(MP.roadLength);
	}

	public static CarDirections newSink() {
		return new CarFailure(1);
	}

	public static CarDirections newSource() {
		return new CarShowCase(0);
	}

	public static CarDirections newLight() {
		return new Light(MP.getIntersectionLength());
	}

	/*
	 * package private. static calculation method to help CarAcceptors tell
	 * there cars where they should be.
	 */

	static double distanceToCarBack(Car car, double fromPosition,
			List<Car> cars, CarDirections road) {
		double carBackPosition = Double.POSITIVE_INFINITY;
		if (car.getCurrentRoad() == road) {
			for (Car c : cars)
				if (c != car && c.getBackPosition() >= fromPosition
						&& c.getBackPosition() < carBackPosition)
					carBackPosition = c.getBackPosition() - 1;
		} else {
			for (Car c1 : cars) {
				if (c1.getBackPosition() <= 1)
					carBackPosition = c1.getBackPosition();
				else if (c1.getBackPosition() >= fromPosition
						&& c1.getBackPosition() < carBackPosition)
					carBackPosition = c1.getBackPosition() - 1;
			}
		}
		return carBackPosition - fromPosition
				+ car.getCurrentRoad().getRoadEnd();
	}
}
