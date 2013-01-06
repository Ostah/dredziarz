
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
    public static final int closeDistance = 2;
    public static final int mediumDistance = 5;
    public static final int farDistance = 15;
    int lowPrice, highPrice, distanceToSchool, distanceMin, distanceMax, lowArea, highArea, minPrice, maxPrice, minArea, maxArea, maxSchoolDistance, maxTownDistance;
    String type, flatSize, sNorm, tNorm;
    boolean garage, secured, playground, elevator, selectedDistanceToDowntown, selectedDistanceToSchool, isMoney;
    ArrayList krotki;
    Krotka krotka;


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
        this.distanceMin = distanceMin;
        this.distanceMax = distanceMax;
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
/*
        System.out.println("Low cena: " + lowPrice);
        System.out.println("High cena: " + highPrice);
*/      
        selectedDistanceToDowntown = isDistance;
      

        if (distanceToSchool != -1) {
            selectedDistanceToSchool = true;
        }
        //calculating min and max prices

        minPrice = (int) (lowPrice - lowPrice * 0.5f);
        maxPrice = (int) (highPrice + highPrice * 0.5f);

        //calculating min and max areas

        minArea = (int) (lowArea - lowArea * 0.2f);
        maxArea = (int) (highArea + highArea * 0.2f);

    }

    List<Rekord> start() {


        //calculating max distances to school

        maxSchoolDistance = (int) (distanceToSchool + 5);

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

        Collections.sort(list);
    
        return list;
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
        
        
        //System.out.println("moneyPercent: " + moneyPercent / 100.0);
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

        percent = (moneyPercent + areaPercent) / 2;

        if (selectedDistanceToSchool) { // w ogole jest zaznaczone
            if (rekord.closeToSchool <= distanceToSchool) ;//	5 <= 3			
            else if (rekord.closeToSchool <= maxSchoolDistance) {
                int x1 = maxSchoolDistance;
                int y1 = 0;

                int x2 = distanceToSchool;
                int y2 = 1;

                float a = (float) (y2 - y1) / (x2 - x1);
                float b = (float) y1 - a * x1;

                schoolDistancePercent = (int) ((a * rekord.closeToSchool + b) * 100);

                percent = (percent + schoolDistancePercent) / 2;
                rekord.info += "<br /><img src='img/falseSmall.png' /> Mieszkanie jest <b>daleko od szkoły</b>";
            }
        }

        if (selectedDistanceToDowntown) { // w ogole jest zaznaczone
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
        
            float wynik = Zadrozny.compute(krotki, krotka,t_norm, s_norm);
            //System.out.println("Krotka:" + krotka.id + " wartosc:" + wynik);
            float wynik100 = wynik * 100;
            System.out.println("id: " + krotka.id + " bipolar: " + wynik100 + "%");
            krotka.bipolarPercent = (int) (wynik100);
            list1.get(count).compatibilityBipolar=(int) (wynik100);   
        }  
    }
}
