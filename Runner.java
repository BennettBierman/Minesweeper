import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Scanner;

public class Runner extends JPanel implements MouseListener, KeyListener {

    private static final long serialVersionUID = 1L;
    private static final int PREF_W = 720, PREF_H = 720;
    private RenderingHints hints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    private final int row = 24;
    private final int col = 24;
    private int mines;
    private Board board;
    private boolean flag;
    private boolean firstClick;

    public Runner(){
        super();
        this.setBackground(Color.WHITE );
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setFocusable(true);
        flag = false;
        firstClick = false;
        String numBombs = (String) JOptionPane.showInputDialog(null,
                "Please type how many Mines you would like in the board",
                "Mine Selection",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                "0");
        mines = Integer.parseInt(numBombs);
        board = new Board(row, col, mines);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHints(hints);
        board.draw(g2);
        if(!firstClick){
            g2.setFont(new Font("Cooper Black", Font.PLAIN, 90));
            g2.setColor(Color.RED);
            g2.drawString("CLICK TO START",0,150);
        }
        if(board.gameOver()){
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, PREF_W, PREF_H);
            g2.setColor(Color.CYAN);
            g2.setFont(new Font("Cooper Black", Font.PLAIN, 75));
            g2.drawString("YOU FUCKING LOSE", 0,200);
            g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
            g2.drawString("Press R to Restart", 200,400);
        }
        if(board.gameWin()){
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, PREF_W, PREF_H);
            g2.setColor(Color.GREEN);
            g2.setFont(new Font("Cooper Black", Font.PLAIN, 75));
            g2.drawString("YAY YOU WIN", 120,200);
            g2.setFont(new Font("Cooper Black", Font.PLAIN, 40));
            g2.drawString("Press R to Restart", 200,400);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(PREF_W, PREF_H);
    }

    private static void createAndShowGUI() {
        Runner gamePanel = new Runner();
        JFrame frame = new JFrame("Mine Sweeper");
        frame.getContentPane().add(gamePanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void restart(){
        board.restart();
        firstClick = false;
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                createAndShowGUI();
            }
        });
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        firstClick = true;
        int x = e.getX();
        int y = e.getY();
        int len = board.getTile(0,0).DIM;
        x = x/len;
        y = y/len;
        if(!board.gameOver()){
            if(flag){
                if(!board.getTile(y,x).isFlagged()) board.getTile(y,x).setFlagged(true);
                else board.getTile(y,x).setFlagged(false);
            }
            else{
                board.makeClick(y,x);
            }
        }
        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE) flag = true;
        if(keyCode == KeyEvent.VK_R) restart();
        repaint();


    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if(keyCode == KeyEvent.VK_SPACE) flag = false;

    }
}
