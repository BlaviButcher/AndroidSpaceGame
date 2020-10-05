package com.example.assignmentseven.GameUtils;

public class Vector {
    public double x, y;

    // Constructors
    public Vector(){
        x = 0; y = 0;
    }
    public Vector(double x, double y){
        this.x = x; this.y = y;
    }
    public Vector(Point a, Point b){
        x = b.x - a.x; y = b.y - a.y;
    }

    // Returns the magnitude of the vector
    public double mag(){
        return Math.sqrt(x * x + y * y);
    }

    // Returns the new unit vector
    public Vector unit(){
        double m = this.mag();
        x= x/m; y = y/m;
        return this;
    }

    // adds the other vector to this vector.
    public Vector add(Vector other){
        x += other.x;
        y += other.y;
        return this;
    }

    // dot product of the two vectors
    public Vector dot(Vector other){
        x *= other.x;
        y *= other.y;
        return this;
    }

    // returns a copy of the two vectors
    public Vector copy(){
        return new Vector(x,y);
    }

    // scales the vector by factor
    public Vector scale(double factor){
        x *= factor; y *= factor;
        return this;
    }
}
