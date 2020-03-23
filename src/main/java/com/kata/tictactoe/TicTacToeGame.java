package com.kata.tictactoe;

public class TicTacToeGame {
    private char[][] gameBoardLayout = new char[3][3];
    private char currentPlayer;

    public String playTurnAt(final int positionX, final int positionY) throws PositionAlreadyInUseException {
        checkIsPositionAlreadyFilled(positionX, positionY);
        currentPlayer = getCurrentPlayerToBePlayed();
        gameBoardLayout[positionX][positionY] = currentPlayer;
        if (isCurrentPlayerWinnerByRow()) {
            return "Player " + currentPlayer + " is the Winner";
        }
        return null;
    }

    private boolean isCurrentPlayerWinnerByRow() {
        for (int row = 0; row < 3; row++) {
            if ((getPlayerAt(row, 0) == currentPlayer) &&
                    (getPlayerAt(row, 0) == getPlayerAt(row, 1)) &&
                    (getPlayerAt(row, 0) == getPlayerAt(row, 2))) {
                return true;
            }
        }
        return false;
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