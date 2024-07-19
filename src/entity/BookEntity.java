package entity;

public class BookEntity {

    private String Book_Id;
    private String Book_Name;
    private String Language;
    private String Author;
    private Integer Book_Count;
    private String Category_Id;

    public BookEntity() {

    }

    public BookEntity(String book_Id, String book_Name, String language, String author, Integer book_Count,
            String category_Id) {
        Book_Id = book_Id;
        Book_Name = book_Name;
        Language = language;
        Author = author;
        Book_Count = book_Count;
        Category_Id = category_Id;
    }

    public String getBook_Id() {
        return Book_Id;
    }

    public void setBook_Id(String book_Id) {
        Book_Id = book_Id;
    }

    public String getBook_Name() {
        return Book_Name;
    }

    public void setBook_Name(String book_Name) {
        Book_Name = book_Name;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public Integer getBook_Count() {
        return Book_Count;
    }

    public void setBook_Count(Integer book_Count) {
        Book_Count = book_Count;
    }
 
    public String getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(String category_Id) {
        Category_Id = category_Id;
    }

    @Override
    public String toString() {
        return "BookEntity [Book_Id=" + Book_Id + ", Book_Name=" + Book_Name + ", Language=" + Language + ", Author="
                + Author + ", Book_Count=" + Book_Count + ", Category_Id=" + Category_Id + "]";
    }

}
