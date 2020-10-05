package com.example.assignmentseven.GameUtils;

public class Point {
    public int x, y;

    // Constructors
    public Point(){
        x = 0; y= 0;
    }
    public Point(int x, int y){
        this.x = x; this.y = y;
    }

    // sets the position
    public void set(int x, int y){
        this.x = x; this.y = y;
    }

    // adds to the position
    public void add(int x, int y){
        this.x += x; this.y += y;
    }

    // adds to the position with a vector
    public void add(Vector v){
        x += Math.round(v.x);
        y += Math.round(v.y);
    }

    // copy returns a copy of this point
    public Point copy(){
        return new Point(x,y);
    }

    // distanceTo returns the distance between points
    public double distanceTo(Point other){
        return new Vector(x - other.x, y - other.y).mag();
    }
}
