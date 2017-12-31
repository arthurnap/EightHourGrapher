import javax.swing.*;

public class Form extends JComponent
{
  private int numRows = 2;
  private int numCols;

  private String[] categories;
  double[] vals[];

  public Form(String[] categories, double[] vals)
  {
    int length = categories.length;
    numCols = length;
  }
}