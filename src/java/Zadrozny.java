
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
		MINIMUM, ILOCZYN, T_LUKASIEWICZ
    }
    
    public enum sNorm {
		MAKSIMUM, SUMA_PROB, S_LUKASIEWICZ
    }
    
    static float compute(ArrayList<Krotka> list, Krotka krotka, tNorm t, sNorm s, Boolean circumstancial)
    {
        
        float internalMax = 0.0f;
        float outerMax;
       
        for (int i = 0; i < list.size(); i++) {
            //znajduje największą wartość z min(C(s),P(s))    
            float Cs = list.get(i).cId;
            if(circumstancial) Cs = t_norm(krotka.isSameDistance(list.get(i)),list.get(i).cId,t);
            float minimum = t_norm(Cs, list.get(i).pId,t);
            if (internalMax < minimum) internalMax = minimum;
        }

        outerMax = s_norm(1-internalMax, krotka.pId,s);
        return t_norm(krotka.cId, outerMax,t);
    }
    
    private static float t_norm(float a, float b, tNorm t)
    {
        switch(t){
            case MINIMUM:
                return Math.min(a, b);
            case ILOCZYN:
                return a*b;
            case T_LUKASIEWICZ:
                return Math.max(0, a+b-1);
        }
        return 0.0f;
    }
    private static float s_norm(float a, float b, sNorm s)
    {
        switch(s){
            case MAKSIMUM:
                return Math.max(a, b);
            case SUMA_PROB:
                return a+b-a*b;
            case S_LUKASIEWICZ:
                return Math.min(1, a+b);
        }
        return 0.0f;
    }
}
