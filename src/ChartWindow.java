import javax.swing.*;
import java.awt.*;

public class ChartWindow extends JFrame
{
  // topmost level container:
  private JPanel topContainer;

  // Holds the attributes:
  private JPanel graphAttCont;

  // Holds the table that contains modifiable data
  private JPanel graphDataCont;

  private ChartManager chartManager;
  private ValueTable valueTable;

  private GridBagConstraints gAttGBC, gDataGBC;
  private GridBagLayout gAttGBL, gDataGBL;

  private JTextField title;
  private JComboBox<Integer> numCategories;

  private Color[] colorSet = {Color.RED, Color.BLUE, Color.BLACK};
  private Integer[] numBarSet = new Integer[14];
  private JComboBox<Color> colors;

  public ChartWindow()
  {
    initialize();
    putContents();

    chartManager = new ChartManager(this);
    chartManager.attachChartPanel();

    pack();
  }

  private void initialize()
  {
    setTitle("Eight Hour Grapher");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

  private void putContents()
  {
    JLabel titleLabel = new JLabel("Title: ");
    JLabel colorLabel = new JLabel("Color: ");
    JLabel numBarsLabel = new JLabel("Bars:");
    BorderLayout topContBL = new BorderLayout();

    topContainer = new JPanel(topContBL);
    title = new JTextField();

    for (int i=1; i<=numBarSet.length; i++)
    {
      numBarSet[i-1] = i;
    }

    title.addActionListener(
        ae ->
        {
          chartManager.updateChartPanel();
          //System.out.println(title.getText());
        }
    );

    colors = new JComboBox<>(colorSet);
    colors.addActionListener(
        ae ->
        {
          chartManager.updateChartPanel(); // create a new chart and re attach
        }
    );

    numCategories = new JComboBox<>(numBarSet);
    numCategories.setSelectedIndex(10);
    numCategories.addActionListener(
        ae ->
        {
          chartManager.updateChartPanel(); // create a new chart and re attach
        }
    );

    initContents();
    topContainer.add(graphAttCont, BorderLayout.WEST);
    //topContainer.add(graphCont, BorderLayout.CENTER);
    //topContainer.add(graphDataCont, BorderLayout.SOUTH);

    JButton testButton1 = new JButton("Click 1");
    JButton testButton2 = new JButton("Click 2");
    addComponent(graphDataCont, testButton1, gDataGBC, 0, 0);
    addComponent(graphDataCont, testButton2, gDataGBC, 1, 0);

    title.setSize(10,1);

    addComponent(graphAttCont, titleLabel, gAttGBC, 0,0);
    addComponent(graphAttCont, title, gAttGBC, 1,0);
    addComponent(graphAttCont, colorLabel, gAttGBC, 0,1);
    addComponent(graphAttCont, colors, gAttGBC, 1,1);
    addComponent(graphAttCont, numBarsLabel, gAttGBC, 0, 2);
    addComponent(graphAttCont, numCategories, gAttGBC, 1, 2);
    setCompsPrefSize(graphAttCont, Constants.GRAPH_ATT_DIM);

    add(topContainer); // add topContainer to this JFrame
  }

  private void setCompsPrefSize(JPanel cont, Dimension newDim)
  {
    Component[] comps = cont.getComponents();
    for (Component comp : comps)
    {
      if (!(comp instanceof JLabel))
      {
        comp.setPreferredSize(newDim);
      }
    }
  }

  private void addComponent(JPanel panel, JComponent comp, GridBagConstraints gbc, int x, int y)
  {
    gbc.gridx = x;
    gbc.gridy = y;
    panel.add(comp, gbc);
  }

  private void initContents()
  {
    gAttGBL = new GridBagLayout();
    gDataGBL = new GridBagLayout();

    gAttGBC = new GridBagConstraints();
    gDataGBC = new GridBagConstraints();

    graphAttCont = new JPanel(gAttGBL);
    graphDataCont = new JPanel(gDataGBL);

    graphAttCont.setPreferredSize(new Dimension(200,300));

    gAttGBL.setConstraints(graphAttCont, gAttGBC);
    gDataGBL.setConstraints(graphDataCont, gDataGBC);
  }

  public int getNumCategories()
  {
    return (Integer) numCategories.getSelectedItem();
  }

  public Color getBarColor()
  {
    return (Color) colors.getSelectedItem();
  }

  public JPanel getTopContainer()
  {
    return topContainer;
  }

  public String getGraphTitle()
  {
    return title.getText();
  }

  void showSelf()
  {
    setVisible(true);
  }
}
