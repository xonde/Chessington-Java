package training.chessington.model.pieces;

import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PawnTest {
    @Test
    public void whitePawnCanMoveUpOneSquare() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(6, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).contains(new Move(coords, coords.plus(-1, 0)));
    }

    @Test
    public void blackPawnCanMoveDownOneSquare() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(1, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).contains(new Move(coords, coords.plus(1, 0)));
    }

    @Test
    public void whitePawnCanMoveUpTwoSquaresIfNotMoved() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(6, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).contains(new Move(coords, coords.plus(-2, 0)));
    }

    @Test
    public void blackPawnCanMoveDownTwoSquaresIfNotMoved() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(1, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).contains(new Move(coords, coords.plus(2, 0)));
    }

    @Test
    public void whitePawnCannotMoveUpTwoSquaresIfAlreadyMoved() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates initial = new Coordinates(6, 4);
        board.placePiece(initial, pawn);

        Coordinates moved = initial.plus(-1, 0);
        board.move(initial, moved);

        List<Move> moves = pawn.getAllowedMoves(moved, board);

        assertThat(moves).doesNotContain(new Move(moved, moved.plus(-2, 0)));
    }

    @Test
    public void blackPawnCannotMoveDownTwoSquaresIfAlreadyMoved() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates initial = new Coordinates(1, 4);
        board.placePiece(initial, pawn);

        Coordinates moved = initial.plus(1, 0);
        board.move(initial, moved);

        List<Move> moves = pawn.getAllowedMoves(moved, board);

        assertThat(moves).doesNotContain(new Move(moved, moved.plus(2, 0)));
    }
}
