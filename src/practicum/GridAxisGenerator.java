
package practicum;

import java.util.ArrayList;
import javafx.scene.chart.XYChart;
import practicum.MethodsComputation.Formula;
import practicum.MethodsComputation.NumericalMethod;
import static practicum.MethodsComputation.NumericalMethod.GetXStepsGlobalErrorValues;
import static practicum.MethodsComputation.NumericalMethod.GetXStepsValues;
import practicum.MethodsComputation.Solvable;

/**
 *
 * @author kopko
 */
public class GridAxisGenerator {

    private final Solvable currentFormula;
    private final float n;
    private final float n0;
    private final float x;
    private final float x0;
    private final float y0;
    
    public GridAxisGenerator(Solvable currentFormula,float n,float x, float x0, float y0,float n0){
        this.currentFormula=currentFormula;
        this.n=n;
        this.n0=n0;
        this.x=x;
        this.x0=x0;
        this.y0=y0;
    }
    
    public XYChart.Series GetFunctionGraphData(NumericalMethod method, String methodName){
            int graphCellsCount = method.GetMethodCalculation(currentFormula,x,x0,y0,n).size();
            XYChart.Series series = new XYChart.Series();
            ArrayList<Float> xAxisValues= GetXStepsValues(x0,y0,n,x);
            ArrayList<Float> yAxisValues= method.GetMethodCalculation(currentFormula,x,x0,y0,n);
            for(int i=0;i<graphCellsCount-1;i++) {
                float xValue=xAxisValues.get(i);
                float yValue= yAxisValues.get(i);
                XYChart.Data data= new XYChart.Data(xValue,yValue);
                series.getData().add(data);
            }
            series.setName(methodName);
            return series;
    }
    
    public XYChart.Series GetLTEGraphData(Formula function,NumericalMethod method, String methodName){
           
            XYChart.Series series = new XYChart.Series();
            int graphCellsCount = method.GetMethodCalculation(currentFormula,x,x0,y0,n).size();
            ArrayList<Float> xAxisValues= GetXStepsValues(x0,y0,n,x);
            ArrayList<Float> yAxisValues= method.GetLocalError(currentFormula,x,x0,y0,n);
            for(int i=0;i<graphCellsCount-2;i++) {
                float xValue = xAxisValues.get(i);
                float yValue = yAxisValues.get(i);
                XYChart.Data data= new XYChart.Data(xValue,yValue);
                series.getData().add(data);
            }
            series.setName(methodName);
            return series;
    }
    XYChart.Series GetGTEGraphData(Formula function,NumericalMethod method, String methodName){
           
            ArrayList<Integer> xAxisValues= GetXStepsGlobalErrorValues(n,n0);
            ArrayList<Float> yAxisValues= method.GetGlobalError(currentFormula,x0,y0,n0,x,n);
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<xAxisValues.size();i++) {
                float xValue = xAxisValues.get(i);
                float yValue = yAxisValues.get(i);
                XYChart.Data data= new XYChart.Data(xValue,yValue);
                series.getData().add(data);
            }
            
            series.setName(methodName);
            return series;
    }
    XYChart.Series GetExactSolutionGraphData(Solvable formula){
      XYChart.Series series = new XYChart.Series();
      ArrayList<Float> xAxisValues= GetXStepsValues(x0,y0,n,x);
      ArrayList<Float> yAxisValues=  formula.GetExactSolution(x0,y0,n,x);
      for(int i=0;i<xAxisValues.size()-1;i++) {
                float xValue=xAxisValues.get(i);
                float yValue=yAxisValues.get(i);
                XYChart.Data data= new XYChart.Data(xValue,yValue);
                series.getData().add(data);
                series.setName("Exact");
                
      }
            
          return series;
    }
}
