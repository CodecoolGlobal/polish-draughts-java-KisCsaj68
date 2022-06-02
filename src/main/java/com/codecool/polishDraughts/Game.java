package com.codecool.polishDraughts;


public class Game {
    private static final int POSITION_A = 97;

    private Board gameBoard;
    private Player playerOne;
    private Player playerTwo;

    public Game(int size) {
        this.gameBoard = new Board(size);
        this.playerOne = new Player("black");
        this.playerTwo = new Player("white");

    }

    public void start() {
        Player winner = null;
        while (checkForWinner() == null) {
            playRound(playerOne);
            playRound(playerTwo);
        }

        this.gameBoard.toString();
        winner = checkForWinner();
        theWinner(winner);
    }

    public void playRound(Player player) {
        boolean hasException = true;
        while (hasException) {
            try {
                this.gameBoard.toString();
                Coordinate moveFrom = getMovePawnFrom(player);
                while (invalidPawnPick(player, moveFrom)) {
                    System.out.println("Invalid Pawn pick! Try again!");
                    moveFrom = getMovePawnFrom(player);
                }
                Coordinate moveTo = getMovePawnTo(player);
                while (invalidMove(player, moveFrom, moveTo)) {
                    System.out.println("Invalid Pawn move! Try again!");
                    moveTo = getMovePawnTo(player);
                }
                this.gameBoard.movePawn(moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
                hasException = false;
            } catch (InterruptPlayRound e) {
                    // hasException remain true
            }


        }
//        this.gameBoard.toString();
//        Coordinate moveFrom = getMovePawnFrom(player);
//        while (invalidPawnPick(player, moveFrom)) {
//            System.out.println("Invalid Pawn pick! Try again!");
//            moveFrom = getMovePawnFrom(player);
//        }
//        Coordinate moveTo = getMovePawnTo(player);
//        while (invalidMove(player, moveFrom, moveTo)) {
//            System.out.println("Invalid Pawn move! Try again!");
//            moveTo = getMovePawnTo(player);
//        }
//        this.gameBoard.movePawn(moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }

    private boolean invalidMove(Player player, Coordinate moveFrom, Coordinate moveTo) {
        return !isCoordinateOnBoard(moveTo) || !isFieldEmpty(moveTo.getX(), moveTo.getY()) ||
                !isValidDiagonalMove(player, moveFrom.getX(), moveFrom.getY(), moveTo.getX(), moveTo.getY());
    }

//    private boolean tryToMakeMove(Coordinate moveFrom, Coordinate moveTo) {
//        return false;
//    }

    private boolean isValidDiagonalMove(Player player, int moveFromX, int moveFromY, int moveToX, int moveToY) {
        return checkDiagonalMove(moveFromX, moveFromY, moveToX, moveToY) &&
                checkValidDiagMoveToOppnentWay(player, moveFromX, moveToX, moveFromY) &&
                isValidJump(moveFromX, moveFromY, moveToX, moveToY, player);
    }

    private boolean isValidJump(int moveFromX, int moveFromY, int moveToX, int moveToY, Player player) {
        if (Math.abs(moveFromX - moveToX) > 2) {
            return false;
        } else if (Math.abs(moveFromX - moveToX) == 1) {
            return true;

        } else {
            return this.gameBoard.isOppositePawnBetween(moveFromX, moveFromY, moveToX, moveToY, player);
        }
    }

    private boolean isFieldEmpty(int moveToX, int moveToY) {
        return gameBoard.checkToField(moveToX, moveToY);
    }

    private boolean invalidPawnPick(Player player, Coordinate moveFrom) {
        return !isCoordinateOnBoard(moveFrom) || !isCorrectColorPicked(player, moveFrom.getX(), moveFrom.getY());
    }

    private boolean isCoordinateOnBoard(Coordinate moveFrom) {
        return gameBoard.checkCoordinateOnBoard(moveFrom.getX(), moveFrom.getY());
    }

    private boolean isCorrectColorPicked(Player player, int moveFromX, int moveFromY) {
        return gameBoard.checkPlayerAndPawnColor(player, moveFromX, moveFromY);
    }

    public Coordinate getMovePawnFrom(Player player) throws InterruptPlayRound {
        int rowFrom ;
        int column ;
        System.out.println("move" + player.getColor());
        try {
            int[] userInput = player.getUserInput(player.getColor().substring(0, 1).toUpperCase() +
                    player.getColor().substring(1) + " player, enter coordinate Pawn to pick: ");

            rowFrom = userInput[0] - POSITION_A;
            column = userInput[1];

        }catch (InvalidUserInputLength e){
            rowFrom = -1;
            column = -1;
        }

        Coordinate result = new Coordinate(rowFrom, column);
        return result;
    }

    public Coordinate getMovePawnTo(Player player) throws InterruptPlayRound {
        int rowTo;
        int column;
        int[] userInput;
        try{
            userInput = player.getUserInput(player.getColor().substring(0, 1).toUpperCase() +
                    player.getColor().substring(1) + " player, coordinate field to move: ");

            rowTo = userInput[0] - POSITION_A;
            column = userInput[1];
        }catch (InvalidUserInputLength e ){
            rowTo = -1;
            column = -1;
        }
        Coordinate result = new Coordinate(rowTo, column);
        return result;
    }


    public boolean checkDiagonalMove(int rowFrom, int columnFrom, int rowTo, int columnTo) {
        return (Math.abs(rowFrom - rowTo) - Math.abs(columnFrom - columnTo) == 0);
    }


    public Player checkForWinner() {
        return this.gameBoard.hasZeroPawn(playerOne, playerTwo);
    }

    public boolean checkValidDiagMoveToOppnentWay(Player player, int rowFrom, int rowTo, int columnFrom) {
        if (player.getColor().equals("black")) {
            if (this.gameBoard.checkPawnCrowned(rowFrom, columnFrom)) {
                return true;
            }
            return rowFrom < rowTo;
        } else {
            if (this.gameBoard.checkPawnCrowned(rowFrom, columnFrom)) {
                return true;
            }
            return rowTo < rowFrom;
        }
    }

    public static String theWinner(Player player) {

        return "The winner player is " + player.getColor();

    }
}
