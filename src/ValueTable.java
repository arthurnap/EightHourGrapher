import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ValueTable extends JPanel
{
  private JPanel valueTable; // category-value table
  private JButton submit;

  private ArrayList<JTextField> categoriesFields;
  private ArrayList<JTextField> valsFields;

  public ValueTable(String[] categories, double[] vals)
  {
    createValueTable(categories, vals);
  }

  public void createValueTable(String[] categories, double[] vals)
  {
    int length = categories.length;
    valueTable = new JPanel(new GridLayout(2, length));
    submit = new JButton("Submit"); // Need to put action listener to this and submit info to ChartManager when clicked

    categoriesFields = new ArrayList<>();
    valsFields = new ArrayList<>();

    for (int i=0; i<length; i++)
    {
      JTextField cat = new JTextField(categories[i]);
      categoriesFields.add(cat);
      valueTable.add(cat);
    }

    for (int i=0; i<length; i++)
    {
      JTextField val = new JTextField(""+vals[i]);
      valsFields.add(val);
      valueTable.add(val);
    }

    valueTable.setPreferredSize(new Dimension(200, 100));

    submit.setPreferredSize(new Dimension(50,30));
  }

  public JPanel getValueTable()
  {
    return valueTable;
  }

  public String[] getCategoryList()
  {
    int numCats = categoriesFields.size();
    String[] categories = new String[numCats];

    for (int i=0; i<numCats; i++)
    {
      categories[i] = categoriesFields.get(i).getText();
    }

    return categories;
  }

  public double[] getValues()
  {
    int length = valsFields.size();
    double[] arr = new double[length];

    for (int i=0; i<length; i++)
    {
      arr[i] = Double.parseDouble(valsFields.get(i).getText());
    }

    return arr;
  }

  public void updateFieldsList(int newMaxIdx)
  {
    int length = valsFields.size();
    for (int i=newMaxIdx; i<length; i++)
    {
      valueTable.remove(categoriesFields.get(i));
      valueTable.remove(valsFields.get(i));
    }

    categoriesFields.subList(0, newMaxIdx);
    valsFields.subList(0, newMaxIdx);

    valueTable.revalidate();
  }

}
