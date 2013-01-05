
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
		NORM1, NORM2
    }
    
    public enum sNorm {
		NORM1, NORM2
    }
    
    static float compute(ArrayList<Krotka> list, Krotka krotka, tNorm t, sNorm s)
    {
        
        float internalMax = 0.0f;
        float outerMax = 0.0f;
        int position = -1;
       
        for (int i = 0; i < list.size(); i++) {
            
            //znajduje największą wartość z min(C(s),P(s))
            float minimum = Math.min(krotka.cId, krotka.pId);
            if (internalMax < minimum) internalMax = minimum;
  
        }
       
        //zabezpieczenie
        assert position!= -1 : "Nie znaleziono elementu o tym id";

        outerMax = Math.max(1-internalMax, krotka.pId);
        return Math.min(krotka.cId, outerMax);
    }
}
