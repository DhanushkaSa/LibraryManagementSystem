package dto;

import java.util.ArrayList;

public class BookReturnDto {

    private String Return_Id;
    private String Return_Date;
    private Double Fine;
    private String Borrow_Id;
    private ArrayList<BorrowDto> borrowDtos;
    private ArrayList<MemberDto> memberDtos;
    private ArrayList<BookDto> bookDtos;

    public BookReturnDto() {

    }

    public BookReturnDto(String return_Id, String return_Date, Double fine, String borrow_Id,
            ArrayList<BorrowDto> borrowDtos, ArrayList<MemberDto> memberDtos, ArrayList<BookDto> bookDtos) {
        Return_Id = return_Id;
        Return_Date = return_Date;
        Fine = fine;
        Borrow_Id = borrow_Id;
        this.borrowDtos = borrowDtos;
        this.memberDtos = memberDtos;
        this.bookDtos = bookDtos;
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

    public String getBorrow_Id() {
        return Borrow_Id;
    }

    public void setBorrow_Id(String borrow_Id) {
        Borrow_Id = borrow_Id;
    }

    public ArrayList<BorrowDto> getBorrowDtos() {
        return borrowDtos;
    }

    public void setBorrowDtos(ArrayList<BorrowDto> borrowDtos) {
        this.borrowDtos = borrowDtos;
    }

    public ArrayList<MemberDto> getMemberDtos() {
        return memberDtos;
    }

    public void setMemberDtos(ArrayList<MemberDto> memberDtos) {
        this.memberDtos = memberDtos;
    }

    public ArrayList<BookDto> getBookDtos() {
        return bookDtos;
    }

    public void setBookDtos(ArrayList<BookDto> bookDtos) {
        this.bookDtos = bookDtos;
    }

    @Override
    public String toString() {
        return "BookReturnDto [Return_Id=" + Return_Id + ", Return_Date=" + Return_Date + ", Fine=" + Fine
                + ", Borrow_Id=" + Borrow_Id + ", borrowDtos=" + borrowDtos + ", memberDtos=" + memberDtos
                + ", bookDtos=" + bookDtos + "]";
    }

    
}
