public class NBody{
	public static double readRadius(String filename){
		In in = new In(filename);
		int n = in.readInt();
		double res = in.readDouble();
		return res;
	}

	public static Planet[] readPlanets(String filename){
		In in = new In(filename);
		int n = in.readInt();
		double r = in.readDouble();
		Planet[] res = new Planet[n];
		for(int i = 0; i < n; i = i + 1){
			res[i] = new Planet(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return res;
	}

	public static void main(String[] args){
		StdDraw.enableDoubleBuffering();
		double t = 0;
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double r = readRadius(filename);
		Planet[] p = readPlanets(filename);
		int len = p.length;

		StdDraw.setScale(-r, r);

		StdDraw.clear();

		while(t < T){
			/** Create an xForces array and a yForces array */
			double[] xForces = new double[len];
			double[] yForces = new double[len];
			/** Calculate the net forces for each planet */
			for(int i = 0; i < len; i = i + 1){
				xForces[i] = p[i].calcNetForceExertedByX(p);
				yForces[i] = p[i].calcNetForceExertedByY(p);
			}

			for(int i = 0; i < len; i = i + 1){
				p[i].update(dt, xForces[i], yForces[i]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg");

			/** Draw all of the planets */
			for(int i = 0; i < len; i = i + 1){
				p[i].draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t = t + dt;
		}
		

		
	}
}