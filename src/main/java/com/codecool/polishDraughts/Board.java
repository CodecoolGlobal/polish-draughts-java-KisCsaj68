package com.codecool.polishDraughts;

public class Board {
    private Pawn[][] board;
    private String alphabet = "abcdefghijklmnopqrstuvwxyz";

    public Board(int n) {
        //check if n between 10 and 20
        this.board = createBoard(n);
    }

    private Pawn[][] createBoard(int n) {
        Pawn[][] result = new Pawn[n][n];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < n; j++) {
                if (i % 2 == 0 && j % 2 != 0) {
                    result[i][j] = new Pawn("black", i, j, false);
                } else if (i % 2 != 0 && j % 2 == 0) {
                    result[i][j] = new Pawn("black", i, j, false);
                } else {
                    result[i][j] = null;
                }
            }
            for (int k = n - 4; k < n; k++) {
                for (int l = 0; l < n; l++) {
                    if (k % 2 == 0 && l % 2 != 0) {
                        result[k][l] = new Pawn("white", k, l, false);
                    } else if (k % 2 != 0 && l % 2 == 0) {
                        result[k][l] = new Pawn("white", k, l, false);
                    } else {
                        result[k][l] = null;
                    }
                }
            }
        }
        return result;
    }

    @Override
    public String toString() {
        String result = "";

        System.out.print("   ");
        for (int index = 1; index <= this.board[0].length; index++) {
            System.out.print(index + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.board.length; i++) {
            System.out.print(alphabet.toUpperCase().charAt(i) + " ");
            for (int j = 0; j < this.board[0].length; j++) {
                if (board[i][j] != null) {
                    System.out.print(" " + board[i][j].toString() + " ");
                } else {
                    System.out.print(" . ");
                }
            }
            System.out.println();
        }
        return result;
    }

    public void removePawn(int x, int y) {
        board[x][y] = null;
    }

    public void movePawn(int x, int y, int nextX, int nextY) {
        String color = board[x][y].getColor();
        boolean isCrowned = board[x][y].isCrowned();
        if (!isCrowned) {
            setCrowned(color, x, y, nextX);
        }
        board[nextX][nextY] = board[x][y];
        removePawn(x, y);
    }

    private void setCrowned(String color, int x, int y, int nextX) {
        if (color == "white" && nextX == 0) {
            board[x][y].setCrowned(true);
        } else if (color == "black" && nextX == this.board.length - 1) {
            board[x][y].setCrowned(true);
        }
    }

    public boolean checkPlayerAndPawnColor(Player player, int row, int column) {
        if (this.board[row][column] == null) {
            return false;
        }
        String playerColor = player.getColor();
        String pawnColor = this.board[row][column].getColor();

        return playerColor.equals(pawnColor);
    }

    public boolean checkToField(int row, int column) {
        if (this.board[row][column] == null) {
            return true;
        }
        return false;
    }

    public boolean checkCoordinateOnBoard(int row, int column) {
        if (row < this.board.length && row >= 0 && column >= 0 && column < this.board.length) {
            return true;
        }
        return false;
    }

    public Pawn[][] getBoard() {
        return this.board;
    }

    public Player hasZeroPawn(Player playerOne, Player playerTwo) {
        int black = 0;
        int white = 0;
        for (int i = 0; i < this.board.length; i++) {
            for (int y = 0; y < this.board.length; y++) {
                if (this.board[i][y] != null) {
                    String color = this.board[i][y].getColor();
                    if (color.compareTo("black") == 0) {
                        black += 1;
                    } else {
                        white += 1;
                    }
                }
            }
        }
        if (black == 0) {
            return playerTwo;
        } else if (white == 0) {
            return playerOne;
        }
        return null;
    }

    public boolean isOppositePawnBetween(int moveFromX, int moveFromY, int moveToX, int moveToY, Player player) {
        String color = player.getColor();
        if (moveFromX < moveToX && moveFromY < moveToY) {
            // 1,1
            return isOppositePawnRemoved(moveFromX, moveFromY, 1, 1, color);
//            for (int i = moveFromX; i <= moveToX; i++) {
//                if (this.board[i][moveFromY + i] != null && !this.board[i][moveFromY + i].getColor().equals(color)) {
//                    removePawn(i, moveFromY + i);
//                    return true;
//                }
//
//            }
        } else if (moveFromX < moveToX && moveFromY > moveToY) {
            //1, -1
            return isOppositePawnRemoved(moveFromX, moveFromY, 1, -1, color);
//            for (int i = moveFromX; i <= moveToX; i++) {
//                if (this.board[i][moveFromY - i] != null && !this.board[i][moveFromY - i].getColor().equals(color)) {
//                    removePawn(i, moveFromY - i);
//                    return true;
//                }
//            }

        } else if (moveFromX > moveToX && moveFromY < moveToY) {
            //-1, 1
            return isOppositePawnRemoved(moveFromX, moveFromY, -1, 1, color);
//            for (int i = moveFromX; i <= moveToX; i--) {
//                if (this.board[i][moveFromY + i] != null && !this.board[i][moveFromY + i].getColor().equals(color)) {
//                    removePawn(i, moveFromY + i);
//                    return true;
//                }
//            }
        } else if (moveFromX > moveToX && moveFromY > moveToY) {
            //-1, -1
            return isOppositePawnRemoved(moveFromX, moveFromY, -1, -1, color);
//            for (int i = moveFromX; i <= moveToX; i--) {
//                if (this.board[i][moveFromY - i] != null && !this.board[i][moveFromY - i].getColor().equals(color)) {
//                    removePawn(i, moveFromY - i);
//                    return true;
//                }
//
//            }

        }
        return false;
    }

    private boolean isOppositePawnRemoved(int moveFromX, int moveFromY, int scaleX, int scaleY, String color) {
        if (this.board[moveFromX + scaleX][moveFromY + scaleY] != null && !this.board[moveFromX + scaleX][moveFromY + scaleY].getColor().equals(color)) {
            removePawn(moveFromX + scaleX, moveFromY + scaleY);
            return true;
        }
        return false;
    }
}
