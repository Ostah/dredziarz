
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Ostah
 */
public class Zadrozny {
    
    public enum tNorm {
		MINIMUM, PRODUCT, T_LUKASIEWICZ
    }
    
    public enum sNorm {
		MAXIMUM, PROB_SUM, S_LUKASIEWICZ
    }
    
    static float compute(ArrayList<Tuple> list, Tuple tuple, tNorm t, sNorm s, Boolean circumstancial){      
        float internalMax = 0.0f;
        float outerMax;
       
        for (int i = 0; i < list.size(); i++) {   
            float Cs = list.get(i).cId;
            if(circumstancial) Cs = t_norm(tuple.isSameDistance(list.get(i)),list.get(i).cId,t);
            float minimum = t_norm(Cs, list.get(i).pId,t);
            if (internalMax < minimum) internalMax = minimum;
        }

        outerMax = s_norm(1-internalMax, tuple.pId,s);
        return t_norm(tuple.cId, outerMax,t);
    }
    
    private static float t_norm(float a, float b, tNorm t)
    {
        switch(t){
            case MINIMUM:
                return Math.min(a, b);
            case PRODUCT:
                return a*b;
            case T_LUKASIEWICZ:
                return Math.max(0, a+b-1);
        }
        return 0.0f;
    }
    private static float s_norm(float a, float b, sNorm s)
    {
        switch(s){
            case MAXIMUM:
                return Math.max(a, b);
            case PROB_SUM:
                return a+b-a*b;
            case S_LUKASIEWICZ:
                return Math.min(1, a+b);
        }
        return 0.0f;
    }
}
