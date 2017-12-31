import javax.swing.*;
import java.awt.Dimension;
public class Constants
{
  public static final int WINDOW_WIDTH = 500;
  public static final int WINDOW_HEIGHT = 300;
  public static final int GRAPH_CONT_WIDTH = 300;
  public static final int GRAPH_CONT_HEIGHT = 300;

  public static final Dimension GRAPH_ATT_DIM = new Dimension(150, 30);

  public static final int GRAPH_CONT_BAR_ZERO = 240;
  public static final int GRAPH_CONT_BAR_MAX = 30;
  public static final int GRAPH_CONT_LEFT_PADDING = 30;
  public static final int GRAPH_CONT_TITLE_START = 150; // padding + (graph container width / 2)

  public static void packPanel(JPanel panel)
  {
    panel.setPreferredSize(panel.getPreferredSize());
  }
}