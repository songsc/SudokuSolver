import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Frame extends JFrame
{
  public Frame()
  {
    add(new Board());
    setSize (275, 350);
    setTitle("Sudoku Solver");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(0, 0);
    setResizable(true);
    setVisible(true);
  }
  public static void main (String[]args)
  {
    new Frame();
  }
}