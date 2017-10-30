package training.chessington.model.pieces;

import org.junit.Before;
import org.junit.Test;
import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.List;

import static training.chessington.model.pieces.PieceAssert.*;
import static org.assertj.core.api.Assertions.*;

public class RookTest {

    private Board board;
    private Rook rook = new Rook(PlayerColour.WHITE);

    @Before
    public void setup() {
        board = Board.empty();
    }

    @Test
    public void rookCanMoveHorizontally() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).contains(
                new Move(coords, new Coordinates(3, 0)),
                new Move(coords, new Coordinates(3, 1)),
                new Move(coords, new Coordinates(3, 2)),
                new Move(coords, new Coordinates(3, 3)),
                new Move(coords, new Coordinates(3, 5)),
                new Move(coords, new Coordinates(3, 6)),
                new Move(coords, new Coordinates(3, 7))
        );
    }

    @Test
    public void rookCannotMoveToOwnSquare() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).doesNotContain(new Move(coords, coords));
    }

    @Test
    public void rookCanMoveVertically() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).contains(
                new Move(coords, new Coordinates(0, 4)),
                new Move(coords, new Coordinates(1, 4)),
                new Move(coords, new Coordinates(2, 4)),
                new Move(coords, new Coordinates(4, 4)),
                new Move(coords, new Coordinates(5, 4)),
                new Move(coords, new Coordinates(6, 4)),
                new Move(coords, new Coordinates(7, 4))
        );
    }

    @Test
    public void rookCanCaptureOpposingPieceHorizontally() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece opponent = new Queen(PlayerColour.BLACK);
        Coordinates opponentCoords = new Coordinates(3, 2);
        board.placePiece(opponentCoords, opponent);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).contains(new Move(coords, opponentCoords));
    }

    @Test
    public void rookCanCaptureOpposingPieceVertically() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece opponent = new Queen(PlayerColour.BLACK);
        Coordinates opponentCoords = new Coordinates(5, 4);
        board.placePiece(opponentCoords, opponent);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).contains(new Move(coords, opponentCoords));
    }

    @Test
    public void rookIsBlockedByFriendlyPieceHorizontally() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece friendlyPiece = new Queen(PlayerColour.WHITE);
        Coordinates friendlyCoords = new Coordinates(3, 2);
        board.placePiece(friendlyCoords, friendlyPiece);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).doesNotContain(
                new Move(coords, friendlyCoords),
                new Move(coords, new Coordinates(3, 1)),
                new Move(coords, new Coordinates(3, 0))
        );
    }

    @Test
    public void rookIsBlockedByFriendlyPieceVertically() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece friendlyPiece = new Queen(PlayerColour.WHITE);
        Coordinates friendlyCoords = new Coordinates(5, 4);
        board.placePiece(friendlyCoords, friendlyPiece);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).doesNotContain(
                new Move(coords, friendlyCoords),
                new Move(coords, new Coordinates(6, 4)),
                new Move(coords, new Coordinates(7, 4))
        );
    }

    @Test
    public void rookCannotPassThroughOpposingPieceHorizontally() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece opponent = new Queen(PlayerColour.BLACK);
        Coordinates opponentCoords = new Coordinates(3, 2);
        board.placePiece(opponentCoords, opponent);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).doesNotContain(
                new Move(coords, new Coordinates(3, 1)),
                new Move(coords, new Coordinates(3, 0))
        );
    }

    @Test
    public void rookCannotPassThroughOpposingPieceVertically() {
        Coordinates coords = new Coordinates(3, 4);
        board.placePiece(coords, rook);

        Piece opponent = new Queen(PlayerColour.BLACK);
        Coordinates opponentCoords = new Coordinates(5, 4);
        board.placePiece(opponentCoords, opponent);

        List<Move> allowedMoves = rook.getAllowedMoves(coords, board);

        assertThat(allowedMoves).doesNotContain(
                new Move(coords, new Coordinates(6, 4)),
                new Move(coords, new Coordinates(7, 4))
        );
    }
}
