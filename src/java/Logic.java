
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class Logic {

    //distances
    public static final int[] closeSchoolDistance = {0, 500};
    public static final int[] mediumSchoolDistance = {500, 5000};
    public static final int[] farSchoolDistance = {5000, 20000};
    
    int lowPrice, highPrice, distanceToSchool, lowArea, highArea, minPrice, maxPrice, minArea, maxArea, maxTownDistance, lowSchoolDistance, highSchoolDistance, minSchoolDistance, maxSchoolDistance,distanceToDowntown;
    String  flatSize, sNorm, tNorm;
    boolean garage, secured, playground, elevator, isConditionalQuery, selectedDistanceToSchool, isMoney;
    ArrayList mTuplesList;
    int [] distanceValues;
    Tuple mTuple;

    public Logic(int [] distanceValues, int lowPrice, int highPrice,
            int distanceToSchool,
            int distanceToDowntown,
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
        mTuplesList = new ArrayList();
        this.lowPrice = lowPrice;
        this.highPrice = highPrice;
        this.distanceToSchool = distanceToSchool;
        this.lowArea = lowArea;
        this.highArea = highArea;
        this.garage = garage;
        this.secured = secured;
        this.playground = playground;
        this.elevator = elevator;
        this.isMoney = isMoney;
        this.sNorm = sNorm;
        this.tNorm = tNorm;
        this.distanceToDowntown = distanceToDowntown;
        this.isConditionalQuery = isDistance;
        this.distanceValues = distanceValues;
      
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
                
        //obliczanie minimalnej i maksymalnej ceny
        minPrice = (int) (lowPrice - lowPrice * 0.5f);
        maxPrice = (int) (highPrice + highPrice * 0.5f);

        //obliczanie minimalnej i maksymalnej powierzchni
        minArea = (int) (lowArea - lowArea * 0.2f);
        maxArea = (int) (highArea + highArea * 0.2f);
        
        //obliczanie minimalnej i maksymalnej odległosci
        minSchoolDistance = (int) (lowSchoolDistance - lowSchoolDistance * 0.5f);
        maxSchoolDistance = (int) (highSchoolDistance + highSchoolDistance * 0.5f);
    }

    List<Record> start(float[] arrayCheat) {

        //połączenie z bazą danych
        Database dbConn = new Database();
        dbConn.createConnection();
        ResultSet rs = dbConn.getSpecificData("select * from apartment");//  where price > " + minPrice + "&& price < " + maxPrice + "&& size > " + minArea + "&& size < " + maxArea);
       
        Record rekord;
        List<Record> list = new ArrayList<Record>();
        
        //przejście po wszystkich rekordach otrzymanych z bazy
        int i=-1;
        try {
            while (rs.next()) {
                i++;
                rekord = new Record();
                rekord.id = rs.getInt("id");
                rekord.title = rs.getString("title");
                if(rekord.title.equalsIgnoreCase("Bungalow")){
                    System.out.println("found: "+String.valueOf(i));
                }
                rekord.city = rs.getString("city");
                rekord.address = rs.getString("address");
                rekord.price = rs.getInt("price");
                rekord.area = rs.getInt("size");
                rekord.year = rs.getInt("year");
                rekord.onWhatFloor = rs.getInt("onwhatfloor");
                rekord.garage = rs.getBoolean("garage");
                rekord.blocksFromCenter = rs.getInt("blocksFromCenter");
                rekord.closeToSchool = rs.getInt("closeToSchool");
                rekord.elevator = rs.getBoolean("elevator");
                rekord.playground = rs.getBoolean("playground");
                rekord.securityEstate = rs.getBoolean("secutityEstate");
                rekord.compatibility = calculatePercentages(rekord);
                list.add(rekord);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        computeBipolar(list);
        
        List<Record> finalList = new ArrayList<Record>();
        
        float compabilitySum = 0.0f;
        for(int j=0;j<list.size();j++){
            Record r = list.get(j);
            compabilitySum += r.compatibilityBipolar;
            if(r.compatibility!=0 || r.compatibilityBipolar!=0 || (r.compatibilityConditional !=0 && r.compatibilityConditional!=-1)){
                finalList.add(r);
            }
        }
        arrayCheat[1]= new Float(compabilitySum/(float)list.size());
        
        Collections.sort(finalList);
        return finalList;
    }
    
    int computeDistance(int distanceInMeters) {
        if (distanceInMeters >= distanceValues[0] && distanceInMeters < distanceValues[5]) {
            return 1;
        } else if (distanceInMeters >= distanceValues[1] && distanceInMeters < distanceValues[6]) {
            return 2;
        } else if (distanceInMeters >= distanceValues[2] && distanceInMeters < distanceValues[7]) {
            return 3;
        } else if (distanceInMeters >= distanceValues[3] && distanceInMeters < distanceValues[8]) {
            return 4;
        } else {
            return 5;
        }
    }

    // logika zapytań rozmytych (nie bipolarne)
    int calculatePercentages(Record rekord) {
        
        mTuple = new Tuple();
        int percent = 0;
        int moneyPercent = 0;
        int areaPercent = 0;
        int schoolDistancePercent = 0;

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

        if (selectedDistanceToSchool) {
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
        
        }
        if (selectedDistanceToSchool) {
            percent = (int) ((moneyPercent + areaPercent + schoolDistancePercent) / 3.0);
        } else {
            percent = (int) ((moneyPercent + areaPercent) / 2.0);
        }


        if (isConditionalQuery) { // w ogole jest zaznaczone
            mTuple.distance = computeDistance(rekord.blocksFromCenter); //1 - 5
        } else {
            mTuple.distance = 0;
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

        mTuple.id = rekord.id;
        if (isMoney) {
            mTuple.cId = (float) (moneyPercent / 100.0);
            mTuple.pId = (float) (areaPercent / 100.0);
        } else {
            mTuple.pId = (float) (moneyPercent / 100.0);
            mTuple.cId = (float) (areaPercent / 100.0);
        }
        
        mTuple.fuzzyPercent = percent;
        mTuplesList.add(mTuple);
        
        
        return mTuple.fuzzyPercent;
    }

    //logika bipolarna
    private void computeBipolar(List<Record> list1) {
        Enumeration e = Collections.enumeration(mTuplesList);

        int count =-1;
        while(e.hasMoreElements()){
            count++;
            if(count == 11){
                System.out.print("catch");
            }
            Tuple localTuple = (Tuple) (e.nextElement());
            System.out.println("Tuple: "+ localTuple.id+" "+localTuple.distance);
            Zadrozny.tNorm t_norm=Zadrozny.tNorm.MINIMUM;
            Zadrozny.sNorm s_norm=Zadrozny.sNorm.MAXIMUM;
        
            if(tNorm.equalsIgnoreCase("iloczyn")) t_norm = Zadrozny.tNorm.PRODUCT;
            if(tNorm.equalsIgnoreCase("t_lukasiewicz")) t_norm = Zadrozny.tNorm.T_LUKASIEWICZ;       
            if(sNorm.equalsIgnoreCase("suma")) s_norm = Zadrozny.sNorm.PROB_SUM;
            if(sNorm.equalsIgnoreCase("s_lukasiewicz")) s_norm = Zadrozny.sNorm.S_LUKASIEWICZ;
        
            float result = Zadrozny.compute(mTuplesList, localTuple,t_norm, s_norm,false);
            int resultConditional = -1;
           
            if(isConditionalQuery){
                 resultConditional = (int)(Zadrozny.compute(mTuplesList, localTuple,t_norm, s_norm,true)*100);  
            }
          
            float result100 = result * 100;
            System.out.println("id: " + localTuple.id + " bipolar: " + result100 + "%");
            localTuple.bipolarPercent = (int) (result100);
            localTuple.conditionalPercent = resultConditional;
            list1.get(count).compatibilityBipolar=(int) (result100);  
            list1.get(count).compatibilityConditional = resultConditional; 
        }  
    }
}
