package com.kata.tictactoe;

public class TicTacToeGame {

    private final int MAX_GRID_SIZE = 3;
    private final int FIRST_POSITION = 0;
    private final int SECOND_POSITION = 1;
    private final int THIRD_POSITION = 2;
    private char[][] gameBoardLayout = new char[MAX_GRID_SIZE][MAX_GRID_SIZE];
    private char currentPlayer;
    private int totalTurnsPlayed;

    public String playTurnAt(final int positionX, final int positionY) throws PositionAlreadyInUseException, PositionOutOfGridException {
        checkIsPositionOutOfGameBoardGrid(positionX, positionY);
        checkIsPositionAlreadyFilled(positionX, positionY);
        currentPlayer = getCurrentPlayerToBePlayed();
        gameBoardLayout[positionX][positionY] = currentPlayer;
        totalTurnsPlayed++;
        if (isCurrentPlayerWinner()) {
            return ("Player " + currentPlayer + " is the Winner");
        }
        if (isGameDraw()) {
            return "It is a Draw";
        }
        return null;
    }

    private void checkIsPositionOutOfGameBoardGrid(int positionX, int positionY) throws PositionOutOfGridException {
        if (positionX < FIRST_POSITION || positionY < FIRST_POSITION || positionX > THIRD_POSITION || positionY > THIRD_POSITION) {
            throw new PositionOutOfGridException();
        }
    }

    private boolean isGameDraw() {
        return (totalTurnsPlayed == 9);
    }

    private boolean isCurrentPlayerWinner() {
        return (isCurrentPlayerWinnerByRow() ||
                isCurrentPlayerWinnerByColumn() ||
                isCurrentPlayerWinnerByLeftToRightDiagonal() ||
                isCurrentPlayerWinnerByRightToLeftDiagonal());
    }

    private boolean isCurrentPlayerWinnerByRightToLeftDiagonal() {
        return (getPlayerAt(FIRST_POSITION, THIRD_POSITION) == currentPlayer) &&
                (getPlayerAt(FIRST_POSITION, THIRD_POSITION) == getPlayerAt(SECOND_POSITION, SECOND_POSITION)) &&
                (getPlayerAt(FIRST_POSITION, THIRD_POSITION) == (getPlayerAt(THIRD_POSITION, FIRST_POSITION)));
    }

    private boolean isCurrentPlayerWinnerByLeftToRightDiagonal() {
        return (getPlayerAt(FIRST_POSITION, FIRST_POSITION) == currentPlayer) &&
                (getPlayerAt(FIRST_POSITION, FIRST_POSITION) == getPlayerAt(SECOND_POSITION, SECOND_POSITION)) &&
                (getPlayerAt(FIRST_POSITION, FIRST_POSITION) == (getPlayerAt(THIRD_POSITION, THIRD_POSITION)));
    }

    private boolean isCurrentPlayerWinnerByColumn() {
        for (int column = FIRST_POSITION; column < MAX_GRID_SIZE; column++) {
            if ((getPlayerAt(FIRST_POSITION, column) == currentPlayer) &&
                    (getPlayerAt(FIRST_POSITION, column) == getPlayerAt(SECOND_POSITION, column)) &&
                    (getPlayerAt(FIRST_POSITION, column) == getPlayerAt(THIRD_POSITION, column))) {
                return true;
            }
        }
        return false;
    }

    private boolean isCurrentPlayerWinnerByRow() {
        for (int row = FIRST_POSITION; row < MAX_GRID_SIZE; row++) {
            if ((getPlayerAt(row, FIRST_POSITION) == currentPlayer) &&
                    (getPlayerAt(row, FIRST_POSITION) == getPlayerAt(row, SECOND_POSITION)) &&
                    (getPlayerAt(row, FIRST_POSITION) == getPlayerAt(row, THIRD_POSITION))) {
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