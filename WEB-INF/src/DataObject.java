public class DataObject {

    public String APPLICATION, DATE1,TEST_CASE_NAME,TEST_STATUS;
    public int  NUMBER_OF_ERRORS;
    
    public void setDATE1(String DATE1) { this.DATE1=DATE1;}
    public void setAPPLICATION(String APPLICATION) { this.APPLICATION=APPLICATION;}
    public void setTESTCASE_NAME(String TEST_CASE_NAME) {this.TEST_CASE_NAME=TEST_CASE_NAME;}
    public void setTEST_STATUS(String TEST_STATUS) {this.TEST_STATUS=TEST_STATUS;}
    public void setNUMBER_OF_ERRORS(int NUMBER_OF_ERRORS) {this.NUMBER_OF_ERRORS=NUMBER_OF_ERRORS;}
    
    public String getDATE1() {return DATE1;}
    public String getAPPLICATION() {return APPLICATION;}
    public String getTESTCASE_NAME() {return TEST_CASE_NAME;}
    public String getTEST_STATUS() {return TEST_STATUS;}
    public int getNUMBER_OF_ERRORS() {return NUMBER_OF_ERRORS;}
}
