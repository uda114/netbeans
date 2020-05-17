package models;

public class Control {

    private int lNum,lineWeight,noOfControl,cspps;
    private String line;

    public int getCspps() {
            return cspps;
    }
    public void setCspps(int cspps) {
            this.cspps = cspps;
    }
    public String getLine() {
            return line;
    }
    public void setLine(String line) {
            this.line = line;
    }

    public int getlNum() {
        return lNum;
    }

    public void setlNum(int lNum) {
        this.lNum = lNum;
    }

    public int getLineWeight() {
        return lineWeight;
    }

    public void setLineWeight(int lineWeight) {
        this.lineWeight = lineWeight;
    }

    public int getNoOfControl() {
        return noOfControl;
    }

    public void setNoOfControl(int noOfControl) {
        this.noOfControl = noOfControl;
    }
	
}
