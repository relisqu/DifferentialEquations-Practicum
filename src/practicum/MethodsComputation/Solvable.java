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
public interface Solvable {
    
    public float Compute(float x, float y);
    
    public float GetExactSolutionValue(float x,float x0, float y0);
    
    public ArrayList<Float> GetExactSolution(float x0, float y0, float h, float x);
    
}
