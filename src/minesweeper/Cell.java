package minesweeper;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Cell extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8016435748669257866L;
	static JButton button[][]; 
	static Boolean mine[][]; 
	final int size = 9; 
	int mineSet = 0;
	public Boolean boom = false;
	public Boolean empty = false; 
	Random state = new Random(); {
	for (int x = 0; x < size; x++) {
		for (int y = 0; y < size; y++) {
			button[x][y] = new JButton("");
			button[x][y].addActionListener(new ButtonPress());
			if (state.nextInt(20) < 20)	{
				if (mineSet < 20) {
					mine[x][y] = true;
					mineSet++;	
				}
				else 
					mine[x][y] = false;
			}
			else 
				mine[x][y] = false;
			add(button[x][y]);
			}
		}
	}
	
	//Counts the amount of mines around a pushed button
	public int scanMines(int x, int y) {
		int count = 0;
		 //Checks top tile
        if ((x > 0) && (mine[x][y-1])) {
            count++;
        	}
		//Checks top-left tile
        if ((x > 0) && (y > 0) && (mine[x-1][y-1])) {
        	count++;
            }
        //Checks left tile
        if ((x > 0) && (mine[y - 1][y])) {
            count++;
        	}
        //Checks bottom-left tile
        if ((x > 0) && (y + 1 < size) && (mine[x - 1][y + 1])) {
            count++;
        	}
        //Checks bottom tile
        if ((y + 1 < size) && (mine[x][y + 1])) {
            count++;
        	}
        //Checks bottom-right tile
        if ((x+1 < size) && (y > 0) && (mine[x + 1][y - 1])) {
            count++;
        	}
        //Checks right tile
        if ((x + 1 < size) && (mine[x + 1][y])) {
            count++;
        	}
        //Checks top-right tile
        if ((x + 1 < size) && (y + 1 < size) && (mine[x + 1][y + 1])) {
            count++;
        	}      
        return count;
	}
	public void reveal(int x, int y) {
        
        if ((x < 0) || (y<0) || (x>=size) || (y>= size))
        	return;
        int allMine = scanMines(x, y);
        if (allMine > 0)
            return;
        reveal(x - 1, y - 1);
        reveal(x - 1, y);
        reveal(x - 1, y + 1);
        reveal(x, y - 1);
        reveal(x, y + 1);
        reveal(x + 1, y - 1);
        reveal(x + 1, y);
        reveal(x + 1, y + 1);
    	}
	
	 private class ButtonPress implements ActionListener {
	        public void actionPerformed(ActionEvent m) {
	            for (int x = 0; x < size; x++) {
	                for (int y = 0; y < size; y++) {
	                    if (m.getSource() == button[x][y]) {
	                        if (mine[x][y]) {
	                            boom = true;
	                        } 
	                        else {
	                            reveal(x,y);	
	                            empty = true; 
	                            }
	                        }
	                    }
	                }
	            }
	        }
}