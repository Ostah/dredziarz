
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
    
    static float compute(ArrayList<Krotka> list, int elementId, tNorm t, sNorm s)
    {
        
        float internalMax = 0.0f;
        float outerMax = 0.0f;
        int position = -1;
       
        for (int i = 0; i < list.size(); i++) {

            //znajduje krotkę dla której mam obliczyć stopień przynależności
            if(list.get(i).id == elementId) position = i;
            
            //znajduje największą wartość z min(C(s),P(s))
            float minimum = Math.min(list.get(i).cId, list.get(i).pId);
            if (internalMax < minimum) internalMax = minimum;
  
        }
       
        //zabezpieczenie
        assert position!= -1 : "Nie znaleziono elementu o tym id";

        outerMax = Math.max(1-internalMax, list.get(position).pId);
        return Math.min(list.get(position).cId, outerMax);
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
