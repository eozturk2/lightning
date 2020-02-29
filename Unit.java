package lightning;

import java.util.concurrent.ThreadLocalRandom;
import acm.graphics.GPoint;

public class Unit extends GPoint{
	boolean passed;
	int num = ThreadLocalRandom.current().nextInt(1,100);
	public Unit(double x, double y) {
		super(x,y);
	}}
