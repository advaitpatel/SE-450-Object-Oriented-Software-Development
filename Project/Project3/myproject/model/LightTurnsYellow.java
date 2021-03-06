package myproject.model;

import java.awt.Color;

final class LightTurnsYellow implements LightStateControl {

	@Override
	public String getState() {
		return "YELLOW";
	}

	@Override
	public Color getColor() {
		return Color.YELLOW;
	}

	@Override
	public LightStateControl next() {
		return new LightTurnsRed();
	}

	@Override
	public double time(double green, double yellow) {
		return yellow;
	}

	@Override
	public double obstacleDistance(Car car, double frontPosition, Light light) {
		double distance = Double.POSITIVE_INFINITY;
		if (car.getNSCar() && car.getCurrentRoad() != light) {
			distance = car.getCurrentRoad().getRoadEnd() - frontPosition;
		}
		if (!car.getNSCar()) {
			if (car.getMaxVelocity() > car.getCurrentRoad().getRoadEnd()
					- frontPosition + light.getRoadEnd()) {
				distance = Double.POSITIVE_INFINITY;
			} else {
				distance = car.getCurrentRoad().getRoadEnd() - frontPosition;
			}
		}
		return distance;
	}

}
