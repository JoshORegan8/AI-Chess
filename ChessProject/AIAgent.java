import java.util.*;

public class AIAgent{
    Random rand;

    public AIAgent(){
        rand = new Random();
    }

/*
  The method randomMove takes as input a stack of potential moves that the AI agent
  can make. The agent uses a rondom number generator to randomly select a move from
  the inputted Stack and returns this to the calling agent.
*/

    public Move randomMove(Stack possibilities){

        int moveID = rand.nextInt(possibilities.size());
        System.out.println("Agent randomly selected move : "+moveID);
        for(int i=1;i < (possibilities.size()-(moveID));i++){
            possibilities.pop();
        }
        Move selectedMove = (Move)possibilities.pop();
        return selectedMove;
    }

    public Move nextBestMove(Stack possibilitiesWhite, Stack possibilitiesBlack) {
        Stack whiteOrigin = (Stack) possibilitiesWhite.clone();
        Square blackSquare;
        int value;
        int pieceValue;
        Move AIhvm = null; //highest value move
        Move AIcurrent;
        Move AImove;
        pieceValue = 0;
        value = 0;

        while (possibilitiesWhite.size() > 0) { // provides the "blackOrigin" Stack, refreshes the positions.
            AImove = (Move) possibilitiesWhite.pop();
            AIcurrent = AImove;
            Stack blackOrigin = (Stack) possibilitiesBlack.clone();

            while (blackOrigin.size() > 0) { // This while loop will evaluate the moves and provide a value, if no piece present, random move will be made.
                blackSquare = (Square) blackOrigin.pop();
                if ((AIcurrent.getLanding().getXC() == blackSquare.getXC()) && (AIcurrent.getLanding().getYC() == blackSquare.getYC())) {
                    if (blackSquare.getName().contains("BlackPawn")) {
                        value = 10;
                    }
                    else if (blackSquare.getName().contains("BlackKnight")) {
                        value = 30;
                    }
                    else if (blackSquare.getName().contains("BlackRook")) {
                        value = 50;
                    }
                    else if (blackSquare.getName().contains("BlackBishop")) {
                        value = 40;
                    }
                    else if (blackSquare.getName().contains("BlackQueen")) {
                        value = 80;
                    }
                    else if (blackSquare.getName().contains("BlackKing")) {
                        value = 100;
                    }
                }
                if(pieceValue < value){
                    pieceValue = value;
                    AIhvm = AIcurrent;
                }
            }
        }

        if (pieceValue > 0){
            return AIhvm;
        }
        else{
            return randomMove(whiteOrigin);
        }
    }

    public Move twoLevelsDeep(Stack possibilitiesWhite, Stack possibilitiesBlack) {

        Stack whiteOrigin = (Stack) possibilitiesWhite.clone();
        Square blackSquare;
        int value;
        int pieceValue;
        Move AIhvm = null; //highest value move
        Move AIcurrent;
        Move AImove;
        pieceValue = 0;
        value = 0;
        Stack blackOrigin = (Stack) possibilitiesBlack.clone();

        while (possibilitiesWhite.size() > 0) {
            AImove = (Move) possibilitiesWhite.pop();
            AIcurrent = AImove;

            while (blackOrigin.size() > 0) {
                blackSquare = (Square) blackOrigin.pop();
                if ((AIcurrent.getLanding().getXC() == blackSquare.getXC()) && (AIcurrent.getLanding().getYC() == blackSquare.getYC())) {
                    if (blackSquare.getName().contains("BlackPawn")) {
                        value = 10;
                    } else if (blackSquare.getName().contains("BlackKnight")) {
                        value = 30;
                    } else if (blackSquare.getName().contains("BlackRook")) {
                        value = 50;
                    } else if (blackSquare.getName().contains("BlackBishop")) {
                        value = 40;
                    } else if (blackSquare.getName().contains("BlackQueen")) {
                        value = 80;
                    } else if (blackSquare.getName().contains("BlackKing")) {
                        value = 100;
                    }
                }
                if (pieceValue < value) {
                    pieceValue = value;
                    AIhvm = AIcurrent;
                }
            }
            blackOrigin = (Stack) possibilitiesBlack.clone();
        }

        if (pieceValue > 0) {
            return AIhvm;
        } else {
            System.out.println("RANDOM MOVE");
            return randomMove(whiteOrigin);
        }
    }
}
