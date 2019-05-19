package algorithms.DP;

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
 * If we use 4 directions for each point, there will be a lot of duplicates.
 * So we will use 2 directions only: 
 * 		right (for right to left consecutive 1 processing)
 * 		down (for top to bottom consecutive 1 processing)
 *  
 * class Point {
 * 	private int final right;
 *  private int final down;
 * }
 * 
 * input matrix: Point[][]
 * 
 * preprocess all points to get two matrices:
 * 	rightArms: right to left largest consecutive 1s 
 * 	downArms: bottom to top largest consecutive 1s
 * 
 * then find largest square as in largestSquareSurroundedByOnes()
 * */
public class LargestSquareFromMatches {

}
