// Proj1 NBody.java//

public class NBody {

//ReadRadius

	public static double readRadius(String path) { // Why use "static"?

		In in = new In(path);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

//ReadNumbers

		public static int readNumbers(String path) {

		In in = new In(path);
		int num = in.readInt();
		return num;
	}

//ReadPlanets

	public static Planet[] readPlanets(String path) {

		In in = new In(path);
		int num = in.readInt();
		double r = in.readDouble();
		Planet[] planets = new Planet[num];
			for(int i = 0; i < num; i = i + 1) {
				double xpos = in.readDouble();
				double ypos = in.readDouble();
				double xv = in.readDouble();
				double yv = in.readDouble();
				double mass = in.readDouble();
				String imgname = in.readString();
				planets[i] = new Planet(xpos, ypos, xv, yv, mass, imgname);
			}
		return planets;
	}

//Drawing the Initial Universe State (main)

	public static void main(String[] args) {

		String t_s = args[0];
		double t = Double.parseDouble(t_s);
		String dt_s = args[1];
		double dt = Double.parseDouble(dt_s);

		String filename = args[2];

		Planet[] planets = readPlanets(filename);
		double radius = readRadius(filename);
		int num = readNumbers(filename);
		
		DrawBackground();
		//Drawing All of the Planets
		for(int i = 0; i < num; i = i + 1) {
			planets[i].draw();
		}

		StdDraw.enableDoubleBuffering(); //A graphics technique to prevent flickering in the animation
		//Creating an Animation
		for(double tc = 0; tc <= t; tc = tc + 100000) {
			double[] xForces = new double[num];
			double[] yForces = new double[num];
			for(int i = 0; i < num; i = i +1) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for(int i = 0; i < num; i = i + 1) {
				double xForce_i = planets[i].calcNetForceExertedByX(planets);
				double yForce_i = planets[i].calcNetForceExertedByY(planets);
				planets[i].update(100000, xForce_i, yForce_i);
			}
			DrawBackground();
			for(int i = 0; i < num; i = i + 1) {
				planets[i].draw();
			}
			StdDraw.pause(10);
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}

	public static String backgroundToDraw = "./images/starfield.jpg";
	
//Drawing the Background

	public static void DrawBackground() {
		
		StdDraw.setScale(-2.50e+11, 2.50e+11);
		StdDraw.clear();
		StdDraw.picture(0, 0, backgroundToDraw);
		StdDraw.show();
		//StdDraw.pause(5000);
	}
}