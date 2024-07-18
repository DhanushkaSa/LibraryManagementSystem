package entity;

public class BorrowEntity {
    
    private String Borrow_Id;
    private String Member_Id;
    private String Book_Id;
    private String DueDate;
    private String Borrow_Date;
    

    public BorrowEntity() {
       
    }


    public BorrowEntity(String borrow_Id, String member_Id, String book_Id, String dueDate, String borrow_Date) {
        Borrow_Id = borrow_Id;
        Member_Id = member_Id;
        Book_Id = book_Id;
        DueDate = dueDate;
        Borrow_Date = borrow_Date;
    }


    public String getBorrow_Id() {
        return Borrow_Id;
    }


    public void setBorrow_Id(String borrow_Id) {
        Borrow_Id = borrow_Id;
    }


    public String getMember_Id() {
        return Member_Id;
    }


    public void setMember_Id(String member_Id) {
        Member_Id = member_Id;
    }


    public String getBook_Id() {
        return Book_Id;
    }


    public void setBook_Id(String book_Id) {
        Book_Id = book_Id;
    }


    public String getDueDate() {
        return DueDate;
    }


    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }


    public String getBorrow_Date() {
        return Borrow_Date;
    }


    public void setBorrow_Date(String borrow_Date) {
        Borrow_Date = borrow_Date;
    }


    @Override
    public String toString() {
        return "BorrowEntity [Borrow_Id=" + Borrow_Id + ", Member_Id=" + Member_Id + ", Book_Id=" + Book_Id
                + ", DueDate=" + DueDate + ", Borrow_Date=" + Borrow_Date + "]";
    }


    
    

    
}
