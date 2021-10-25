package practicum.Legacy;

import java.util.ArrayList;
import static java.lang.Math.abs;


public class NumericalMethods {

    public NumericalMethods(){}



    public ArrayList<Float> eulerMethod(float x0, float y0, float h, float x){

        ArrayList<Float> fin = new ArrayList();

        float y = y0;
        float last;
        float step = (x - x0) / h;
        for(int i = 0; i <=h; i++) {

            float xi = x0 + i * step;
            fin.add(y);
            last = equation_helper(xi, y);
            y = y + step * last;
        }

        return fin;

    }

    public ArrayList<Float> improvedeEulerMethod(float x0, float y0, float h, float x){

        ArrayList<Float> fin = new ArrayList();

        float y = y0;
        float last = equation_helper(x0, y0);
        float xInCalc, yInCalc;
        float step = (x - x0) / h;

        for(int i = 0; i <= h; i++){

            float xi = x0 + i * step;
            fin.add(y);
            xInCalc = xi + step / 2;
            yInCalc = y + (step / 2) * last;
            y = y + step * equation_helper(xInCalc, yInCalc);
            last = equation_helper(xi + step, y);
        }

        return fin;
    }

    public ArrayList<Float> rungeKuttaMethod(float x0, float y0, float h, float x){

        ArrayList<Float> fin = new ArrayList();

        float y = y0;
        float last = equation_helper(x0, y0);
        float step = (x - x0) / h;

        for(int i = 0; i <= h; i++){

            float xi = x0 + i * step;
            fin.add(y);
            float k1 = last;
            double k2 = equation_helper(xi + step / 2, y + (step * k1) / 2);
            double k3 = equation_helper(xi + step / 2, (float) (y + (step * k2) / 2));
            double k4 = equation_helper(xi + step, (float) (y + step * k3));
            y = (float) (y + step / 6 * (k1 + 2 * k2 + 2 * k3 + k4));
            last = equation_helper(xi + step, y);
        }

        return fin;
    }

    public ArrayList<Float> exactSolution(float x0, float y0, float h, float x){
        ArrayList<Float> fin = new ArrayList();
        float y = y0;
        float last;
        float step = (x - x0) / h;

        for (int i=0;i<=h;i++){
            float xi = x0 + i * step;
            fin.add(y);
            y = solution(xi,x0,y0);
        }

        return fin;

    }
    public float solution(float x,float x0, float y0){
        float c1= (float) ((2*Math.pow(x0, 4) - y0 * Math.pow(x0, 5)) / (2 + y0*x0));
        float solution = (-2/x) + ((4 * x * x * x) / (x * x * x * x + c1));
        return solution;
    }
    public ArrayList<Float> x_values(float x0, float y0, float h, float x){
        ArrayList<Float> finx = new ArrayList<Float>();
        float xi=x0;
        float step=(x-x0)/h;
        for (int i=1;i<=h;i++){
            finx.add(xi);
            xi=finx.get(i-1)+step;
        }
        return finx;
    }

    public ArrayList<Float> Euler_localerror(float x0, float y0, float h, float x){
        ArrayList<Float> lerrorfin = new ArrayList<Float>();
        for (int i=1;i<h;i++){
            lerrorfin.add(Math.abs((eulerMethod(x0, y0, h,x).get(i-1)) - solution(x_values(x0, y0, h,x).get(i-1), x0, y0)) - (eulerMethod(x0, y0, h,x).get(i)) - solution(x_values(x0, y0,  h,x).get(i), x0, y0));
        }
        return lerrorfin;
    }
    public ArrayList<Float> ImpEuler_localerror(float x0, float y0, float h, float x){
        ArrayList<Float> lerrorfin = new ArrayList<Float>();
        for (int i=1;i<h;i++){
            lerrorfin.add(java.lang.Math.abs((improvedeEulerMethod(x0, y0, h, x).get(i-1)) - solution(x_values(x0, y0, h, x).get(i-1), x0, y0)) - (improvedeEulerMethod(x0, y0, h, x).get(i)) - solution(x_values(x0, y0, h, x).get(i), x0, y0));
        }
        return lerrorfin;
    }
    public ArrayList<Float> runge_localerror(float x0, float y0, float h,float x){
        ArrayList<Float> lerrorfin = new ArrayList<Float>();
        for (int i=1;i<h;i++){
            lerrorfin.add(Math.abs((rungeKuttaMethod(x0, y0, h,x).get(i-1)) 
                    - solution(x_values(x0, y0, h,x).get(i-1), x0, y0)) 
                    - (rungeKuttaMethod(x0, y0, h,x).get(i)) 
                    - solution(x_values(x0, y0, h,x).get(i), x0, y0));
        }
        return lerrorfin;
    }


    public ArrayList<Float> Euler_globalerror(float x0, float y0, float h,float x ,float N0){
        ArrayList<Float> gerrorfin = new ArrayList<Float>();
        float s=solution(x,x0,y0);
        ArrayList<Integer> N = new ArrayList<Integer>();
        for (int i = (int)N0; i <=h ; i++) {
            N.add(i);
            gerrorfin.add(Math.abs(eulerMethod(x0, y0, h, x).get(i)- s));
        }
        return gerrorfin;
    }
    public ArrayList<Float> ImpEuler_globalerror(float x0, float y0,  float h,float x, float N0){
        ArrayList<Float> gerrorfin = new ArrayList<Float>();
        float s=solution(x,x0,y0);
        ArrayList<Integer> N = new ArrayList<Integer>();
        for (int i = (int)N0; i <=h ; i++) {
            N.add(i);
            gerrorfin.add(Math.abs(improvedeEulerMethod(x0, y0, h, x).get(i)- s));
        }
        return gerrorfin;


    }
    public ArrayList<Float> Range_globalerror(float x0, float y0, float h, float x, float N0){
        ArrayList<Float> gerrorfin = new ArrayList<Float>();
        float s=solution(x,x0,y0);
        ArrayList<Integer> N = new ArrayList<Integer>();
        for (int i = (int)N0; i <=h ; i++) {
            N.add(i);
            gerrorfin.add(Math.abs(rungeKuttaMethod(x0, y0, h, x).get(i)- s));
        }
        return gerrorfin;
    }
    public ArrayList<Integer> X_values_gerror(float x0, float y0, float h, float x, float N0){
        ArrayList<Integer> N = new ArrayList<Integer>();
        for (int i = (int)N0; i <h ; i++) {
            N.add(i);
        }
        return N;
    }




    float equation_helper(float x, float y)
    {
        return (4 / (x * x)) - (y / x) - y * y;
    }

}
