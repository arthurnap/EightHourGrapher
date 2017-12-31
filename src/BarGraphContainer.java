import javax.swing.*;
import java.awt.*;

public class BarGraphContainer extends JPanel
{
  private int numBars;
  private int[] heights;
  private int[] intervals;

  public BarGraphContainer(LayoutManager layout)
  {
    super(layout);
    setPreferredSize(new Dimension(400,100));
    initialize();
    repaint();
  }

  @Override
  public void paintComponent(Graphics g)
  {
    drawGraph(g);
  }

  private void initialize()
  {
    numBars = 1;
    setBackground(Color.WHITE);
    setSize(Constants.GRAPH_CONT_WIDTH, Constants.GRAPH_CONT_HEIGHT);
  }

  private void drawGraph(Graphics g)
  {
    super.paintComponent(g);
    g.setColor(Color.ORANGE);
    for (int i=0; i < numBars; i++)
    {
      g.fillRect(Constants.GRAPH_CONT_LEFT_PADDING + (i*25), Constants.GRAPH_CONT_BAR_MAX, 20, Constants.GRAPH_CONT_BAR_ZERO);
    }
    g.setColor(Color.BLACK);
    g.drawString("Test title", Constants.GRAPH_CONT_TITLE_START, 20);
    /*
     * First need to find the current size of BorderLayout.Center on which
     * to draw the bar graph.
     */
  }

  public void setNumBars(int val)
  {
    numBars = val;
  }

  public void setHeights(int[] vals)
  {
    heights = vals;
  }

  public void setIntervals(int[] vals)
  {
    intervals = vals;
  }

}