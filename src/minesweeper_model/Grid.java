package minesweeper_model;

import java.util.ArrayList;

/**
 * This class symbolize the board of the game.
 * @author yannick
 */
public class Grid
{
    /**
     * Variables which contain the height and width of the grid
     */
    public final int WIDTH_GRID;
    public final int HEIGHT_GRID;
    /**
     * Variable contenant le tableau
     */
    private Case[][] grid;
    
    /**
     * Base constructor. Create the grid with width columns and height rows.
     * @param width Nb columns
     * @param height Nb rows
     */
    public Grid(int width, int height)
    {
        WIDTH_GRID = width; 
        HEIGHT_GRID = height;
        this.initGrid();
    }
    
    /**
     * Internal function, initialize the variable grid.
     */
    private void initGrid()
    {
        //Grid creation
        this.grid = new Case[HEIGHT_GRID][WIDTH_GRID];
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.grid[i][j] = new Case(i,j);
            }
        }
        
        //Mine random generation
        int nb_mines = 15;
        
        ArrayList<Integer> mines = new ArrayList<>();
        for(int i=0; i<nb_mines; i++)
        {
            int nbAlea = 0;
            do
            {
                nbAlea = (int) (Math.random()*(HEIGHT_GRID*WIDTH_GRID));
            }while(mines.contains(nbAlea));
            mines.add(nbAlea);
        }
        
        int case_number = 0;
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                this.majNeighboors(i,j);
                if(mines.contains(case_number))
                {
                    this.grid[i][j].setMine(true);
                    for(Case c : this.grid[i][j].getNeighboors())
                    {
                        c.increaseValue();
                    }
                }
                case_number++;
            }
        }        
        
        System.out.println(this);
    }
    
    //GET & SET
    
    public Case getCase(int i, int j)
    {
        return this.grid[i][j];
    }
    
    public int getWidthGrid()
    {
        return WIDTH_GRID;
    }
    
    public int getHeightGrid()
    {
        return HEIGHT_GRID;
    }

    /**
     * Function used to put number on each neighbor case of a mine
     * @param i The row of the actual mine case
     * @param j The column of the actual mine case
     */
    private void majNeighboors(int i, int j)
    {
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i-1][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j-1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
        try
        {
            this.grid[i][j].addNeighboor(this.grid[i+1][j+1]);
        }
        catch(ArrayIndexOutOfBoundsException ex){}
    }
    
    /**
     * Override ToString function
     * @return The grid in a string.
     */
    @Override
    public String toString()
    {
        String text = "";
        for(int i=0; i<HEIGHT_GRID; i++)
        {
            for(int j=0; j<WIDTH_GRID; j++)
            {
                if(this.grid[i][j].isMine())
                {
                    text += " M ";
                }
                else
                {
                    text += " "+this.grid[i][j].getValue()+" ";
                }
            }
            text += "\n";
        }    
        
        return text;
    }
    
    
}
