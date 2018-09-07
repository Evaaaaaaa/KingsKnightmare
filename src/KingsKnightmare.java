import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;

/**
 * @author Evangeline Li
 * This class is an implementation of 
 * HW1 for CS540 
 */
/**
 *  * Data structure to store each node.  
 */
class Location {
	private int x;
	private int y;
	private Location parent;

	public Location(int x, int y, Location parent) {
		this.x = x;
		this.y = y;
		this.parent = parent;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Location getParent() {
		return parent;
	}

	@Override
	public String toString() {
		return x + " " + y;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null && obj instanceof Location) {
			Location loc = (Location) obj;
			return loc.x == x && loc.y == y;
		}
		return false;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 31 * (hash + x);
		hash = 31 * (hash + y);
		return hash;
	}
}

public class KingsKnightmare {
	// represents the map/board
	private static boolean[][] board;

	// represents the goal node
	private static Location king;
	// represents the start node
	private static Location knight;
	// y dimension of board
	private static int n;
	// x dimension of the board
	private static int m;

	// enum defining different algo types
	enum SearchAlgo {
		BFS, DFS, ASTAR;
	}

	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			// loads the input file and populates the data variables
			SearchAlgo algo = loadFile(args[0]);
			if (algo != null) {
				switch (algo) {
				case DFS:
					executeDFS();
					break;
				case BFS:
					executeBFS();
					break;
				case ASTAR:
					executeAStar();
					break;
				default:
					break;
				}
			}
		}
	}

	/**
	 * Implemention of DFS algorithm
	 */
	private static void executeDFS() {
		Stack<Location> frontier = new Stack<Location>();
		Stack<Location> path = new Stack<Location>();
		boolean found = false;
		// represents the explored set
		boolean[][] explored = new boolean[n][m];
		for (boolean[] p : explored) {
			for (boolean q : p) {
				q = false;
			}
		}

		int expanded = 0;
		frontier.push(knight);

		while (!found) {
			if (frontier.isEmpty()) {
				System.out.println("NOT REACHABLE");
				System.out.print("Expanded Nodes: " + expanded);
				break;// what's after
			}
			Location toExpand = frontier.pop();

			if (toExpand.equals(king)) {
				found = true;
				path.push(toExpand);// what's after

				Location child = toExpand;
				while (child.getParent() != null) {
					child = child.getParent();
					path.push(child);
				}
				while (!path.isEmpty()) {
					System.out.println(path.pop().toString());
				}

				System.out.print("Expanded Nodes: " + expanded);
				break;
			}

			// mark as explored 
			explored[toExpand.getY()][toExpand.getX()] = true;
			expanded = expanded + 1;

			int x = toExpand.getX();
			int y = toExpand.getY();

			// children in 8 positions
			Location c1, c2, c3, c4, c5, c6, c7, c8;

			// x, y coordinates for the 8 children
			int x18 = x + 2;
			int x27 = x + 1;
			int x36 = x - 1;
			int x45 = x - 2;

			int y67 = y + 2;
			int y58 = y + 1;
			int y41 = y - 1;
			int y32 = y - 2;

			// System.out.println(x36+" "+y32);

			// for child8 if not explored or an obstacle 
			if (-1 < x18 && x18 < m && -1 < y58 && y58 < n && !board[y58][x18]
					&& !explored[y58][x18]) {
				c8 = new Location(x18, y58, toExpand);
				frontier.push(c8);
				//

				explored[y58][x18] = true;
				if (c8.equals(king)) {
					continue;
				}
			}
			// for child7 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y67 && y67 < n && !board[y67][x27]
					&& !explored[y67][x27]) {
				c7 = new Location(x27, y67, toExpand);
				frontier.push(c7);
				explored[y67][x27] = true;
				if (c7.equals(king)) {
					continue;
				}
			}

			// for child6 if not explored or an obstacle 
			if (-1 < x36 && x36 < m && -1 < y67 && y67 < n && !board[y67][x36]
					&& !explored[y67][x36]) {
				c6 = new Location(x36, y67, toExpand);
				frontier.push(c6);
				explored[y67][x36] = true;
				if (c6.equals(king)) {
					continue;
				}
			}
			// for child5 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y58 && y58 < n && !board[y58][x45]
					&& !explored[y58][x45]) {
				c5 = new Location(x45, y58, toExpand);
				frontier.push(c5);
				explored[y58][x45] = true;
				if (c5.equals(king)) {
					continue;
				}
			}
			// for child4 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y41 && y41 < n && !board[y41][x45]
					&& !explored[y41][x45]) {
				c4 = new Location(x45, y41, toExpand);
				frontier.push(c4);
				explored[y41][x45] = true;
				if (c4.equals(king)) {
					continue;
				}
			}
			// for child3 if not explored or an obstacle 

			if (-1 < x36 && x36 < m && -1 < y32 && y32 < n && !board[y32][x36]
					&& !explored[y32][x36]) {
				c3 = new Location(x36, y32, toExpand);
				frontier.push(c3);
				explored[y32][x36] = true;
				if (c3.equals(king)) {
					continue;
				}
			}
			// for child2 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y32 && y32 < n && !board[y32][x27]
					&& !explored[y32][x27]) {
				c2 = new Location(x27, y32, toExpand);
				frontier.push(c2);

				explored[y32][x27] = true;
				if (c2.equals(king)) {
					continue;
				}
			}

			// for child1 if not explored or an obstacle 
			if (0 <= x18 && x18 < m && 0 <= y41 && y41 < n && !board[y41][x18]
					&& !explored[y41][x18]) {
				c1 = new Location(x18, y41, toExpand);
				frontier.push(c1);

				explored[y41][x18] = true;
				if (c1.equals(king)) {
					continue;
				}
			}

		}

	}

	/**
	 * Implementation of BFS algorithm
	 */
	private static void executeBFS() {
		Queue<Location> frontier = new LinkedList<Location>();
		Stack<Location> path = new Stack<Location>();
		boolean found = false;
		// represents the explored set
		boolean[][] explored = new boolean[n][m];
		for (boolean[] p : explored) {
			for (boolean q : p) {
				q = false;
			}
		}
		int k = 0;
		int expanded = 0;
		frontier.add(knight);
		// System.out.println(frontier);
		while (!found) {

			if (frontier.isEmpty()) {
				System.out.println("NOT REACHABLE");
				System.out.print("Expanded Nodes: " + (expanded + k));
				break;// what's after
			}
			Location toExpand = frontier.poll();

			if (toExpand.equals(king)) {
				found = true;
				path.add(toExpand);// what's after

				Location child = toExpand;
				while (child.getParent() != null) {
					child = child.getParent();
					path.add(child);
				}
				while (!path.isEmpty()) {
					System.out.println(path.pop().toString());
				}

				System.out.print("Expanded Nodes: " + (expanded + k));
				break;
			}

			// mark as explored 
			explored[toExpand.getY()][toExpand.getX()] = true;

			int x = toExpand.getX();
			int y = toExpand.getY();

			// children in 8 positions
			Location c1, c2, c3, c4, c5, c6, c7, c8;

			// x, y coordinates for the 8 children
			int x18 = x + 2;
			int x27 = x + 1;
			int x36 = x - 1;
			int x45 = x - 2;

			int y67 = y + 2;
			int y58 = y + 1;
			int y41 = y - 1;
			int y32 = y - 2;

			// System.out.println(x36+" "+y32);
			int temp = 0;

			// for child8 if not explored or an obstacle 
			if (-1 < x18 && x18 < m && -1 < y58 && y58 < n && !board[y58][x18]
					&& !explored[y58][x18]) {
				c8 = new Location(x18, y58, toExpand);
				frontier.add(c8);
				temp++;
				explored[y58][x18] = true;
				if (c8.equals(king)) {
					k = temp;
					continue;
				}
			}
			// for child7 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y67 && y67 < n && !board[y67][x27]
					&& !explored[y67][x27]) {
				c7 = new Location(x27, y67, toExpand);
				frontier.add(c7);
				temp++;
				explored[y67][x27] = true;
				if (c7.equals(king)) {
					k = temp;
					continue;
				}
			}

			// for child6 if not explored or an obstacle 
			if (-1 < x36 && x36 < m && -1 < y67 && y67 < n && !board[y67][x36]
					&& !explored[y67][x36]) {
				c6 = new Location(x36, y67, toExpand);
				frontier.add(c6);
				temp++;
				explored[y67][x36] = true;
				if (c6.equals(king)) {
					k = temp;
					continue;
				}
			}
			// for child5 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y58 && y58 < n && !board[y58][x45]
					&& !explored[y58][x45]) {
				c5 = new Location(x45, y58, toExpand);
				frontier.add(c5);
				temp++;
				explored[y58][x45] = true;
				if (c5.equals(king)) {
					k = temp;
					continue;
				}
			}
			// for child4 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y41 && y41 < n && !board[y41][x45]
					&& !explored[y41][x45]) {
				c4 = new Location(x45, y41, toExpand);
				frontier.add(c4);
				temp++;
				explored[y41][x45] = true;
				if (c4.equals(king)) {
					k = temp;
					continue;
				}
			}
			// for child3 if not explored or an obstacle 

			if (-1 < x36 && x36 < m && -1 < y32 && y32 < n && !board[y32][x36]
					&& !explored[y32][x36]) {
				c3 = new Location(x36, y32, toExpand);
				frontier.add(c3);
				temp++;
				explored[y32][x36] = true;
				if (c3.equals(king)) {
					k = temp;
					continue;
				}
			}
			// for child2 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y32 && y32 < n && !board[y32][x27]
					&& !explored[y32][x27]) {
				c2 = new Location(x27, y32, toExpand);
				frontier.add(c2);
				temp++;
				explored[y32][x27] = true;
				if (c2.equals(king)) {
					k = temp;
					continue;
				}
			}

			// for child1 if not explored or an obstacle 
			if (0 <= x18 && x18 < m && 0 <= y41 && y41 < n && !board[y41][x18]
					&& !explored[y41][x18]) {
				c1 = new Location(x18, y41, toExpand);
				frontier.add(c1);
				temp++;
				explored[y41][x18] = true;
				if (c1.equals(king)) {
					k = temp;
					continue;
				}
			}

			if (!frontier.contains(king)) {
				expanded++;
			}

		}

	}

	/**
	 * Implementation of Astar algorithm for the problem
	 */
	private static void executeAStar() {
		// represents queue of nodes with scores
		PriorityQ<Location> frontier = new PriorityQ<Location>();
		// represents scores
		Stack<Location> path = new Stack<Location>();
		boolean found = false;
		int expanded = 0;

		// represents the explored set
		boolean[][] explored = new boolean[n][m];
		for (boolean[] p : explored) {
			for (boolean q : p) {
				q = false;
			}
		}
		int g = 0;
		int f = g + h(knight);
		frontier.add(knight, f);

		// Frontier = {S} where S is the start node Explored ={}
		// Loop do
		// if Frontier is empty then return failure
		// pick node, n, with min f value from Frontier if n is a goal node then
		// re
		// turn solution foreach each child, n’, of n do
		// if n’ is not in Explored or Frontier then add n’ to Frontier
		// else if g(n’) ≥ g(m) then throw n’ away else add n’ to Frontier and
		// remove m
		// Remove n from Frontier and add n to Explored
		//
		while (!found) {
			if (frontier.isEmpty()) {
				System.out.println("NOT REACHABLE");
				System.out.print("Expanded Nodes: " + expanded);
				break;// what's after
			}

			Location toExpand = frontier.peek().getKey();

			if (toExpand.equals(king)) {
				found = true;
				path.add(toExpand);// what's after

				Location child = toExpand;
				while (child.getParent() != null) {
					child = child.getParent();
					path.add(child);
				}
				while (!path.isEmpty()) {
					System.out.println(path.pop().toString());
				}

				System.out.print("Expanded Nodes: " + (expanded));
				break;
			}

			// mark as explored 
			explored[toExpand.getY()][toExpand.getX()] = true;

			int x = toExpand.getX();
			int y = toExpand.getY();

			// children in 8 positions
			Location c1, c2, c3, c4, c5, c6, c7, c8;

			// x, y coordinates for the 8 children
			int x18 = x + 2;
			int x27 = x + 1;
			int x36 = x - 1;
			int x45 = x - 2;

			int y67 = y + 2;
			int y58 = y + 1;
			int y41 = y - 1;
			int y32 = y - 2;

			// for child8 if not explored or an obstacle 
			c8 = new Location(x18, y58, toExpand);
			if (-1 < x18 && x18 < m && -1 < y58 && y58 < n && !board[y58][x18]
					&& !explored[y58][x18]) {
				if (frontier.exists(c8)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c8);
					if (frontier.getPriorityScore(c8) > fs) {
						frontier.modifyEntry(c8, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c8);
					frontier.add(c8, fs);

				}
				if (c8.equals(king)) {
					continue;
				}
			}

			// for child7 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y67 && y67 < n && !board[y67][x27]
					&& !explored[y67][x27]) {
				c7 = new Location(x27, y67, toExpand);
				if (frontier.exists(c7)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c8);
					if (frontier.getPriorityScore(c7) > fs) {
						frontier.modifyEntry(c7, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c7);
					frontier.add(c7, fs);

				}
				if (c7.equals(king)) {
					continue;
				}
			}

			// for child6 if not explored or an obstacle 
			if (-1 < x36 && x36 < m && -1 < y67 && y67 < n && !board[y67][x36]
					&& !explored[y67][x36]) {
				c6 = new Location(x36, y67, toExpand);
				if (frontier.exists(c6)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c6);
					if (frontier.getPriorityScore(c6) > fs) {
						frontier.modifyEntry(c6, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c6);
					frontier.add(c6, fs);

				}
				if (c6.equals(king)) {
					continue;
				}
			}
			// for child5 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y58 && y58 < n && !board[y58][x45]
					&& !explored[y58][x45]) {
				c5 = new Location(x45, y58, toExpand);
				if (frontier.exists(c5)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c5);
					if (frontier.getPriorityScore(c5) > fs) {
						frontier.modifyEntry(c5, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c5);
					frontier.add(c5, fs);

				}
				if (c5.equals(king)) {
					continue;
				}
			}
			// for child4 if not explored or an obstacle 
			if (-1 < x45 && x45 < m && -1 < y41 && y41 < n && !board[y41][x45]
					&& !explored[y41][x45]) {
				c4 = new Location(x45, y41, toExpand);
				if (frontier.exists(c4)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c4);
					if (frontier.getPriorityScore(c4) > fs) {
						frontier.modifyEntry(c4, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c4);
					frontier.add(c4, fs);

				}
				if (c4.equals(king)) {
					continue;
				}
			}
			// for child3 if not explored or an obstacle 

			if (-1 < x36 && x36 < m && -1 < y32 && y32 < n && !board[y32][x36]
					&& !explored[y32][x36]) {
				c3 = new Location(x36, y32, toExpand);
				if (frontier.exists(c3)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c3);
					if (frontier.getPriorityScore(c3) > fs) {
						frontier.modifyEntry(c3, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c3);
					frontier.add(c3, fs);

				}
				if (c3.equals(king)) {
					continue;
				}
			}
			// for child2 if not explored or an obstacle 
			if (-1 < x27 && x27 < m && -1 < y32 && y32 < n && !board[y32][x27]
					&& !explored[y32][x27]) {
				c2 = new Location(x27, y32, toExpand);
				if (frontier.exists(c2)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c2);
					if (frontier.getPriorityScore(c2) > fs) {
						frontier.modifyEntry(c2, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c2);
					frontier.add(c2, fs);

				}
				if (c2.equals(king)) {
					continue;
				}
			}

			// for child1 if not explored or an obstacle 
			if (0 <= x18 && x18 < m && 0 <= y41 && y41 < n && !board[y41][x18]
					&& !explored[y41][x18]) {
				c1 = new Location(x18, y41, toExpand);
				if (frontier.exists(c1)) {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c1);
					if (frontier.getPriorityScore(c1) > fs) {
						frontier.modifyEntry(c1, fs);
					}
				} else {
					int parentScore = frontier.getPriorityScore(toExpand);
					int pDistance = h(toExpand);
					int gCost = parentScore - pDistance;
					int fs = gCost + 3 + h(c1);
					frontier.add(c1, fs);

				}
				if (c1.equals(king)) {
					continue;
				}
			}
			frontier.poll();
			explored[y][x] = true;
			expanded++;
		}
	}

	/**
	 *  
	 * 
	 * @param filename
	 * @return Algo type This method reads the input file and populates all the 
	 *         data variables for further processing
	 */
	private static SearchAlgo loadFile(String filename) {
		File file = new File(filename);
		try {
			Scanner sc = new Scanner(file);
			SearchAlgo algo = SearchAlgo
					.valueOf(sc.nextLine().trim().toUpperCase());
			n = sc.nextInt();
			m = sc.nextInt();
			sc.nextLine();
			// check if 5 < n, m < 201
			if (n <= 5 || m >= 201) {
				System.out.println("Index out of bound: 5 < n, m < 201");
				System.exit(1);
			}
			board = new boolean[n][m];

			for (int i = 0; i < n; i++) {
				String line = sc.nextLine();
				for (int j = 0; j < m; j++) {
					if (line.charAt(j) == '1') {
						board[i][j] = true;
					} else if (line.charAt(j) == 'S') {
						knight = new Location(j, i, null);
					} else if (line.charAt(j) == 'G') {
						king = new Location(j, i, null);
					}
				}
			}
			sc.close();
			return algo;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static int h(Location t) {
		int h = Math.abs(t.getX() - king.getX())
				+ Math.abs(t.getY() - king.getY());
		return h;
	}

}
