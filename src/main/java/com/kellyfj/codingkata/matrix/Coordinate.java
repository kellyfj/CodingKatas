package com.kellyfj.codingkata.matrix;

public class Coordinate {

    public int x;
    public int y;

    public Coordinate(int x, int y) {
        this.x=x;
        this.y=y;
    }
    
    @Override
    public String toString() {
        return "("+x+","+y+")";
    }

    @Override
    public boolean equals(Object e) {
        if (e == null)
            return false;
        if (!(e instanceof Coordinate))
            return false;
        Coordinate eCoord = (Coordinate) e;
        return ((eCoord.x == this.x) && (eCoord.y == this.y));

    }
}
