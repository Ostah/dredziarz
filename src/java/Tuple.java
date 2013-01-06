/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jacek
 */
public class Tuple 
{
    int id;
    float cId;
    float pId;
    int fuzzyPercent;
    int conditionalPercent;
    int bipolarPercent;
    int distance;
    
    public float isSameDistance(Tuple k){
        if(k.distance == distance) return 1.0f;
        return 0.0f;
    }
}
