package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        ArrayList<Move> allowedMoves = new ArrayList<>();

        //Move(coords, coords.plus(-1, 0))

        if(this.colour == PlayerColour.WHITE){
            if(from.getRow() == 6){
                allowedMoves.add(new Move(from,from.plus(-1,0)));
                allowedMoves.add(new Move(from,from.plus(-2,0)));
            }
            else if (from.getRow() != 0){
                allowedMoves.add(new Move(from,from.plus(-1,0)));
            }
        }
        else if(this.colour == PlayerColour.BLACK){
            if(from.getRow() == 1){
                allowedMoves.add(new Move(from,from.plus(1,0)));
                allowedMoves.add(new Move(from,from.plus(2,0)));
            }
            else if (from.getRow() != 7){
                allowedMoves.add(new Move(from,from.plus(1,0)));
            }
        }

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

        Move moveDL = null;
        Move moveDR = null;


        if(this.colour == PlayerColour.WHITE){
            Coordinates diagonalLeft = new Coordinates(from.getRow()-1,from.getCol()-1);
            Coordinates diagonalRight = new Coordinates(from.getRow()-1,from.getCol()+1);
            moveDL = new Move(from, diagonalLeft);
            moveDR = new Move(from, diagonalRight);


        }
        else{
            Coordinates diagonalLeft = new Coordinates(from.getRow()+1,from.getCol()-1);
            Coordinates diagonalRight = new Coordinates(from.getRow()+1,from.getCol()+1);
            moveDL = new Move(from, diagonalLeft);
            moveDR = new Move(from, diagonalRight);
        }



        if( moveDL.getTo().getRow() >= 0 && moveDL.getTo().getRow() <= 7){
            if( moveDL.getTo().getCol() >= 0 && moveDL.getTo().getCol() <= 7){
                if( moveDR.getTo().getRow() >= 0 && moveDR.getTo().getRow() <= 7){
                    if( moveDR.getTo().getCol() >= 0 && moveDR.getTo().getCol() <= 7){
                                if(board.get(moveDL.getTo()) != null && board.get(moveDL.getTo()).getColour() != this.colour){
                                    allowedMoves.add(moveDL);
                                }
                                if(board.get(moveDR.getTo()) != null && board.get(moveDR.getTo()).getColour() != this.colour){
                                    allowedMoves.add(moveDR);
                                }
                    }
                }
            }
        }

        //Checks if Move is within board
        //Check if colours are different
        //Checks if piece is on a coord





            //w:0 b:7
        return allowedMoves;
    }
}
