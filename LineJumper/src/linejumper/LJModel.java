/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linejumper;

/**
 *
 * @author Sashank
 */
public class LJModel {
    
    private char[] letters;
    
    public LJModel()
    {
        letters = new char[] {'T', 'T', 'T', 'T', '_', 'F', 'F', 'F', 'F'};
    }
    
    public char[] getPositions()
    {
        return letters;
    }
    public boolean didWin() {
        char[] solution = {'F', 'F', 'F', 'F', '_', 'T', 'T', 'T', 'T'};
        for (int i = 0; i < letters.length; i++) {
            if (letters[i] != solution[i]) {
                return false;
            }
        }
        return true;
    }
    
    public void movePiece(int n)
    {
        if (Math.abs(n-getBlank()) < 3)
        {
            letters[getBlank()] = letters[n];
            letters[n] = '_';
        }
        
    }
    public int getBlank()
    {
        for (int i = 0; i < letters.length; i++)
        {
            if (letters[i] == '_') return i;
        }
        return -1;
    }
    
}
