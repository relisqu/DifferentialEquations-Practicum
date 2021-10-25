/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practicum.MethodsComputation;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Kopeikina Anna
 */
public abstract class NumericalMethod{
    
    public abstract ArrayList<Float> GetMethodCalculation(Solvable function, float x, float x0, float y0, float h );
    
    public ArrayList<Float> GetLocalError(Solvable function, float x, float x0, float y0, float n){
        float h=(x-x0)/n;
        ArrayList<Float> errorArray = new ArrayList<>();
            
        for(int i=0;i<n;i++) {
            float xi = x0 + i * h;
            float analyticResult= function.GetExactSolutionValue(xi,x0,y0);
            errorArray.add(Math.abs(analyticResult-GetMethodCalculation(function,x, x0, y0, n).get(i)));
        }
        return errorArray;
    }
    
    public ArrayList<Float> GetGlobalError(Solvable function, float x0, float y0, float n0, float x, float n){
        ArrayList<Float> errorArray = new ArrayList<>();
        for (int nI = (int)n0; nI <=n ; nI++) {
            
            ArrayList<Float> list= GetLocalError(function,x, x0, y0, nI);
            
            float maxError= Collections.max(list);
            errorArray.add(maxError);
        }
        return errorArray;
    }
    
    public static ArrayList<Float> GetXStepsValues(float x0, float y0, float n, float x){
        ArrayList<Float> xAxis = new ArrayList<>();
        float xi=x0;
        float h=(x-x0)/n;
        for (int i=1;i<=n;i++){
            xAxis.add(xi);
            xi=xAxis.get(i-1)+h;
        }
        return xAxis;
    }
    public static ArrayList<Integer> GetXStepsGlobalErrorValues(float n, float n0){
        ArrayList<Integer> nValues = new ArrayList<>();
        for (int i = (int)n0; i <=n ; i++) {
            nValues.add(i);
        }
        return nValues;
    }
}
