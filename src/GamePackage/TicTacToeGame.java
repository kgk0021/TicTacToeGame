package GamePackage;

import java.util.Arrays;

import PlayersPackage.Player;

public class TicTacToeGame {

	Player playerA;
	Player playerB;
	Player winnerplayer = null;
	int table[][] = null;
	int horizontalSum[] = null;
	int verticalSum[] = null;
	int crossSum1 = 0;
	int crossSum2 = 0;
	int count = 0;
	int n = 0;
	int totalCount = 0;

	public TicTacToeGame(Player a, Player b, int n) {
		this.playerA = a;
		this.playerB = b;
		this.n = n;
		initGame();
		showTable();
	}

	private void initGame() {
		horizontalSum = new int[n];
		verticalSum = new int[n];
		table = new int[n][n];
		for (int i = 0; i < n; i++)
			Arrays.fill(table[i], 0);
		Arrays.fill(horizontalSum, 0);
		Arrays.fill(verticalSum, 0);
		crossSum1 = 0;
		crossSum2 = 0;
		this.totalCount = n * n;

	}

	private void showTable() {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (table[i][j] == 0)
					System.out.print("-" + " ");
				else if (table[i][j] == -1)
					System.out.print("O" + " ");
				else
					System.out.print("X" + " ");
			}
			System.out.println();

		}
	}

	private void updateGameState(int i, int j) {

		int currenCellValue = (count % 2) == 0 ? -1 : 1;
		Player currentPlayer = (count % 2) == 0 ? playerA : playerB;
		table[i][j] = currenCellValue;
		horizontalSum[i] += currenCellValue;
		verticalSum[j] += currenCellValue;
		if (i == j)
			crossSum1 += currenCellValue;
		if (i + j == 2)
			crossSum2 += currenCellValue;

		updateWinnnerIfAny(i, j, currenCellValue * n, currentPlayer);
		count++;

	}

	private void updateWinnnerIfAny(int i, int j, int value, Player player) {
		if (horizontalSum[i] == value || verticalSum[j] == value || crossSum1 == value || crossSum2 == value)
			winnerplayer = player;

	}

	private boolean isValidIndex(int index) {

		return index > 0 && index <= totalCount && table[getXCoordiate(index)][getYCoordiate(index)] == 0;
	}

	private int getXCoordiate(int index) {

		return (index - 1) / n;
	}

	private int getYCoordiate(int index) {

		return (index - 1) % n;
	}

	public boolean playIfPossible(int index) {

		if (!isValidIndex(index)) {
			showTable();
			return false;
		}
		int x = getXCoordiate(index);
		int y = getYCoordiate(index);

		updateGameState(x, y);
		showTable();
		return true;

	}

	public boolean isDraw() {
		return count == totalCount;
	}

	public boolean isEnd() {
		return winnerplayer != null || isDraw();
	}

	public Player getWinner() {
		return winnerplayer;
	}
}
