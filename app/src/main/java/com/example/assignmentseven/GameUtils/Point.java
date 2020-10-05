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

    // copy returns a copy of this point
    public Point copy(){
        return new Point(x,y);
    }
}
