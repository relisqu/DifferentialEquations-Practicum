/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum.MethodsComputation;

import java.util.ArrayList;

/**
 *
 * @author kopko
 */
public class RungeKuttaMethod extends NumericalMethod{

    @Override
    public ArrayList<Float> GetMethodCalculation(Solvable function, float x, float x0, float y0, float n) {
        ArrayList<Float> values = new ArrayList();
        float y = y0;
        float h = (x - x0) / n;
        for(int i = 0; i <= n; i++){
            float xi = x0 + i * h;
            values.add(y);
            float k1 = function.Compute(xi, y);
            double k2 = function.Compute(xi + h / 2, y + (h * k1) / 2);
            double k3 = function.Compute(xi + h / 2, (float) (y + (h * k2) / 2));
            double k4 = function.Compute(xi + h, (float) (y + h * k3));
            y = (float) (y + h / 6 * (k1 + 2 * k2 + 2 * k3 + k4));
        }
        return values;
    }
    
}
