package entity;

public class BookCategoryEntity {
    private String Category_Id;
    private String Category_Name;

    public BookCategoryEntity() {

    }

    public BookCategoryEntity(String category_Id, String category_Name) {
        Category_Id = category_Id;
        Category_Name = category_Name;
    }

    public String getCategory_Id() {
        return Category_Id;
    }

    public void setCategory_Id(String category_Id) {
        Category_Id = category_Id;
    }

    public String getCategory_Name() {
        return Category_Name;
    }

    public void setCategory_Name(String category_Name) {
        Category_Name = category_Name;
    }

    @Override
    public String toString() {
        return "BookCategoryEntity [Category_Id=" + Category_Id + ", Category_Name=" + Category_Name + "]";
    }

}
