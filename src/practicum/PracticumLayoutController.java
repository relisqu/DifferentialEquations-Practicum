
package practicum;

import GUIExtentions.GapLineChart;
import GUIExtentions.GapNumberAxis;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import jfxtras.styles.jmetro.JMetroStyleClass;
import practicum.MethodsComputation.EulerMethod;
import practicum.MethodsComputation.Formula;
import practicum.MethodsComputation.ImprovedEulerMethod;
import practicum.MethodsComputation.RungeKuttaMethod;

/**
 * FXML Controller class
 *
 *  @author Kopeikina Anna
 */
public class PracticumLayoutController implements Initializable {

    @FXML
    private LineChart<Number,Number> FunctionChart;
    @FXML
    private LineChart<Number,Number> LteChart;
    @FXML
    private LineChart<Number,Number> GteChart;
    @FXML
    private CheckBox ExactSolutionCheckbox;
    @FXML
    private CheckBox EulerCheckbox;
    @FXML
    private CheckBox ImprovedEulerCheckbox;
    @FXML
    private CheckBox RungeKuttaCheckbox;
    @FXML
    private TextField X0Field;
    @FXML
    private TextField Y0Field;
    @FXML
    private TextField HField;
    @FXML
    private TextField XField;
    @FXML
    private TextField N0Field;
    
    private float x0=0;
    private float y0=0;
    private float n=0;
    private float x=0;
    private float n0=0;
    @FXML
    private VBox BackgroundVBox;
    private Formula solvedFormula;
    @FXML
    private StackPane FuncGraphPane;
    @FXML
    private StackPane LTEGraphPane;
    @FXML
    private StackPane GTEGraphPane;
    
    List<XYChart.Series> graphLines= new ArrayList<>();
    List<XYChart.Series> lteLines= new ArrayList<>();
    List<XYChart.Series> gteLines= new ArrayList<>();
    
    private GridAxisGenerator axisGenerator;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        BackgroundVBox.getStyleClass().add(JMetroStyleClass.BACKGROUND);
        solvedFormula= new Formula();
        FunctionChart.setCreateSymbols(false);
        GteChart.setCreateSymbols(false);
        LteChart.setCreateSymbols(false);
    }    

    @FXML
    private void RecalculateGraph(ActionEvent event) {
    
    }
    private boolean AnyNumericalCheckBoxIsSelected(){
        return RungeKuttaCheckbox.isSelected()|| EulerCheckbox.isSelected()|| ImprovedEulerCheckbox.isSelected();
    };
    @FXML
    private void Calculate(ActionEvent event) {
        clearCharts();
        CheckConditions(!N0Field.getText().isEmpty());
        axisGenerator= new GridAxisGenerator(solvedFormula, n, x, x0, y0, n0);
        
        if(ExactSolutionCheckbox.isSelected()){
            graphLines.add(axisGenerator.GetExactSolutionGraphData(solvedFormula)); 
        }
       
        if(EulerCheckbox.isSelected()){
            graphLines.add(axisGenerator.GetFunctionGraphData(new EulerMethod(),"Euler"));
            lteLines.add(axisGenerator.GetLTEGraphData(solvedFormula,new EulerMethod(),"Euler"));
            if(!N0Field.getText().isEmpty()) gteLines.add(axisGenerator.GetGTEGraphData(solvedFormula,new EulerMethod(),"Euler"));
        }  
        if(ImprovedEulerCheckbox.isSelected()){
            graphLines.add(axisGenerator.GetFunctionGraphData(new ImprovedEulerMethod(),"ImprovedEuler"));
            lteLines.add(axisGenerator.GetLTEGraphData(solvedFormula,new ImprovedEulerMethod(),"ImprovedEuler"));
            if(!N0Field.getText().isEmpty()) gteLines.add(axisGenerator.GetGTEGraphData(solvedFormula,new ImprovedEulerMethod(),"ImprovedEuler"));
        }   
        if(RungeKuttaCheckbox.isSelected()){
            graphLines.add(axisGenerator.GetFunctionGraphData(new RungeKuttaMethod(),"RungeKutta"));
            lteLines.add(axisGenerator.GetLTEGraphData(solvedFormula,new RungeKuttaMethod(),"RungeKutta"));
            if(!N0Field.getText().isEmpty()) gteLines.add(axisGenerator.GetGTEGraphData(solvedFormula,new RungeKuttaMethod(),"RungeKutta"));
        }   
        
        new ZoomManager(FuncGraphPane, FunctionChart, graphLines);
        if(AnyNumericalCheckBoxIsSelected())new ZoomManager(LTEGraphPane, LteChart, lteLines);//Dirty code for external library to turn on zoom of graph.
        if(!N0Field.getText().isEmpty()) new ZoomManager(GTEGraphPane, GteChart, gteLines);
    }
    void CheckConditions(boolean n0Matters){
        if((x-x0)/n>1){
            AlertRaiser.showWarningAlert("Size of current function step is bigger than 1, code can be unstable.", "Step size warning");
        }
        if(x0>=x){
            AlertRaiser.showWarningAlert("The range is either 0 or couldn't be built, code can be unstable.", "Range warning");
        }
        if(!RungeKuttaCheckbox.isSelected() && !EulerCheckbox.isSelected()
                &&!ImprovedEulerCheckbox.isSelected()&&!ExactSolutionCheckbox.isSelected()){
        
            AlertRaiser.showWarningAlert("No graph checkboxes methods were selected.", "Graph drawing warning");

        }
        if(!n0Matters)return;
        
        if((x-x0)/n0>1){
            AlertRaiser.showWarningAlert("Size of current function step for some values of n for GTE is bigger than 1, code can be unstable.",  "Step size warning");
        }
        
    }
    private void clearCharts(){
        FunctionChart.getData().clear();
        GteChart.getData().clear();
        LteChart.getData().clear();
        graphLines.clear();
        lteLines.clear();
        gteLines.clear();
    }
    @FXML
    private void SetX0Value(Event event) {
     try{
            x0= Float.parseFloat(X0Field.getText());
        }catch(Exception e){
        }
    }

    @FXML
    private void SetY0Value(Event event) {
        try{
            y0= Float.parseFloat(Y0Field.getText());
        }catch(NumberFormatException e){
        }
    }

    @FXML
    private void SetHValue(Event event) {
        try{
            n= Float.parseFloat(HField.getText());
        }catch(NumberFormatException e){
           
        }
    }

    @FXML
    private void SetXValue(Event event) {
         try{
            x= Float.parseFloat(XField.getText());
        }catch(NumberFormatException e){
           
        }
    }

    @FXML
    private void SetN0Value(Event event) {
        try{
            n0= Float.parseFloat(N0Field.getText());
        }catch(NumberFormatException e){
           
        }
    }
}
