package models;

public class size {

    private int Keyword,Identifier,Operator,Numerical,String_Count,lNum;
    private String line;

    public int getKeyword() {
            return Keyword;
    }
    public void setKeyword(int keyword) {
            Keyword = keyword;
    }
    public int getIdentifier() {
            return Identifier;
    }
    public void setIdentifier(int identifier) {
            Identifier = identifier;
    }
    public int getOperator() {
            return Operator;
    }
    public void setOperator(int operator) {
            Operator = operator;
    }
    public int getNumerical() {
            return Numerical;
    }
    public void setNumerical(int numerical) {
            Numerical = numerical;
    }
    public String getLine() {
            return line;
    }
    public void setLine(String line) {
            this.line = line;
    }

    public int getString_Count() {
        return String_Count;
    }

    public void setString_Count(int String_Count) {
        this.String_Count = String_Count;
    }

    public int getlNum() {
        return lNum;
    }

    public void setlNum(int lNum) {
        this.lNum = lNum;
    }
	
}
