package com.kauruck.CoastEngine.Core.Math;
//Vector math from https://www.mathsisfun.com/algebra/vectors.html [12.0.2021]
//https://www.mathsisfun.com/algebra/vectors-dot-product.html [12.01.2021]
//https://www.mathsisfun.com/algebra/vectors-cross-product.html [12.01.2021]
//https://stackoverflow.com/questions/243945/calculating-a-2d-vectors-cross-product [12.01.2021]

/**
 * A point in 3d space offset form 0|0|0
 */
public class Vector3 {

    private double x;
    private double y;
    private double z;

    /**
     * Creates a zero vector3
     */
    public Vector3(){
        x = 0;
        y = 0;
        z = 0;
    }

    /**
     * Creates a vector3 with x|y|0
     * @param x x
     * @param y y
     */
    public Vector3(double x, double y){
        this.x = x;
        this.y = y;
        this.z = 0;
    }

    /**
     * Creates a vector3 with x|y|z
     * @param x x
     * @param y y
     * @param z z
     */
    public Vector3(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a vector3 from a vector2 with z=0
     * @param org The original vector2
     */
    public Vector3(Vector2 org){
        this.x = org.getX();
        this.y = org.getY();
        this.z = 0;
    }

    /**
     * Adds to vectors
     * @param b The second vector
     * @return The result
     */
    public Vector3 add(Vector3 b){
        return new Vector3(this.x + b.x, this.y + b.y, this.z + b.z);
    }

    /**
     * Subtracts to vectors
     * @param b The vector to subtract
     * @return The result
     */
    public Vector3 subtract(Vector3 b){
        return new Vector3(this.x - b.x, this.y - b.y, this.z - b.z);
    }

    /**
     * Calculates the dotproduct
     * @param b The second vector
     * @return The dotproduct
     */
    public double dot(Vector3 b){
        return this.x * b.x + this.y * b.x + this.z * b.z;
    }

    /**
     * Calculates the crossprodct
     * @param b The second vector
     * @return The crossproduct
     */
    public Vector3 crossProduct(Vector3 b){
        return new Vector3(this.y * b.z - this.z * b.y, this.z * b.x - this.x * b.z, this.x * b.y - this.y *b.x);
    }

    /**
     * Scales the vector
     * @param scalar The scalar
     * @return The scaled vector
     */
    public Vector3 scale(double scalar){
        return new Vector3(x*scalar, y*scalar,z*scalar);
    }

    /**
     * Calculates the magnitude
     * @return The magnitude
     */
    public double magnitude(){
        return Math.sqrt(x*x+y*y+z*z);
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

    /**
     * Gets z
     * @return z
     */
    public double getZ() {
        return z;
    }

    /**
     * Sets z
     * @param z value of z
     */
    public void setZ(double z) {
        this.z = z;
    }
}
