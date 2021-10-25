package practicum.MethodsComputation;

import java.util.ArrayList;

/**
 *
 * @author kopko
 */
public class Formula implements Solvable{

    @Override
    public float Compute(float x, float y) {
        return (2-y*y)/(2*x*x*y);
                
    }
    
    @Override
    public float GetExactSolutionValue(float x, float x0, float y0) {
        float c1=(float) (Math.log(Math.abs(y0*y0-2)))-1/x0;
        
        System.out.println("c1: "+c1+" mtah: "+(float)(Math.sqrt(2+Math.pow(Math.E, 1/x+c1))));
        
        return (float)(Math.sqrt(2-Math.pow(Math.E, 1/x+c1)));
    }
    
    @Override
    public ArrayList<Float> GetExactSolution(float x0, float y0, float h, float x){
        ArrayList<Float> fin = new ArrayList();
        float y;
        float step = (x - x0) / h;

        for (int i=0;i<=h;i++){
            float xi = x0 + i * step;
            y= GetExactSolutionValue (xi,x0,y0);
              System.out.println("YValue: "+y);
            fin.add(y);
        }

        return fin;

    }
}
