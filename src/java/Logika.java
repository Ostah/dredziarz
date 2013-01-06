
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

public class Logika {

    //area constans
    public static final int[] verySmallArea = {10, 20};
    public static final int[] smallArea = {20, 40};
    public static final int[] mediumArea = {40, 70};
    public static final int[] largeArea = {70, 120};
    public static final int[] veryLargeArea = {120, 300};
    //distances
    public static final int[] closeSchoolDistance = {2, 3};
    public static final int[] mediumSchoolDistance = {5, 10};
    public static final int[] farSchoolDistance = {15, 30};
    
    int lowPrice, highPrice, distanceToSchool, lowArea, highArea, minPrice, maxPrice, minArea, maxArea, maxTownDistance, lowSchoolDistance, highSchoolDistance, minSchoolDistance, maxSchoolDistance;
    String type, flatSize, sNorm, tNorm;
    boolean garage, secured, playground, elevator, selectedDistanceToDowntown, selectedDistanceToSchool, isMoney;
    ArrayList krotki;
    Krotka krotka;
    int distance;


    public Logika(int lowPrice, int highPrice,
            int distanceToSchool,
            int distance,
            String type,
            int lowArea, 
            int highArea,
            boolean garage,
            boolean secured,
            boolean playground,
            boolean elevator,
            boolean isMoney,
            boolean isDistance,
            String sNorm,
            String tNorm
            ) {
        krotki = new ArrayList();
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.distanceToSchool = distanceToSchool;
        this.type = type;
        this.lowArea = lowArea;
        this.highArea = highArea;
        this.garage = garage;
        this.secured = secured;
        this.playground = playground;
        this.elevator = elevator;
        this.isMoney = isMoney;
        this.sNorm = sNorm;
        this.tNorm = tNorm;
        this.distance = distance;

        
        selectedDistanceToDowntown = isDistance;
      
        if (distanceToSchool != -1) {
            selectedDistanceToSchool = true;
            switch (distanceToSchool) {
                case 1:
                        lowSchoolDistance = closeSchoolDistance[0];
                        highSchoolDistance = closeSchoolDistance[1];
                        break;
                case 2:
                        lowSchoolDistance = mediumSchoolDistance[0];
                        highSchoolDistance = mediumSchoolDistance[1];
                        break;
                case 3:
                        lowSchoolDistance = farSchoolDistance[0];
                        highSchoolDistance = farSchoolDistance[1];
                        break;
            }
            
        }
        //calculating min and max prices

        minPrice = (int) (lowPrice - lowPrice * 0.5f);
        maxPrice = (int) (highPrice + highPrice * 0.5f);

        //calculating min and max areas

        minArea = (int) (lowArea - lowArea * 0.2f);
        maxArea = (int) (highArea + highArea * 0.2f);
        
        //calculating min and max distances
        
        minSchoolDistance = (int) (lowSchoolDistance - lowSchoolDistance * 0.5f);
        maxSchoolDistance = (int) (highSchoolDistance + highSchoolDistance * 0.5f);
        
    }

