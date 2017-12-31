import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;

public class TestBarChart extends JFrame
{
  public TestBarChart(String var1)
  {
    this.setLayout(new BorderLayout());

    JPanel var2 = createDemoPanel();
    JPanel userPanel = userInputPanel();

    var2.setPreferredSize(new Dimension(500, 270));

    this.add(userPanel, BorderLayout.NORTH);
    this.add(var2, BorderLayout.CENTER);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //this.setContentPane(var2);
  }

  private JPanel userInputPanel()
  {
    JTextField field1 = new JTextField();
    JTextField field2 = new JTextField();

    field1.setPreferredSize(new Dimension(100, 25));
    field2.setPreferredSize(new Dimension(100, 25));

    JPanel userPanel = new JPanel(new GridLayout(2,2));

    userPanel.add(new JLabel("Label1"));
    userPanel.add(field1);
    userPanel.add(new JLabel("Label2"));
    userPanel.add(field2);

    return userPanel;
  }

  private static CategoryDataset createDataset()
  {
    String var0 = "First";
    String var1 = "Second";
    String var2 = "Third";
    String var3 = "Category 1";
    String var4 = "Category 2";
    String var5 = "Category 3";
    String var6 = "Category 4";
    String var7 = "Category 5";
    DefaultCategoryDataset var8 = new DefaultCategoryDataset();
    var8.addValue(1.0D, var0, var3);
    var8.addValue(2.0D, var0, var4);
    var8.addValue(3.0D, var0, var5);
    var8.addValue(5.0D, var0, var6);
    var8.addValue(5.0D, var0, var7);
    var8.addValue(5.0D, var1, var3);
    var8.addValue(7.0D, var1, var4);
    var8.addValue(6.0D, var1, var5);
    var8.addValue(8.0D, var1, var6);
    var8.addValue(4.0D, var1, var7);
    var8.addValue(4.0D, var2, var3);
    var8.addValue(3.0D, var2, var4);
    var8.addValue(2.0D, var2, var5);
    var8.addValue(3.0D, var2, var6);
    var8.addValue(6.0D, var2, var7);
    return var8;
  }

  // TODO: need to put createChart() inside repaint(), repaint whenever a user input item's value is changed
  private static JFreeChart createChart(CategoryDataset var0)
  {
    JFreeChart var1 = ChartFactory.createBarChart("Bar Chart Demo 1", "HKJHK", "Dose", var0);
    CategoryPlot var2 = (CategoryPlot)var1.getPlot();
    var2.setDomainGridlinesVisible(true);
    var2.setRangeCrosshairVisible(true);
    var2.setRangeCrosshairPaint(Color.blue);

    NumberAxis var3 = (NumberAxis)var2.getRangeAxis();
    var3.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
    BarRenderer var4 = (BarRenderer)var2.getRenderer();
    var4.setDrawBarOutline(false);
    GradientPaint var5 = new GradientPaint(0.0F, 0.0F, Color.blue, 0.0F, 0.0F, new Color(0, 0, 64));
    GradientPaint var6 = new GradientPaint(0.0F, 0.0F, Color.green, 0.0F, 0.0F, new Color(0, 64, 0));
    GradientPaint var7 = new GradientPaint(0.0F, 0.0F, Color.red, 0.0F, 0.0F, new Color(64, 0, 0));
    var4.setSeriesPaint(0, var5);
    var4.setSeriesPaint(1, var6);
    var4.setSeriesPaint(2, var7);
    var4.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
    CategoryAxis var8 = var2.getDomainAxis();
    var8.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(1.5708D));
    return var1;
  }


  public static JPanel createDemoPanel()
  {
    JFreeChart var0 = createChart(createDataset());
    return new ChartPanel(var0);
  }

  public static void main(String[] args)
  {
    TestBarChart var1 = new TestBarChart("JFreeChart: BarChartDemo1.java");
    var1.pack();
    RefineryUtilities.centerFrameOnScreen(var1);
    var1.setVisible(true);

  }
}
