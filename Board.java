import java.awt.*;
import java.util.ArrayList;

public class Board {

    private Tile[][] board;
    private int rows;
    private int cols;
    private int numBombs;
    private ArrayList<Tile> bombs;

    public Board(int row, int col, int numBomb){
        rows = row;
        cols = col;
        numBombs = numBomb;
        board = new Tile[rows][cols];
        for(int r = 0; r<rows; r++){
            for(int c = 0; c<cols; c++){
                board[r][c] = new Tile();
            }
        }
        bombs = new ArrayList<>();
        placeBombs(numBombs);
    }

    public Tile getTile(int r, int c){
        return board[r][c];
    }

    public boolean gameWin(){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                Tile t = board[r][c];
                if(t.getHasMine()){
                    if(!t.isFlagged()){
                        return false;
                    }
                }
                else{
                    if(!t.getClicked()){
                        return false;
                    }
                    else{
                        if(t.isFlagged()){
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }


    public void placeBombs(int numBombs){

        for(int i = 0; i<numBombs; i++){
            int randR = (int)(Math.random() * rows);
            int randC = (int)(Math.random() * cols);

            while(board[randR][randC].getHasMine() == true) {
                randR = (int) (Math.random() * rows);
                randC = (int) (Math.random() * cols);
            }
            board[randR][randC].setHasMine(true);
            board[randR][randC].setAdj(8);
            bombs.add(board[randR][randC]);

            for(int r = randR-1; r<randR+2; r++){
                for(int c = randC-1; c<randC+2; c++){
                    if(r > -1 && r < rows && c > -1 && c < cols){
                        if(board[r][c].getHasMine() == false){
                            board[r][c].setAdj(board[r][c].getAdj()+1);
                        }
                    }
                }
            }

        }
    }

    public void makeClick(int row, int col){

        if(board[row][col].getClicked() == false){

            board[row][col].setClicked(true);

            if(board[row][col].getHasMine() == true){
                System.out.println("YOU ARE A LOSER PLZ STOP PLAYING");
            }

            else{
                if(board[row][col].getAdj() == 0){
                    for(int r=row-1; r<row+2; r++){
                        for(int c=col-1; c<col+2; c++){
                            if(r > -1 && r < rows && c > -1 && c < cols){
                                if(r != row || c != col){
                                    if(board[r][c].getHasMine() == false && board[r][c].getClicked() == false){
                                        makeClick(r,c);
                                    }
                                }
                            }
                        }
                    }
                }
            }

        }
    }

    public boolean gameOver(){
        for(int i=0; i<bombs.size(); i++){
            if(bombs.get(i).getClicked() == true)
                return true;
        }

        return false;
    }

    public void restart(){
        bombs = new ArrayList<>();
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                board[r][c].setFlagged(false);
                board[r][c].setClicked(false);
                board[r][c].setHasMine(false);
                board[r][c].setAdj(0);
            }
        }
        placeBombs(numBombs);

    }

    public void draw(Graphics2D g2){
        int length = board[0][0].DIM;
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                board[r][c].draw(g2, c*length, r*length);
            }
        }
    }

    public void printBoard(){
        for(int r=0; r<rows; r++){
            for(int c=0; c<cols; c++){
                System.out.print(" "+board[r][c]);
            }
            System.out.println();
        }
    }

}
