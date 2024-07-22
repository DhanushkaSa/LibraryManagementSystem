package entity;

public class BookReturnEntity {

    private String Return_Id;
    private String Return_Date;
    private Double Fine;
    

    public BookReturnEntity() {

    }


    public BookReturnEntity(String return_Id, String return_Date, Double fine) {
        Return_Id = return_Id;
        Return_Date = return_Date;
        Fine = fine;
    }


    public String getReturn_Id() {
        return Return_Id;
    }


    public void setReturn_Id(String return_Id) {
        Return_Id = return_Id;
    }


    public String getReturn_Date() {
        return Return_Date;
    }


    public void setReturn_Date(String return_Date) {
        Return_Date = return_Date;
    }


    public Double getFine() {
        return Fine;
    }


    public void setFine(Double fine) {
        Fine = fine;
    }


    @Override
    public String toString() {
        return "BookReturnEntity [Return_Id=" + Return_Id + ", Return_Date=" + Return_Date + ", Fine=" + Fine + "]";
    }

    

}
