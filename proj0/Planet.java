public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

  private static final double G=6.67e-11;

	public Planet(double xP, double yP, double xV, double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass =  m;
		imgFileName = img;
	}
    public Planet(Planet p){
    	this.xxPos = p.xxPos;
    	this.yyPos = p.yyPos;
    	this.xxVel = p.xxVel;
    	this.yyVel = p.yyVel;
    	this.mass = p.mass;
    	this.imgFileName = p.imgFileName;

    }
    public double calcDistance(Planet p){
    	double dx=this.xxPos - p.xxPos;
    	double dy=this.yyPos - p.yyPos;
    	return Math.sqrt(dx*dx + dy*dy);
    }
    public double calcForceExertedBy(Planet p){
    	double r= calcDistance(p);
    	return G*this.mass*p.mass / (r*r);
    }
    public double calcForceExertedByX(Planet p){
       return calcForceExertedBy(p) * (p.xxPos - this.xxPos) / calcDistance(p);
		}
    public double calcForceExertedByY(Planet p){
    	 return calcForceExertedBy(p) * (p.yyPos - this.yyPos) / calcDistance(p);
    }
    public double calcNetForceExertedByX(Planet[] p){
    	double netFx = 0.0;
			for (Planet pl: p) {
            if (!this.equals(pl))
                netFx += calcForceExertedByX(pl);
    	}
    	return netFx;
    }
    public double calcNetForceExertedByY(Planet[] p){
    	double netFy = 0.0;
			for (Planet pl: p) {
            if (!this.equals(pl))
                netFy += calcForceExertedByY(pl);}
    	return netFy;
		}
	

    public void update(double dt, double fX, double fY){
    	double aNetX= fX/this.mass;
    	double aNetY= fY/this.mass;
    	this.xxVel = this.xxVel + dt*aNetX;
    	this.yyVel = this.yyVel + dt*aNetY;
    	this.xxPos = this.xxPos + dt*this.xxVel;
    	this.yyPos = this.yyPos + dt*this.yyVel;
    }
		public void draw(){
			StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
			StdDraw.show();
		}
 }
