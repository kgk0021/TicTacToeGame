package BootStrapperPackage;

import java.util.Scanner;

import GamePackage.TicTacToeGame;
import PlayersPackage.Player;

public class BootStrapperClass {

	static Scanner sc = null;

	private static void play(TicTacToeGame g, Player p) {

		System.out.println("Player-" + p.getName() + "'s turn. Enter any number from 1-9 to indicate your move.");

		int index;
		try {
			index = sc.nextInt();
		} catch (Exception e) {
			index = 0;
		}

		while (!g.playIfPossible(index)) {
			System.out.println("Invalid input. Please try again.");
			try {
				index = Integer.parseInt(sc.next());
			} catch (Exception e) {

				index = 0;

			}
		}

	}

	public static void main(String[] args) {

		try {
			sc = new Scanner(System.in);

			letsPlay();
		} finally {
			sc.close();
		}

	}

	private static void letsPlay() {
		Player A = new Player("O");
		Player B = new Player("X");
		TicTacToeGame g = new TicTacToeGame(A, B, 3);

		while (!g.isEnd()) {
			play(g, A);

			if (g.isEnd())
				break;

			play(g, B);

		}

		if (g.isDraw())
			System.out.println("Game Over : Draw");
		else
			System.out.println("Game Over : Player-" + g.getWinner().getName() + " has won");

	}

}
