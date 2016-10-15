package minesweeper;

import java.awt.GridLayout;
import java.awt.List;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Game {
	private Cell [][] cells;
	private int CellID= 0;
	private int side= 9;
	private int limit= side-1;
	
	public void setGame(){
		JFrame frame = new JFrame(); 
		frame.add(addCells());
		
		plantMines();
		setCellValues();
		
		frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
	
	public JPanel addCells(){
		JPanel panel = new JPanel(new GridLayout(side,side));
        cells = new Cell[side][side];
        for(int i = 0; i< side; i++){
            for(int m = 0; m<side; m++){
                cells[i][m] = new Cell(this);
                cells[i][m].setId(getID());
                panel.add(cells[i][m].getButton());
            }
        }
        return panel;
    }

    public void plantMines(){
        List<Integer> loc = generateMinesLocation(10);
        for(int i : loc){
            getCell(i).setValue(-1);
        }
    }
    //random mines
    public List<Integer> generateMinesLocation(int q){
        List<Integer> loc = new List<Integer>();
        int random;
        for(int i = 0; i<q;){
            random = (int)(Math.random()* (side*side));
            if(!loc.contains(random)){
                loc.add(random);
                i++;
            }
        }
        return loc;
    }
    
    public void setCellValues(){
    	
        for(int i = 0; i<side; i++){
        	
            for(int m = 0; m<side; m++){
            	
                 if(cells[i][m].getValue() != -1){
                	 
                     if(m>=1 && cells[i][m-1].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(m<= limit && cells[i][m+1].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i>=1 && cells[i-1][m].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i<= limit && cells[i+1][m].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i>=1 && m>= 1 && cells[i-1][m-1].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i<= limit && m<= limit && cells[i+1][m+1].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i>=1 && m<= limit && cells[i-1][m+1].getValue() == -1) cells[i][m].incrementValue();
                     
                     if(i<= limit && m>= 1 && cells[i+1][m-1].getValue() == -1) cells[i][m].incrementValue();
                 }
            }
        }
    }
    
    public void scanEmptyCells(){
        for(int i = 0; i<side; i++){
            for(int m = 0; m<side; m++){
                if(!cells[i][m].isNotChecked()){
                	
                    if(m>=1 && cells[i][m-1].isEmpty()) cells[i][m-1].checkCell();
                    
                    if(m<= limit && cells[i][m+1].isEmpty()) cells[i][m+1].checkCell();
                    
                    if(i>=1 && cells[i-1][m].isEmpty()) cells[i-1][m].checkCell();
                    
                    if(i<= limit && cells[i+1][m].isEmpty()) cells[i+1][m].checkCell();
                    
                    if(i>=1 && m>= 1 && cells[i-1][m-1].isEmpty()) cells[i-1][m-1].checkCell();
                    
                    if(i<= limit && m<= limit && cells[i+1][m+1].isEmpty()) cells[i+1][m+1].checkCell();
                    
                    if(i>=1 && m<= limit && cells[i-1][m+1].isEmpty()) cells[i-1][m+1].checkCell();
                    
                    if(i<= limit && m>= 1 && cells[i+1][m-1].isEmpty()) cells[i+1][m-1].checkCell();
                }
            }
        }
    }
    
    public int getID(){
        int id = cellID;
        cellID++;
        return id;
    }

    public Cell getCell(int id){
        for(Cell[] a : cells){
            for(Cell b : a){
                if(b.getId() == id) return b;

            }
        }
        return null;
    }

    public void fail(){
        for(Cell[] a : cells){
            for(Cell b : a){
                b.reveal();
            }
        }
    }

		
		
		
		
	}
	
			


