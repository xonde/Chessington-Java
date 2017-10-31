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

    @Test
    public void pawnsCannotMoveIfPieceInFront() {
        Board board = Board.empty();

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(3, 4);
        board.placePiece(blackCoords, blackPawn);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(4, 4);
        board.placePiece(whiteCoords, whitePawn);

        List<Move> blackMoves = blackPawn.getAllowedMoves(blackCoords, board);
        List<Move> whiteMoves = whitePawn.getAllowedMoves(whiteCoords, board);

        assertThat(blackMoves).isEmpty();
        assertThat(whiteMoves).isEmpty();
    }

    @Test
    public void pawnsCannotMoveTwoSquaresIfPieceTwoInFront() {
        Board board = Board.empty();

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(2, 4);
        board.placePiece(blackCoords, blackPawn);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(4, 4);
        board.placePiece(whiteCoords, whitePawn);

        List<Move> blackMoves = blackPawn.getAllowedMoves(blackCoords, board);
        List<Move> whiteMoves = whitePawn.getAllowedMoves(whiteCoords, board);

        assertThat(blackMoves).doesNotContain(new Move(blackCoords, blackCoords.plus(2, 0)));
        assertThat(whiteMoves).doesNotContain(new Move(blackCoords, blackCoords.plus(-2, 0)));
    }

    @Test
    public void whitePawnsCannotMoveAtTopOfBoard() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Coordinates coords = new Coordinates(0, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).isEmpty();
    }

    @Test
    public void blackPawnsCannotMoveAtBottomOfBoard() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Coordinates coords = new Coordinates(7, 4);
        board.placePiece(coords, pawn);

        List<Move> moves = pawn.getAllowedMoves(coords, board);

        assertThat(moves).isEmpty();
    }

    @Test
    public void whitePawnsCanCaptureDiagonally() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Piece enemyPiece = new Rook(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(4, 4);
        board.placePiece(pawnCoords, pawn);

        Coordinates enemyCoords = pawnCoords.plus(-1, 1);
        board.placePiece(enemyCoords, enemyPiece);

        List<Move> moves = pawn.getAllowedMoves(pawnCoords, board);

        assertThat(moves).contains(new Move(pawnCoords, enemyCoords));
    }

    @Test
    public void blackPawnsCanCaptureDiagonally() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Piece enemyPiece = new Rook(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(3, 4);
        board.placePiece(pawnCoords, pawn);

        Coordinates enemyCoords = pawnCoords.plus(1, 1);
        board.placePiece(enemyCoords, enemyPiece);

        List<Move> moves = pawn.getAllowedMoves(pawnCoords, board);

        assertThat(moves).contains(new Move(pawnCoords, enemyCoords));
    }

    @Test
    public void pawnsCannotMoveDiagonallyOffBoard() {
        Board board = Board.empty();

        Piece blackPawn = new Pawn(PlayerColour.BLACK);
        Coordinates blackCoords = new Coordinates(3, 0);
        board.placePiece(blackCoords, blackPawn);

        Piece whitePawn = new Pawn(PlayerColour.WHITE);
        Coordinates whiteCoords = new Coordinates(4, 0);
        board.placePiece(whiteCoords, whitePawn);

        List<Move> blackMoves = blackPawn.getAllowedMoves(blackCoords, board);
        List<Move> whiteMoves = whitePawn.getAllowedMoves(whiteCoords, board);

        assertThat(blackMoves).isEmpty();
        assertThat(whiteMoves).isEmpty();
    }

    @Test
    public void whitePawnsCannotMoveDiagonallyNotToCapture() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.WHITE);
        Piece friendlyPiece = new Rook(PlayerColour.WHITE);
        Coordinates pawnCoords = new Coordinates(4, 4);
        board.placePiece(pawnCoords, pawn);

        Coordinates rookCoords = pawnCoords.plus(-1, 1);
        board.placePiece(rookCoords, friendlyPiece);

        List<Move> moves = pawn.getAllowedMoves(pawnCoords, board);

        assertThat(moves).doesNotContain(new Move(pawnCoords, rookCoords));
        Coordinates otherDiagonal = pawnCoords.plus(-1, -1);
        assertThat(moves).doesNotContain(new Move(pawnCoords, otherDiagonal));
    }

    @Test
    public void blackPawnsCannotMoveDiagonallyNotToCapture() {
        Board board = Board.empty();
        Piece pawn = new Pawn(PlayerColour.BLACK);
        Piece friendlyPiece = new Rook(PlayerColour.BLACK);
        Coordinates pawnCoords = new Coordinates(3, 4);
        board.placePiece(pawnCoords, pawn);

        Coordinates rookCoords = pawnCoords.plus(1, 1);
        board.placePiece(rookCoords, friendlyPiece);

        List<Move> moves = pawn.getAllowedMoves(pawnCoords, board);

        assertThat(moves).doesNotContain(new Move(pawnCoords, rookCoords));
        Coordinates otherDiagonal = pawnCoords.plus(1, -1);
        assertThat(moves).doesNotContain(new Move(pawnCoords, otherDiagonal));
    }
}
