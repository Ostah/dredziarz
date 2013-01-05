
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
       
        assert position!= -1 : "Nie znaleziono elementu o tym id";

        outerMax = Math.max(1-internalMax, list.get(elementId).pId);
        return Math.min(list.get(elementId).cId, outerMax);
    }
}