    List<Rekord> start() {

        //calculating max distances to school


        //calculating max distance to town

        Database dbConn = new Database();
        dbConn.createConnection();

        ResultSet rs = dbConn.getSpecificData("select * from apartment");//  where price > " + minPrice + "&& price < " + maxPrice + "&& size > " + minArea + "&& size < " + maxArea);

        int i = 0;
        Rekord[] rekord = new Rekord[30];

        //zapisanie pojedynczego rekordu

        List<Rekord> list = new ArrayList<Rekord>();

        try {
            while (rs.next()) {

                rekord[i] = new Rekord();
                rekord[i].id = rs.getInt("id");
                rekord[i].title = rs.getString("title");
                rekord[i].city = rs.getString("city");
                rekord[i].address = rs.getString("address");
                rekord[i].price = rs.getInt("price");
                rekord[i].area = rs.getInt("size");
                rekord[i].year = rs.getInt("year");
                rekord[i].rooms = rs.getInt("rooms");
                rekord[i].onWhatFloor = rs.getInt("onwhatfloor");
                rekord[i].garage = rs.getBoolean("garage");
                rekord[i].blocksFromCenter = rs.getInt("blocksFromCenter");
                rekord[i].closeToSchool = rs.getInt("closeToSchool");
                rekord[i].elevator = rs.getBoolean("elevator");
                rekord[i].playground = rs.getBoolean("playground");
                rekord[i].securityEstate = rs.getBoolean("secutityEstate");

                rekord[i].compatibility = calculatePercentages(rekord[i]);

                list.add(rekord[i]);

                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        obliczBipolar(list);
         
        List<Rekord> finalList = new ArrayList<Rekord>();
        for(int j=0;j<list.size();j++){
            Rekord r = list.get(j);
            if(r.compatibility!=0 || r.compatibilityBipolar!=0 || (r.compatibilityConditional !=0 && r.compatibilityConditional!=-1)){
                finalList.add(r);
            }
        }
    
        Collections.sort(finalList);
        return finalList;
    }

    int calculatePercentages(Rekord rekord) {

        krotka = new Krotka();
        
        int percent = 0;
        int moneyPercent = 0;
        int areaPercent = 0;
        int distancePercent = 0;

        int schoolDistancePercent = 0;
        int townDistancePercent = 0;

        if (rekord.price > maxPrice || rekord.price < minPrice) {
            moneyPercent = 0;
        } else if (rekord.price >= lowPrice && rekord.price <= highPrice) {
            moneyPercent = 100;
        } else if (rekord.price > highPrice) {
            int x1 = highPrice;
            int y1 = 0;

            int x2 = maxPrice;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            moneyPercent = (int) ((a * rekord.price + b) * 100);
            moneyPercent = Math.abs(100 - moneyPercent);
            rekord.info = "<br /><img src='img/falseSmall.png' /> Mieszkanie jest <b>droższe</b> niż oczekiwane";
        } else if (rekord.price < lowPrice) {
            int x1 = minPrice;
            int y1 = 0;

            int x2 = lowPrice;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            moneyPercent = (int) ((a * rekord.price + b) * 100);
            rekord.info = "<br /><img src='img/trueSmall.png' /> Mieszkanie jest <b>tańsze</b> niż oczekiwane";
        }
        
        if (rekord.area > maxArea || rekord.area < minArea) {
            areaPercent = 0;
        } else if (rekord.area >= lowArea && rekord.area <= highArea) {
            areaPercent = 100;
        } else if (rekord.area > highArea) {
            int x1 = highArea;
            int y1 = 0;

            int x2 = maxArea;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            areaPercent = (int) ((a * rekord.area + b) * 100);
            areaPercent = Math.abs(100 - areaPercent);
            rekord.info += "<br /><img src='img/trueSmall.png' /> Mieszkanie jest <b>większe</b> niż oczekiwano ";
        } else if (rekord.area < lowArea) {
            int x1 = minArea;
            int y1 = 0;

            int x2 = lowArea;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            areaPercent = (int) ((a * rekord.area + b) * 100);
            rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie jest <b>mniejsze</b> niż oczekiwano ";
        }

        if (selectedDistanceToSchool)
        if (rekord.closeToSchool > maxSchoolDistance || rekord.closeToSchool < minSchoolDistance) {
            areaPercent = 0;
        } else if (rekord.closeToSchool >= lowSchoolDistance && rekord.closeToSchool <= highSchoolDistance) {
            areaPercent = 100;
        } else if (rekord.closeToSchool > highSchoolDistance) {
            int x1 = highSchoolDistance;
            int y1 = 0;

            int x2 = maxSchoolDistance;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            schoolDistancePercent = (int) ((a * rekord.closeToSchool + b) * 100);
            schoolDistancePercent = Math.abs(100 - schoolDistancePercent);
            rekord.info += "<br /><img src='img/falseSmall.png' /> Szkoła jest <b>dalej</b> niż oczekiwano ";
        } else if (rekord.closeToSchool < lowSchoolDistance) {
            int x1 = minSchoolDistance;
            int y1 = 0;

            int x2 = lowSchoolDistance;
            int y2 = 1;

            float a = (float) (y2 - y1) / (x2 - x1);
            float b = (float) y1 - a * x1;

            schoolDistancePercent = (int) ((a * rekord.closeToSchool + b) * 100);
            rekord.info += "<br /><img src='img/trueSmall.png' /> Szkoła jest <b>bliżej</b> niż oczekiwano ";
        }
        
        if (selectedDistanceToSchool) {
            percent = (int) ((moneyPercent + areaPercent + schoolDistancePercent) / 3.0);
        } else {
            percent = (int) ((moneyPercent + areaPercent) / 2.0);
        }


        if (selectedDistanceToDowntown) { // w ogole jest zaznaczone
            krotka.distance = distance;
        } else {
            krotka.distance = 0;
        }

        if (garage && rekord.garage != garage) {
            percent -= 10;
            rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie <b>nie ma garażu</b>";
        }
        if (elevator && rekord.elevator != elevator) {
            percent -= 10;
            rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie <b>nie ma windy</b>";
        }
        if (secured && rekord.securityEstate != secured) {
            percent -= 10;
            rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie <b>nie ma ochrony</b>";
        }
        if (playground && rekord.playground != playground) {
            percent -= 10;
            rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie <b>nie ma placu zabaw</b>";
        }

        krotka.id = rekord.id;
        if (isMoney) {
            krotka.cId = (float) (moneyPercent / 100.0);
            krotka.pId = (float) (areaPercent / 100.0);
        } else {
            krotka.pId = (float) (moneyPercent / 100.0);
            krotka.cId = (float) (areaPercent / 100.0);
        }
        
        krotka.fuzzyPercent = percent;
        krotki.add(krotka);
        
        
        return krotka.fuzzyPercent;
    }

    private void obliczBipolar(List<Rekord> list1) {
        Enumeration e = Collections.enumeration(krotki);

        int count =-1;
        while(e.hasMoreElements()){
            count++;
            Krotka krotka = (Krotka) (e.nextElement());
        
        
            Zadrozny.tNorm t_norm=Zadrozny.tNorm.MINIMUM;
            Zadrozny.sNorm s_norm=Zadrozny.sNorm.MAKSIMUM;
        
            if(tNorm.equalsIgnoreCase("iloczyn")) t_norm = Zadrozny.tNorm.ILOCZYN;
            if(tNorm.equalsIgnoreCase("t_lukasiewicz")) t_norm = Zadrozny.tNorm.T_LUKASIEWICZ;       
            if(sNorm.equalsIgnoreCase("suma")) s_norm = Zadrozny.sNorm.SUMA_PROB;
            if(sNorm.equalsIgnoreCase("s_lukasiewicz")) s_norm = Zadrozny.sNorm.S_LUKASIEWICZ;
        
            float wynik = Zadrozny.compute(krotki, krotka,t_norm, s_norm,false);
            int wynikWarunkowy = -1;
            if(selectedDistanceToDowntown){
                 wynikWarunkowy = (int)(Zadrozny.compute(krotki, krotka,t_norm, s_norm,true)*100);  
            }
           
            //System.out.println("Krotka:" + krotka.id + " wartosc:" + wynik);
            float wynik100 = wynik * 100;
            System.out.println("id: " + krotka.id + " bipolar: " + wynik100 + "%");
            krotka.bipolarPercent = (int) (wynik100);
            krotka.conditionalPercent = wynikWarunkowy;
            list1.get(count).compatibilityBipolar=(int) (wynik100);  
            list1.get(count).compatibilityConditional = wynikWarunkowy; 
        }  
    }
}
