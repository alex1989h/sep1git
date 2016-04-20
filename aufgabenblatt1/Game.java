package aufgabenblatt1;

public class Game {
  public StringBuffer board;

  private static final int NoMove = -1;

  public Game(String s) {
    board = new StringBuffer(s);
  }

  public Game(StringBuffer s, int position, char player) {
    board = new StringBuffer();
    board.append(s);
    board.setCharAt(position, player);
  }

  public int move(char player) {
    for (int move = 0; move < 9; move++) {
      if (board.charAt(move) == '-') {
        Game game = play(move, player);
        if (game.winner() == player)
          return move;
      }
    }

    for (int move = 0; move < 9; move++) {
      if (board.charAt(move) == '-')
        return move;
    }
    return NoMove;
  }

  public Game play(int move, char player) {
    return new Game(this.board, move, player);
  }

  public char winner() {
    for (int move = 0; move < 7; move += 3) {
      if (!feldIstLeer(move) && board.charAt(move) == board.charAt(move + 1)
          && board.charAt(move + 1) == board.charAt(move + 2))
        return board.charAt(move);
    }
    return '-';
  }

  private boolean feldIstLeer(int move) {
    return board.charAt(move) == '-';
  }
}
