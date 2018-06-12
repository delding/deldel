/**
 Design a Snake game that is played on a device with screen size = width x height. Play the game online if you are not familiar with the game.

 The snake is initially positioned at the top left corner (0,0) with length = 1 unit.

 You are given a list of food's positions in row-column order. When a snake eats the food, its length and the game's score both increase by 1.

 Each food appears one by one on the screen. For example, the second food will not appear until the first food was eaten by the snake.

 When a food does appear on the screen, it is guaranteed that it will not appear on a block occupied by the snake.

 Example:
 Given width = 3, height = 2, and food = [[1,2],[0,1]].

 Snake snake = new Snake(width, height, food);

 Initially the snake appears at position (0,0) and the food at (1,2).

 |S| | |
 | | |F|

 snake.move("R"); -> Returns 0

 | |S| |
 | | |F|

 snake.move("D"); -> Returns 0

 | | | |
 | |S|F|

 snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )

 | |F| |
 | |S|S|

 snake.move("U"); -> Returns 1

 | |F|S|
 | | |S|

 snake.move("L"); -> Returns 2 (Snake eats the second food)

 | |S|S|
 | | |S|

 snake.move("U"); -> Returns -1 (Game over because snake collides with border)
**/

public class SnakeGame {
	int width;
	int height;
	// 2d can be encoded into 1d: rowHead = body.peekFirst() / width, colHead = body.peekFirst() % width;
	// can put body in a set for fast body collision look-up
	Deque<Integer> snakeX = new ArrayDeque<>();
	Deque<Integer> snakeY = new ArrayDeque<>();
	int[][] food;
	int foodIdx = 0;
	/** Initialize your data structure here.
	 @param width - screen width
	 @param height - screen height
	 @param food - A list of food positions
	 E.g food = [[1,1], [1,0]] means the first food is positioned at [1,1], the second is at [1,0]. */
	public SnakeGame(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		snakeX.add(0);
		snakeY.add(0);
	}

	/** Moves the snake.
	 @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 @return The game's score after the move. Return -1 if game over.
	 Game over when snake crosses the screen boundary or bites its body. */
	public int move(String direction) {
		int x = snakeX.peekFirst();
		int y = snakeY.peekFirst();
		switch(direction) {
			case "U" : {
				x--;
				break;
			}
			case "D" : {
				x++;
				break;
			}
			case "L" : {
				y--;
				break;
			}
			case "R" : {
				y++;
				break;
			}
			default :
		}
		if (x < 0 || y < 0 || x >= height || y >= width) return -1;
		Iterator<Integer> xit = snakeX.iterator();
		Iterator<Integer> yit = snakeY.iterator();
		while(xit.hasNext()) {
			int xi = xit.next();
			int yi = yit.next();
			if (xi == snakeX.peekLast() && yi == snakeY.peekLast()) continue; // head can go to where tail is
			if (x == xi && y == yi) return -1;
		}
		snakeX.addFirst(x);
		snakeY.addFirst(y);
		if (foodIdx < food.length && food[foodIdx][0] == x && food[foodIdx][1] == y) {
			foodIdx++;
		} else {
			snakeX.removeLast();
			snakeY.removeLast();
		}
		return foodIdx;
	}
}

/**
 * Your SnakeGame object will be instantiated and called as such:
 * SnakeGame obj = new SnakeGame(width, height, food);
 * int param_1 = obj.move(direction);
 */