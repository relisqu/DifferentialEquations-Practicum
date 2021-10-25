package practicum.MethodsComputation;

import java.util.ArrayList;
import GUIExtentions.AlertRaiser;

/**
 *
 * @author kopko
 */
public class Formula implements Solvable{
    
    private boolean isComputable;
    @Override
    public float Compute(float x, float y) {
        return (2-y*y)/(2*x*x*y);
                
    }
    
    @Override
    public float GetExactSolutionValue(float x, float x0, float y0) {
        float c1= (float) ((y0*y0-2)*Math.pow(Math.E, (-1/x0)));
        System.out.println("doubles: "+(c1*Math.pow(Math.E, (1/x))+2)+" "+(-c1*Math.pow(Math.E, (1/x))+2));
        
        boolean b1= c1>=0;
        boolean b2= 1/x<=Math.log(-2/c1)&& c1<0;
        if(!b1 && !b2){
            isComputable=false;
        }
        if(y0>0){
            return (float)(Math.sqrt(c1*Math.pow(Math.E, (1/x))+2));
        }
            return (float)(-Math.sqrt(c1*Math.pow(Math.E, (1/x))+2));
    }
    
    @Override
    public ArrayList<Float> GetExactSolution(float x0, float y0, float h, float x){
        ArrayList<Float> fin = new ArrayList();
        isComputable=true;
        float y;
        float step = (x - x0) / h;

        for (int i=0;i<=h;i++){
            float xi = x0 + i * step;
            y= GetExactSolutionValue (xi,x0,y0);
              System.out.println("YValue: "+y);
            fin.add(y);
        }
        if(!isComputable){
        
            AlertRaiser.showWarningAlert("Domain exception", "Domain exception");
            
        }
        return fin;

    }
}
