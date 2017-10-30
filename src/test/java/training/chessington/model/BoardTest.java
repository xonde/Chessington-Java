package training.chessington.model;

import org.junit.Test;
import training.chessington.model.pieces.Piece;

import static training.chessington.model.pieces.Piece.PieceType.PAWN;
import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class BoardTest {
    @Test
    public void newBoardHasWhitePiecesAtBottom() {
        Board board = Board.forNewGame();
        Piece piece = board.get(new Coordinates(7, 0));
        assertThat(piece).isColour(PlayerColour.WHITE);
    }

    @Test
    public void newBoardHasBlackPiecesAtTop() {
        Board board = Board.forNewGame();
        Piece piece = board.get(new Coordinates(0, 0));
        assertThat(piece).isColour(PlayerColour.BLACK);
    }

    @Test
    public void canMovePiecesOnBoard() {
        Board board = Board.forNewGame();

        Coordinates from = new Coordinates(6, 0);
        Coordinates to = new Coordinates(4, 4);

        board.move(from, to);

        assertThat(board.get(from)).isNull();
        assertThat(board.get(to)).isColour(PlayerColour.WHITE).isPiece(PAWN);
    }

    @Test
    public void squaresOffBoardAreOutOfRange() {
        Board board = Board.empty();
        assertThat(board.inRange(new Coordinates(-1, -1))).isFalse();
        assertThat(board.inRange(new Coordinates(-1, 4))).isFalse();
        assertThat(board.inRange(new Coordinates(8, 4))).isFalse();
        assertThat(board.inRange(new Coordinates(4, -1))).isFalse();
        assertThat(board.inRange(new Coordinates(4, 8))).isFalse();
        assertThat(board.inRange(new Coordinates(8, 8))).isFalse();
    }

    @Test
    public void squaresOnBoardAreInRange() {
        Board board = Board.empty();
        assertThat(board.inRange(new Coordinates(0, 0))).isTrue();
        assertThat(board.inRange(new Coordinates(0, 7))).isTrue();
        assertThat(board.inRange(new Coordinates(7, 0))).isTrue();
        assertThat(board.inRange(new Coordinates(7, 7))).isTrue();
    }

    @Test
    public void returnsCorrectlyWhenQueryingForPiece() {
        Board board = Board.forNewGame();
        assertThat(board.hasPieceOfColourAt(new Coordinates(0, 0), PlayerColour.BLACK)).isTrue();
        assertThat(board.hasPieceOfColourAt(new Coordinates(0, 0), PlayerColour.WHITE)).isFalse();

        assertThat(board.hasPieceOfColourAt(new Coordinates(4, 4), PlayerColour.WHITE)).isFalse();
        assertThat(board.hasPieceOfColourAt(new Coordinates(4, 4), PlayerColour.BLACK)).isFalse();
    }
}