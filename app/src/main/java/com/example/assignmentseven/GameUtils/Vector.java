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

    // Returns the magnitude of the vector
    public double mag(){
        return Math.sqrt(x * x + y * y);
    }

    // Returns the new unit vector
    public Vector unit(){
        double m = this.mag();
        return  new Vector(x/m, y/m);
    }

    // adds the other vector to this vector.
    public void add(Vector other){
        x += other.x;
        y += other.y;
    }

    // dot product of the two vectors
    public void dot(Vector other){
        x *= other.x;
        y *= other.y;
    }

    // returns a copy of the two vectors
    public Vector copy(){
        return new Vector(x,y);
    }
}
