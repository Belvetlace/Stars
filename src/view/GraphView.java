package view;

import data.SparseMat;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;

public class GraphView extends ScatterChart<Number, Number>
{
   private SparseMat<Character>[] starMap;

   public GraphView(SparseMat<Character>[] starMap, String title)
   {
      super(new NumberAxis(), new NumberAxis());
      setTitle(title);
      this.starMap = starMap;
      final NumberAxis xAxis = (NumberAxis) getXAxis();
      final NumberAxis yAxis = (NumberAxis) getYAxis();
      xAxis.setForceZeroInRange(false);
      yAxis.setForceZeroInRange(false);
//      xAxis.setLabel("X-coordinates");
//      yAxis.setLabel("Y-coordinates");
   }

   public void update(int p)
   {
      ScatterChart.Series<Number, Number> seriesS
              = new ScatterChart.Series<>();
      seriesS.setName("Sun");
      ScatterChart.Series<Number, Number> series
              = new ScatterChart.Series<>();
      series.setName("Stars near the earth");
      ScatterChart.Series<Number, Number> seriesNearest
              = new ScatterChart.Series<>();
      seriesNearest.setName("10 stars nearest to Earth");
      if(p == 0) // 0=(y,x) 1=(y,z) 2=(z,x)
      {
         this.getXAxis().setLabel("Y-coordinates");
         this.getYAxis().setLabel("X-coordinates");
      } else if (p == 1) {
         this.getXAxis().setLabel("Y-coordinates");
         this.getYAxis().setLabel("Z-coordinates");
      }  else {
         this.getXAxis().setLabel("Z-coordinates");
         this.getYAxis().setLabel("X-coordinates");
      }

      Character temp;
      for (int r = 0; r < starMap[p].getRowSize(); r++)
      {
         if (!starMap[p].getRows().get(r).isEmpty())
         {
            for (int c = 0; c < starMap[p].getColSize(); c++)
            {
               temp = starMap[p].get(r, c);
               if (temp != ' ')
               {
                  if (temp == 'S')
                     seriesS.getData().add(new
                             ScatterChart.Data<>(r, c));
                  else if (temp == '*')
                     series.getData().add(new
                             ScatterChart.Data<>(r, c));
                  else
                     seriesNearest.getData().add(new
                             ScatterChart.Data<>(r, c));
               }
            }
         }
      }
      this.getData().add(series);
      this.getData().add(seriesS);
      this.getData().add(seriesNearest);
   }

   public void clear()
   {
      this.getData().clear();
   }

}
