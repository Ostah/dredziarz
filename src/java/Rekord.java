public class Rekord implements Comparable<Rekord>{
	public int id;
	public String title;
	public String city;
	public String address;
	public int price;
	public int area;
	public int year;
	public int rooms;
	public int onWhatFloor;
	public boolean garage;
	public int blocksFromCenter;
	public int closeToSchool;
	public boolean elevator;
	public boolean playground;
	public boolean securityEstate;
	public int compatibility;
        public int compatibilityBipolar;
        public int compatibilityConditional;
	public String info = "";
	
	boolean calculateCompatibility() {
		return true;
	}

	public int compareTo(Rekord compareObject)
    {
        if(compatibilityConditional==-1)
        {
            if (compatibilityBipolar < compareObject.compatibilityBipolar)
                return 1;
            else if (compatibilityBipolar == compareObject.compatibilityBipolar)
                return 0;
            else
                return -1; 
        }
        else{
            if (compatibilityConditional < compareObject.compatibilityConditional)
                return 1;
            else if (compatibilityConditional == compareObject.compatibilityConditional)
                return 0;
            else
                return -1; 
        }
       
    }
        public String checkTrue(boolean param) {
             if(param) {
                 return "<img src='img/true.png' />";
             } else {
                 return "<img src='img/false.png' />";
             }
            
        }

    @Override
    public String toString() {
        String conditional = compatibilityConditional==-1 ? "" : "<td class='important'>" + this.compatibilityConditional + " %</td>";
        return "<tr><td class='important'> " + this.title + "</td><td class='important'>" + this.compatibility + " %</td>" +
                "<td class='important'>" + this.compatibilityBipolar + " %</td>"+conditional+
                 "<td>" + this.address + "</td><td> " + this.area + " m<sup>2</sup> </td><td> " +
                this.price + " zł</td><td>" + this.blocksFromCenter + " metry/ów</td><td>" +
                this.closeToSchool + " metry/ów</td><td>" + checkTrue(elevator) + " </td><td> " +
                checkTrue(playground) + "</td><td>" + checkTrue(securityEstate) + "</td><td class='infos'>" + this.info +"</td>";
    }
        
}


