public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p){
		double res = Math.sqrt(((xxPos - p.xxPos) * (xxPos - p.xxPos) + (yyPos - p.yyPos) * (yyPos - p.yyPos)));
		return res;
	}

	public double calcForceExertedBy(Planet p){
		double r = calcDistance(p);
		double res = 6.67e-11 * mass * p.mass / (r * r);
		return res;
	}

	public double calcForceExertedByX(Planet p){
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double res = F * (p.xxPos - xxPos) / r;
		return res;
	}

	public double calcForceExertedByY(Planet p){
		double F = calcForceExertedBy(p);
		double r = calcDistance(p);
		double res = F * (p.yyPos - yyPos) / r;
		return res;
	}

	public double calcNetForceExertedByX(Planet[] p){
		int len = p.length;
		double res = 0;
		for(int i = 0; i < len; i = i + 1){
			if(equals(p[i])){
				continue;
			}else{
				res = res + calcForceExertedByX(p[i]);
			}
		}
		return res;
	}

	public double calcNetForceExertedByY(Planet[] p){
		int len = p.length;
		double res = 0;
		for(int i = 0; i < len; i = i + 1){
			if(equals(p[i])){
				continue;
			}else{
				res = res + calcForceExertedByY(p[i]);
			}
		}
		return res;
	}

	public boolean equals(Planet p){
		if(calcDistance(p) == 0){
			return true;
		}else{
			return false;
		}
	}

	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel = xxVel + aX * dt;
		yyVel = yyVel + aY * dt;
		xxPos = xxPos + xxVel * dt;
		yyPos = yyPos + yyVel * dt;
		return;
	}

	public void draw(){
		String filename = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, filename);
	}
}