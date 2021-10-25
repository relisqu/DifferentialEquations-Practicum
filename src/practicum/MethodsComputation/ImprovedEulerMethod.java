package practicum.MethodsComputation;

import java.util.ArrayList;

/**
 *
 * @author kopko
 */
public class ImprovedEulerMethod extends NumericalMethod{

    @Override
    public ArrayList<Float> GetMethodCalculation(Solvable function, float x, float x0, float y0, float n) {
        ArrayList<Float> values = new ArrayList();
        float y = y0;
        float h = (x - x0) / n;
        
        for(int i = 0; i <= n; i++){
            values.add(y);
            float xi = x0 + i * h;
            y += h * function.Compute(xi + h / 2, y + (h / 2) * function.Compute(xi, y));
        }

        return values;
    }
    
}
