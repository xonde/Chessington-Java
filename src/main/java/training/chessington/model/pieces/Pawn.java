package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> allowedMoves = new ArrayList<>();
        HashMap<String,Move> moveSet = this.setMoves(from, board);


        createForwardMoves(from,allowedMoves,moveSet);
        checkBlockedMoves(board,allowedMoves);




        Move moveDL = moveSet.get("Diagonal left one step");
        Move moveDR = moveSet.get("Diagonal right one step");

        if(checkIfMovesIsOnBoard(moveDL,7) && checkIfMovesIsOnBoard(moveDR,7)){
            if(checkIfCoordinateIsOccupied(board,moveDL.getTo()) && !checkIfSameColour(this, board.get(moveDL.getTo()))){
                allowedMoves.add(moveDL);
            }
            if(checkIfCoordinateIsOccupied(board,moveDR.getTo()) && !checkIfSameColour(this, board.get(moveDR.getTo()))){
                allowedMoves.add(moveDR);
            }
        }

        return allowedMoves;
    }

    public void checkBlockedMoves(Board board, ArrayList<Move> allowedMoves){
        boolean clearFMoves = false;
        for(Move i: allowedMoves){
            if(board.get(i.getTo()) != null){
                if(allowedMoves.indexOf(i) == 0){
                    clearFMoves = true;
                }
                else{
                    allowedMoves.remove(i);
                }

            }
        }
        if(clearFMoves){
            allowedMoves.clear();
        }
    }

    public void createForwardMoves(Coordinates from, ArrayList<Move> allowedMoves, HashMap<String, Move> moveSet){
        if(from.getRow() == 6 | from.getRow() == 1){
            if(checkIfMovesIsOnBoard(moveSet.get("Forward one step"), 7)){
                allowedMoves.add(moveSet.get("Forward one step"));
            }
            if(checkIfMovesIsOnBoard(moveSet.get("Forward two steps"), 7)){
                allowedMoves.add(moveSet.get("Forward two steps"));
            }

        }
        else if (from.getRow() != 0 | from.getRow() != 7){
            if(checkIfMovesIsOnBoard(moveSet.get("Forward one step"), 7)){
                allowedMoves.add(moveSet.get("Forward one step"));
            }
        }
    }

    public boolean checkIfMovesIsOnBoard(Move move, int max){
        boolean onBoard = false;
        if(move.getTo().getCol() <= max && move.getTo().getCol() >= 0){
            if(move.getTo().getRow() <= max && move.getTo().getRow() >= 0){
                onBoard = true;
            }
        }
        return onBoard;
    }

    public boolean checkIfCoordinateIsOccupied(Board board, Coordinates coord){
        boolean isOccupied = false;
        if(board.get(coord) != null){
            isOccupied = true;
        }
        return isOccupied;
    }

    public boolean checkIfSameColour(Piece x, Piece y){
        boolean isSameColour = true;
        if(x.getColour() != y.getColour()){
            isSameColour = false;
        }
        return isSameColour;
    }

    public HashMap<String,Move> setMoves(Coordinates from, Board board){
        HashMap<String,Move> moveSet = new HashMap<>();
        int rowMultiplier = 1;
        if(this.colour == PlayerColour.WHITE){
            rowMultiplier = -1;
        }

        moveSet.put("Forward one step", new Move(from,from.plus(rowMultiplier,0)));
        moveSet.put("Forward two steps", new Move(from,from.plus(2*rowMultiplier,0)));
        moveSet.put("Diagonal left one step", new Move(from, from.plus(rowMultiplier,-1)));
        moveSet.put("Diagonal right one step", new Move(from, from.plus(rowMultiplier,+1)));

        return moveSet;
    }
}
