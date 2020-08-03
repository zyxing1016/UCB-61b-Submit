// Proj1 Planet setup//

//The Planet Class and Its Constructor

public class Planet {

//instance variables

	public double xxPos; //Its current x position
	public double yyPos; //Its current y position
	public double xxVel; //Its current velocity in the x direction
	public double yyVel; //Its current velocity in the y direction
	public double mass; //Its mass
	public String imgFileName; //The name of the file that corresponds to the image that depicts the planet 

//Constructor for Planet

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName =img;
	}

	public Planet(Planet p) {
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

//calcDistance

	public double calcDistance(Planet p) {
		double x = this.xxPos - p.xxPos;
		double y = this.yyPos - p.yyPos;
		double r = Math.sqrt(x*x + y*y);

		return r;
	}

//calcForceExertedBy

	double g = 6.67e-11;

	public double calcForceExertedBy(Planet p) {
		double f1 = g*(p.mass*this.mass)/(this.calcDistance(p)*this.calcDistance(p));
		return f1;
	}

//calcForceExertedByX and calcForceExertedByY

	public double calcForceExertedByX(Planet p) {
		double fx1 = this.calcForceExertedBy(p)*(p.xxPos - this.xxPos)/this.calcDistance(p);
		return fx1;
	}

	public double calcForceExertedByY(Planet p) {
		double fy1 = this.calcForceExertedBy(p)*(p.yyPos - this.yyPos)/this.calcDistance(p);
		return fy1;
	}

//calcNetForceExertedByX and calcNetForceExertedByY

	public double calcNetForceExertedByX(Planet[] p) {

		double fx = 0;
		for (Planet s : p) {
			if (this.equals(s)) {
				continue;
			}
			fx = fx + this.calcForceExertedByX(s);	
		}
		return fx;
	}

	public double calcNetForceExertedByY(Planet[] p){

		double fy = 0;
		for (Planet s : p) {
			if (this.equals(s)) {
				continue;
			}
			fy = fy + this.calcForceExertedByY(s);	
		}
		return fy;
	}

//update

	public void update(double dt, double fx, double fy) {

		double ax = fx/this.mass;
		double ay = fy/this.mass;
		double v_newx = this.xxVel + ax*dt;
		double v_newy = this.yyVel + ay*dt;
		double p_newx = this.xxPos + v_newx*dt;
		double p_newy = this.yyPos + v_newy*dt;

		this.xxPos = p_newx;
		this.yyPos = p_newy;
		this.xxVel = v_newx;
		this.yyVel = v_newy;
	}

//Drawing One Planet

	public void draw() {

		String planetToDraw = "./images/" + this.imgFileName;
		StdDraw.picture(this.xxPos, this.yyPos, planetToDraw);
		StdDraw.show();
	}

}
