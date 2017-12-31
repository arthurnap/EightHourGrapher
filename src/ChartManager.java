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

import javax.swing.*;
import java.awt.*;

/*
 * Adds a graph into the JFrame
 */
public class ChartManager {
  // categories and vals must have the same length.
  private ChartWindow topGUI;

  private String[] categories;
  private double[] vals;

  private String rowKey = "default";
  private Color barColor = Color.BLUE;

  // graph information
  private String graphTitle = "Eight Hour Grapher";
  private int numCategories;

  private JPanel chartPanel;
  private ValueTable valueTable;

  public ChartManager(ChartWindow topGUI) {
    this.topGUI = topGUI;
    numCategories = topGUI.getNumCategories();
    initializeChart();
    initializeValueTable();
  }

  private void initializeValueTable()
  {
    valueTable = new ValueTable(categories, vals);
  }

  private void initializeChart() {
    vals = new double[numCategories];
    categories = new String[numCategories];

    for (int i=0; i<numCategories; i++) {
      vals[i] = 10;
      categories[i] = "Category" + i;
    }
  }

  // create the dataset that will be reflected on the graph
  private CategoryDataset createDataset() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();

    for (int i = 0; i < numCategories; i++) {
      dataset.addValue(vals[i], rowKey, categories[i]);
    }
    return dataset;
  }

  public JFreeChart createBarChart(CategoryDataset dataset) {
    JFreeChart chart = ChartFactory.createBarChart(graphTitle, "Category", "Values", dataset);
    CategoryPlot plot = (CategoryPlot) chart.getPlot();
    plot.setDomainGridlinesVisible(true);
    plot.setRangeCrosshairVisible(true);
    plot.setRangeCrosshairPaint(Color.blue);

    NumberAxis axis = (NumberAxis) plot.getRangeAxis();
    axis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

    BarRenderer renderer = (BarRenderer) plot.getRenderer();
    renderer.setDrawBarOutline(false);

    GradientPaint color = new GradientPaint(0.0F, 0.0F, barColor, 0.0F, 0.0F, new Color(0, 0, 64));
    renderer.setSeriesPaint(0, color);

    renderer.setLegendItemToolTipGenerator(new StandardCategorySeriesLabelGenerator("Tooltip: {0}"));
    CategoryAxis categoryAxis = plot.getDomainAxis();

    // vertical category labels
    categoryAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(1.5708D));

    return chart;
  }

  public JPanel createChartPanel() {
    JFreeChart chart = createBarChart(createDataset());
    return new ChartPanel(chart);
  }

  public void attachChartPanel() {
    chartPanel = createChartPanel();
    chartPanel.setPreferredSize(new Dimension(500, 270));

    topGUI.getTopContainer().add(valueTable.getValueTable(), BorderLayout.SOUTH);
    topGUI.getTopContainer().add(chartPanel, BorderLayout.CENTER);
  }

  /*
   * Removes the chart panel from the top container of the app's window, then creates a
   * new one and reattaches it as the new chart panel. Revalidate the top container to
   * complete the reattachment of the updated panel.
   */
  public void updateChartPanel() {
    updateGraphInfo();

    System.out.println("Title of the Graph is: " + graphTitle);
    topGUI.getTopContainer().remove(chartPanel);
    attachChartPanel();
    topGUI.getTopContainer().revalidate();
  }

  /*
   * Fetch the attributes currently entered on the ChartWindow's attribute container.
   */
  public void getGraphInfo() {
    if (topGUI.getTitle().isEmpty())
      graphTitle = "Eight Hour Grapher";
    else
      graphTitle = topGUI.getGraphTitle();

    numCategories = topGUI.getNumCategories();
    barColor = topGUI.getBarColor();
  }

  /*
   * Copy the vals and categories array first, then grow it for the update.
   */
  public void updateGraphInfo()
  {
    int numCategoriesOld = vals.length;
    int i;

    getGraphInfo();

    double[] temp1 = new double[numCategories];
    String[] temp2 = new String[numCategories];

    if (numCategoriesOld < numCategories)
    {
      String[] existingCategories = valueTable.getCategoryList();
      double[] existingVals = valueTable.getValues();

      for (i = 0; i < numCategoriesOld; i++)
      {
        temp1[i] = existingVals[i];
        temp2[i] = existingCategories[i];
      }

      for (; i < numCategories; i++)
      {
        temp1[i] = 10;
        temp2[i] = "Category " + i;
      }
      vals = temp1;
      categories = temp2;
      valueTable.createValueTable(temp2, temp1);
    }
    else
    {
      valueTable.updateFieldsList(numCategories);

      vals = valueTable.getValues();
      categories = valueTable.getCategoryList();
    }
  }
}
