package com.kata.tictactoe;

public class TicTacToeGame {
    private char[][] gameBoardLayout = new char[3][3];
    private char currentPlayer;

    public String playTurnAt(final int positionX, final int positionY) throws PositionAlreadyInUseException {
        checkIsPositionAlreadyFilled(positionX, positionY);
        currentPlayer = getCurrentPlayerToBePlayed();
        gameBoardLayout[positionX][positionY] = currentPlayer;
        if ((getPlayerAt(0,0) == 'X' ) &&
                (getPlayerAt(0,0) == getPlayerAt(0,1)) &&
                (getPlayerAt(0,0) == (getPlayerAt(0,2)))) {
            return "Player X is the Winner";
        }
        if ((getPlayerAt(1,0) == 'X' ) &&
                (getPlayerAt(1,0) == getPlayerAt(1,1)) &&
                (getPlayerAt(1,0) == (getPlayerAt(1,2)))) {
            return "Player X is the Winner";
        }
        return null;
    }

    private void checkIsPositionAlreadyFilled(final int positionX, final int positionY) throws PositionAlreadyInUseException {
        if (gameBoardLayout[positionX][positionY] != '\0') {
            throw new PositionAlreadyInUseException(positionX, positionY);
        }
    }

    private char getCurrentPlayerToBePlayed() {
        return currentPlayer == 'X' ? 'O' : 'X';
    }

    public char getPlayerAt(final int positionX, final int positionY) {
        return gameBoardLayout[positionX][positionY];
    }
}