public class NBody{
  public static double readRadius(String fileName){
    In in = new In(fileName);
    in.readInt();
    double radius = in.readDouble();
    return radius;
  }
  public static Planet[] readPlanets(String fileName){
    In in = new In(fileName);
    int planet_count = in.readInt();
    in.readDouble();
    Planet[] planets = new Planet[planet_count];
    for (int i = 0; i < planet_count; i=i+1){
      double xxPos = in.readDouble();
      double yyPos = in.readDouble();
      double xxVel = in.readDouble();
      double yyVel = in.readDouble();
      double mass = in.readDouble();
      String imgFileName = in.readString();
      planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
    }
    return planets;
  }
  public static void main(String[] args) {
    double T = Double.parseDouble(args[0]);
    double dt = Double.parseDouble(args[1]);
    String filename = args[2];
    double radius = readRadius(filename);
    Planet[] planets = readPlanets(filename);
    StdDraw.setScale(-radius, radius);
    StdDraw.picture(0,0,"images/starfield.jpg");
    for (Planet pl: planets) {
            pl.draw();
        }

    StdDraw.enableDoubleBuffering();
    double t = 0;
    while (t<T){
      double[] xForce = new double[planets.length];
      double[] yForce = new double[planets.length];
      for (int i=0; i<planets.length;i++){
        xForce[i] = planets[i].calcNetForceExertedByX(planets);
        yForce[i] = planets[i].calcNetForceExertedByY(planets);
      }
      for (int i=0; i<planets.length;i++){
        planets[i].update(dt,xForce[i],yForce[i]);
      }
        StdDraw.picture(0,0,"images/starfield.jpg");
        for (Planet pl: planets) {
                pl.draw();
            }
        StdDraw.show();
        StdDraw.pause(10);
        t=t+dt;
      }
      StdOut.printf("%d\n", planets.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < planets.length; i++) {
       StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
}
    }
    }
