import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.BasicStroke;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.JFrame;

public class Board extends JPanel implements ActionListener
{  
  int data[][] = new int[9][9];
  JTextField input[][] = new JTextField[9][9];
  JButton result[][] = new JButton[9][9];
  JButton btnSolve;
  
  Board()
  {
    this.setLayout(null);    
    for(int i = 0; i < 9; i++ )
      for(int j = 0; j < 9; j++ )
      {
        input[i][j] = new JTextField(1);
        input[i][j].setLayout(null);
        input[i][j].setSize(24, 24);
        input[i][j].setLocation((26 + j * 25), (51 + i * 25));
        add(input[i][j]);
      }
    btnSolve = new JButton("Solve");    
    btnSolve.setActionCommand("solve");
    btnSolve.setBounds(100, 280, 75, 30);
    btnSolve.addActionListener(this); 
    add(btnSolve);
  }
  
  public void paint(Graphics g)
  {
    super.paint(g);
    Graphics g2d = (Graphics2D)g;
    
    for(int x = 50; x < 250; x = x + 25)
      g2d.drawLine(x, 50, x, 275);
    for(int y = 75; y < 275; y = y + 25)
      g2d.drawLine(25, y, 250, y);
    
    g2d.setColor(Color.RED);
    g2d.drawRect(25, 50, 225, 225);
    for(int x = 100; x < 250; x = x + 75)
      g2d.drawLine(x, 50, x, 275);
    for(int y = 125; y < 275; y = y + 75)
      g2d.drawLine(25, y, 250, y);
  }  
  
  public void actionPerformed(ActionEvent event)
  {
    if(event.getActionCommand() == "solve")
    {
      int num;
      for(int i = 0; i < 9; i++ )
        for(int j = 0; j < 9; j++ )
        {
          if(!input[i][j].getText().equals(""))
          {
            num = Integer.parseInt((String)input[i][j].getText());
            if(check(i, j, num))
                data[i][j] = num;
            else
            {
                reset();
                return;
            }
          }          
        }         

      if(solve(0, 0))
      {
        for(int i = 0; i < 9; i++ )
          for(int j = 0; j < 9; j++ )
          {
            input[i][j].setVisible(false);
            result[i][j] = new JButton("" + data[i][j]);
            result[i][j].setBorder(null);       
            result[i][j].setLayout(null);
            result[i][j].setSize(24, 24);
            result[i][j].setBounds((26 + j * 25), (51 + i * 25), 24, 24);
            add(result[i][j]);
          }
      }
      else
      {
        reset();
      }
    }
  }
  
  public void reset()
  {
    for(int i = 0; i < 9; i++ )
      for(int j = 0; j < 9; j++ )
      {
        data[i][j] = 0;
        //input[i][j].setVisible(true);
      }
  }
  
  public boolean check(int x, int y, int num)
  {
    for(int i = 0; i < 9; i++ )
      if((data[x][i] == num)||(data[i][y] == num))
        return false;
    
    x = (x / 3) * 3;
    y = (y / 3) * 3;
    for(int i = x; i < (x + 3); i++ )
      for(int j = y; j < (y + 3); j++ )
        if(data[i][j] == num)
          return false;
    return true;
  }
  
  public boolean solve(int row, int col)
  {
    boolean done = true;    
    for(int i = row; i < 9; i++ )
      for(int j = 0; j < 9; j++ )
        if(data[i][j] == 0)
        {
          row = i;
          col = j;
          done = false;
          i = 9;
          j = 9;
        }
    if(done)
      return done;    
    int num = 1;
    while(num < 10)
    { 
      if(check(row, col, num))
      {
        data[row][col] = num;
        if(solve(row, col))
          return true;
        data[row][col] = 0;
      }
      num++;
    }
    return false;
  }
}
