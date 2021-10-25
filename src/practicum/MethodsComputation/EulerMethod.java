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
public class EulerMethod extends NumericalMethod{

    @Override
    public ArrayList<Float> GetMethodCalculation(Solvable function,float x, float x0, float y0, float n) {
        ArrayList<Float> yValues = new ArrayList();
        float y = y0;
        float h = (x - x0) / n;
        for(int i = 0; i <=n; i++) {
            float xi = x0 + i * h;
            yValues.add(y);
            y += h * function.Compute(xi, y);
        }
        return yValues;
    }

    
}
