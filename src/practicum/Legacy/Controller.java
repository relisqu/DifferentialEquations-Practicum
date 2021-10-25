package practicum.Legacy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Controller {

    ObservableList<String> methodchoosinglist=FXCollections.observableArrayList("Euler","Improved Euler","Runge Kutta");

    @FXML
    private LineChart<Float,Float> Line_chart;

    @FXML
    private CategoryAxis x;

    @FXML
    private NumberAxis y;

    @FXML
    private ChoiceBox<String> M;

    @FXML
    private CheckBox Euler;
    @FXML
    private CheckBox exact_choice;
    @FXML
    private CheckBox Impeuler;
    @FXML
    private CheckBox Runge;
    @FXML
    private CheckBox GerrorEuler;
    @FXML
    private CheckBox GerrorImpeuler;
    @FXML
    private CheckBox GerrorRunge;
    @FXML
    private CheckBox LerrorEuler;
    @FXML
    private CheckBox LerrorImpeuler;
    @FXML
    private CheckBox LerrorRunge;
    @FXML
    private TextField  Xo_text;
    @FXML
    private TextField  x_text;
    @FXML
    private TextField  yo_text;
    @FXML
    private TextField  h_text;
    @FXML
    private TextField  N0_text;

    float Xo=0;
    float Yo=0;
    float X=0;
    float N=0;
    float N0=0;

    NumericalMethods Nm = new NumericalMethods();

    @FXML
    private void initialize(){

        Xo_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()== KeyCode.ENTER){
                    Xo = Integer.parseInt(Xo_text.getText());
                    System.out.println(Xo);
                }
            }
        });
        yo_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()== KeyCode.ENTER){
                    Yo = Integer.parseInt(yo_text.getText());
                }
            }
        });
        x_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()== KeyCode.ENTER){
                    X= Integer.parseInt(x_text.getText());
                }
            }
        });
        h_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()== KeyCode.ENTER){
                    N = Integer.parseInt(h_text.getText());
                }
            }
        });
        N0_text.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode()== KeyCode.ENTER){
                    N = Integer.parseInt(N0_text.getText());
                }
            }
        });

    }
    @FXML
    public void draw(){

        if(exact_choice.isSelected()){
            System.out.println("euler");

            int loop = Nm.eulerMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            System.out.println(loop);
            for(int i=0;i<loop-1;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Integer.parseInt(Xo_text.getText()),Integer.parseInt(yo_text.getText()),Integer.parseInt(h_text.getText()),Integer.parseInt(x_text.getText())).get(i)), Nm.exactSolution(Xo,Yo,N,X).get(i)));


            }
            Line_chart.getData().addAll(series);
        }
        if(Euler.isSelected()){
            System.out.println("euler");

            int loop = Nm.eulerMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            System.out.println(loop);
            for(int i=0;i<loop-1;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Integer.parseInt(Xo_text.getText()),Integer.parseInt(yo_text.getText()),Integer.parseInt(h_text.getText()),Integer.parseInt(x_text.getText())).get(i)), Nm.eulerMethod(Xo,Yo,N,X).get(i)));


            }
            Line_chart.getData().addAll(series);
        }
        if (Impeuler.isSelected()){
            System.out.println("imp");

            int loop = Nm.improvedeEulerMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<loop-2;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Xo,Yo,N,X).get(i)), Nm.improvedeEulerMethod(Xo,Yo,N,X).get(i)));


            }
            Line_chart.getData().addAll(series);
        }
        if (Runge.isSelected()){
            System.out.println("runge");

            int loop = Nm.rungeKuttaMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<loop-2;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Xo,Yo,N,X).get(i)), Nm.rungeKuttaMethod(Xo,Yo,N,X).get(i)));


            }
            Line_chart.getData().addAll(series);
        }
        if(LerrorEuler.isSelected()){
            System.out.println("euler");

            int loop = Nm.eulerMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<loop-2;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Xo,Yo,N,X).get(i)), -(Nm.Euler_localerror(Xo,Yo,N,X).get(i))));


            }
            Line_chart.getData().addAll(series);
        }
        if (LerrorImpeuler.isSelected()){


            int loop = Nm.improvedeEulerMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<loop-2;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Xo,Yo,N,X).get(i)), -(Nm.ImpEuler_localerror(Xo,Yo,N,X).get(i))));


            }
            Line_chart.getData().addAll(series);
        }
        if (LerrorRunge.isSelected()){
            System.out.println("runge");

            int loop = Nm.rungeKuttaMethod(Xo,Yo,N,X).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<loop-2;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.x_values(Xo,Yo,N,X).get(i)), -(Nm.runge_localerror(Xo,Yo,N,X).get(i))));


            }
            Line_chart.getData().addAll(series);

        }
        if(GerrorEuler.isSelected()){
            System.out.println("euler_glob");

            int loop = Nm.Euler_globalerror(Xo,Yo,N,X,N0).size();
            XYChart.Series series = new XYChart.Series();
            N=Float.parseFloat(h_text.getText());
            for(int i=0;i<N-1;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.X_values_gerror(Xo,Yo,N,X,N0).get(i)), Nm.Euler_globalerror(Xo,Yo,N,X,N0).get(i)));
                System.out.println(Nm.Euler_globalerror(Xo,Yo,N,X,N0).get(i)+"ghjk");

            }
            Line_chart.getData().addAll(series);
            System.out.println(N+" h hai ye ");
        }
        if (GerrorImpeuler.isSelected()){
            System.out.println("imp");
            N=Float.parseFloat(h_text.getText());
            int loop = Nm.ImpEuler_globalerror(Xo,Yo,N,X,N0).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<N-1;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.X_values_gerror(Xo,Yo,N,X,N0).get(i)), Nm.ImpEuler_globalerror(Xo,Yo,N,X,N0).get(i)));


            }
            Line_chart.getData().addAll(series);
        }
        if (GerrorRunge.isSelected()){
            System.out.println("runge");
            N=Float.parseFloat(h_text.getText());
            int loop = Nm.Range_globalerror(Xo,Yo,N,X,N0).size();
            XYChart.Series series = new XYChart.Series();
            for(int i=0;i<N-1;i++) {
                series.getData().add(new XYChart.Data(String.valueOf(Nm.X_values_gerror(Xo,Yo,N,X,N0).get(i)), Nm.Range_globalerror(Xo,Yo,N,X,N0).get(i)));


            }
            Line_chart.getData().addAll(series);
            

        }
    }







}
