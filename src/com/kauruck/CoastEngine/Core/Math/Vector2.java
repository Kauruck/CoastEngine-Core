package com.kauruck.CoastEngine.Core.Math;
//Vector math from https://www.mathsisfun.com/algebra/vectors.html [12.0.2021]
//https://www.mathsisfun.com/algebra/vectors-dot-product.html [12.01.2021]
//https://www.mathsisfun.com/algebra/vectors-cross-product.html [12.01.2021]
//https://stackoverflow.com/questions/243945/calculating-a-2d-vectors-cross-product [12.01.2021]

/**
 * A point in 2d space as offset from 0|0
 */
public class Vector2 {

    private double x;
    private double y;

    /**
     * Creates a zero vector2
     */
    public Vector2(){
        x = 0;
        y = 0;
    }

    /**
     * Creates a vector2 with x|y
     * @param x x
     * @param y y
     */
    public Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }

    /**
     * Adds to Vectors
     * @param b The second vector
     * @return The result
     */
    public Vector2 add(Vector2 b){
        return new Vector2(this.x + b.x, this.y + b.y);
    }

    /**
     * Subtracts to vectors
     * @param b The vector to subtract
     * @return The result
     */
    public Vector2 subtract(Vector2 b){
        return new Vector2(this.x  - b.x, this.y - b.y);
    }

    /**
     * Calculates the crossprodct
     * @param b The second vector
     * @return The crossproduct
     */
    public Vector3 crossProduct(Vector2 b){
        return new Vector3(0, 0, this.x * b.y - this.y * b.x);
    }

    /**
     * Calculates the dotproduct
     * @param b The second vector
     * @return The dotproduct
     */
    public double dot(Vector2 b){
        return this.x * b.x + this.y * b.y;
    }

    /**
     * Scales the vector
     * @param scalar The scalar
     * @return The scaled vector
     */
    public Vector2 scale(double scalar){
        return new Vector2(x*scalar,y*scalar);
    }

    /**
     * Calculates the magnitude
     * @return The magnitude
     */
    public double magnitude(){
        return Math.sqrt(x*x+y*y);
    }

    /**
     * Gets x
     * @return x
     */
    public double getX() {
        return x;
    }

    /**
     * Sets x
     * @param x value of x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Gets y
     * @return y
     */
    public double getY() {
        return y;
    }

    /**
     * Sets y
     * @param y value of y
     */
    public void setY(double y) {
        this.y = y;
    }
}
