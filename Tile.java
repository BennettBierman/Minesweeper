import java.awt.*;

public class Tile {

    private int adj;
    public final int DIM = 30;
    private boolean clicked;
    private boolean hasMine;
    private boolean flagged;

    public void Tile(){
        adj = 0;
        clicked = false;
        hasMine = false;
        flagged = false;

    }

    public void draw(Graphics2D g2, int x, int y){

        if(flagged){
            g2.setColor(Color.RED);
            g2.fillRect(x,y,DIM,DIM);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, DIM, DIM);
        }
        else if(!clicked){
            g2.setColor(Color.GREEN);
            g2.fillRect(x,y,DIM,DIM);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, DIM, DIM);
        }
        else if(!hasMine) {
            g2.setColor(Color.YELLOW);
            g2.fillRect(x,y,DIM,DIM);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, DIM, DIM);
            g2.setColor(Color.BLUE);
            String str = (adj > 0)? adj+"": "";
            g2.drawString(str, x+DIM/2, y+DIM/2);

        }
        else{
            g2.setColor(Color.BLACK);
            g2.fillRect(x,y,DIM,DIM);
            g2.setColor(Color.BLACK);
            g2.drawRect(x, y, DIM, DIM);
            g2.setColor(Color.WHITE);
            g2.drawString("L", x+DIM/2, y+DIM/2);
        }
    }

    public void setFlagged(boolean bool){
        flagged = bool;
    }

    public boolean isFlagged(){
        return flagged;
    }

    public int getAdj(){
        return adj;
    }

    public void setAdj(int num){
        adj = num;
    }

    public boolean getClicked(){
        return clicked;
    }

    public void setClicked(boolean bool){
        clicked = bool;
    }

    public boolean getHasMine(){
        return hasMine;
    }

    public void setHasMine(boolean bool){
        hasMine = bool;
    }

    @Override
    public String toString(){
        String ret = (clicked)? adj+"": "*";
        return ret;
    }
}
