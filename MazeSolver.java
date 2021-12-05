///////////////////////////////////////////////////////////////////////////////
//
// File:               MazeSolver.java
// Quarter:            CSE12 Winter 2021
//
// Author:             Mingyi Li
// Email:              mil011@ucsd.edu
// Instructor's Name:  Professor Miranda
//

public abstract class MazeSolver {
	/**
	 * find the path that minimizes the cost from the start square to the finish square.
	 *
	 * @param maze the maze to be solved
	 * @param pq the priority queue used to store entries while solving the maze
	 * @return the finish square if a path is found, null otherwise.
	 */
	public static Square solve(Maze maze, PriorityQueue<Integer,Square> pq) {
		pq.add(maze.start.getCost(),maze.start);
		while(!pq.isEmpty()){
			Entry current = pq.poll();
			Square currentSquare = (Square)current.value;
			currentSquare.visit();
			if (currentSquare.equals(maze.finish)){
				return currentSquare;
			}
			else{
				int row = currentSquare.getRow();
				int col = currentSquare.getCol();
				//north
				if (availableNeighbor(maze.contents,row-1,col)){
					Square neighbor = maze.contents[row-1][col];
					int currentCost = (int)current.key+neighbor.getCost();
					if (currentCost<neighbor.getRunningCost()){
						neighbor.setPrevious(currentSquare);
						neighbor.setRunningCost(currentCost);
						pq.add(currentCost,neighbor);
					}
				}
				//south
				if (availableNeighbor(maze.contents,row+1,col)){
					Square neighbor = maze.contents[row+1][col];
					int currentCost = (int)current.key+neighbor.getCost();
					if (currentCost<neighbor.getRunningCost()){
						neighbor.setPrevious(currentSquare);
						neighbor.setRunningCost(currentCost);
						pq.add(currentCost,neighbor);
					}
				}
				//east
				if (availableNeighbor(maze.contents,row,col+1)){
					Square neighbor = maze.contents[row][col+1];
					int currentCost = (int)current.key+neighbor.getCost();
					if (currentCost<neighbor.getRunningCost()){
						neighbor.setPrevious(currentSquare);
						neighbor.setRunningCost(currentCost);
						pq.add(currentCost,neighbor);
					}
				}
				//west
				if (availableNeighbor(maze.contents,row,col-1)){
					Square neighbor = maze.contents[row][col-1];
					int currentCost = (int)current.key+neighbor.getCost();
					if (currentCost<neighbor.getRunningCost()){
						neighbor.setPrevious(currentSquare);
						neighbor.setRunningCost(currentCost);
						pq.add(currentCost,neighbor);
					}
				}
			}
		}
		return null;
	}

	/**
	 * checks if the given entry is available.
	 *
	 * @param contents the list that stores the squares in the maze
	 * @param row the row of the neighbor
	 * @param col the col of the neighbor
	 * @return true if not wall and not visited, false otherwise
	 */
	private static boolean availableNeighbor(Square[][] contents, int row, int col){
		if (row>=contents.length||row<0){
			return false;
		}
		if (col>=contents[0].length||col<0){
			return false;
		}
		Square current = contents[row][col];
		if (!current.getIsWall() && !current.isVisited()){
			return true;
		}
		return false;
	}
}
	
