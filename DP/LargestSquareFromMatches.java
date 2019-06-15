package DP;

/**
 * given a graph made from matches, find the largest square
 * matches perpendicular
 * _______
 * |   |
 * |___|
 * |  |
 * |__|
 * 
 * graph modeling using oo design
 * how to represent each point?
 * 	- if we use 1 to represent a match, it does not contain direction information
 * 	- we can model each point (0,0), (0,1)...
 *  - but each point can have matches in 2 directions: right, down
 *  - Point at (0,0) {right = true; down = false;}
 *  - then the input is Point[][]
 *  
 *  
 * If we use 4 directions for each point, there will be a lot of duplicates.
 * So we will use 2 directions only: 
 * 		right (for right to left consecutive 1 processing)
 * 		down (for top to bottom consecutive 1 processing)
 *  
 * preprocess all points to get two matrices:
 * 	rightArms: right to left largest consecutive 1s 
 * 	downArms: bottom to top largest consecutive 1s
 * 
 * then find largest square as in largestSquareSurroundedByOnes()
 * */
public class LargestSquareFromMatches {

}

class Point {
	private boolean right;
	private boolean bottom;
	
	public Point(boolean right, boolean bottom) {
		this.right = right;
		this.bottom = bottom;
	}
}