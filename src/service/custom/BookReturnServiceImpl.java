package service.custom;

import java.sql.Connection;
import java.util.ArrayList;

import connection.DBConnection;
import dao.DaoFactory;
import dao.custom.BookDao;
import dao.custom.BookReturnDao;
import dao.custom.BorrowDao;
import dto.BookDto;
import dto.BookReturnDto;
import entity.BookEntity;
import entity.BookReturnEntity;

public class BookReturnServiceImpl implements BookReturnService {

    private BookReturnDao bookReturnDao = (BookReturnDao) DaoFactory.getInstance()
            .getDaoType(DaoFactory.DaoType.BOOK_RETURN);

    private BorrowDao borrowDao = (BorrowDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.BORROW);
    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.BOOKS);

    @Override
    public String save(BookReturnDto dto, String BorrowId) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            String borrowDeleteResponse = borrowDao.delete(BorrowId);
            if (borrowDeleteResponse.equals("Delete Successfully !!")) {
                boolean isSave = true;
                BookReturnEntity entity = new BookReturnEntity(dto.getReturn_Id(),
                        dto.getReturn_Date(), dto.getFine());

                String ReturnBookResponse = bookReturnDao.save(entity);
                if (!ReturnBookResponse.equals("Save Successfully !!!")) {
                    isSave = false;
                }
                if (isSave) {
                    boolean isUpdateBook = true;
                    ArrayList<BookDto> bookArray = dto.getBookDtos();
                    BookEntity bookEntity = new BookEntity();

                    for (BookDto data : bookArray) {
                        BookEntity entityBook = bookDao.get(data.getBook_Id());

                        bookEntity.setBook_Id(data.getBook_Id());
                        bookEntity.setBook_Name(data.getBook_Name());
                        bookEntity.setLanguage(data.getLanguage());
                        bookEntity.setAuthor(data.getAuthor());
                        bookEntity.setBook_Count(entityBook.getBook_Count() + 1);
                        bookEntity.setCategory_Id(data.getCategory_Id());
                    }
                    String bookUpdateResponse = bookDao.update(bookEntity);
                    if (!bookUpdateResponse.equals("Update Successfully !!")) {
                        isUpdateBook = false;
                    }

                    if (isUpdateBook) {
                        connection.commit();
                        return "Successfully !!!";
                    } else {
                        connection.rollback();
                        return "Book update fail !!!";
                    }
                } else {
                    connection.rollback();
                    return "Save fail !!!";
                }
            } else {
                connection.rollback();
                return "Borrow delete fail !!!";
            }
        } catch (Exception e) {
            connection.rollback();
            return "Server error !!!";
        } finally {
            connection.setAutoCommit(true);
        }
    }

    @Override
    public ArrayList<BookReturnDto> getAll() throws Exception {
        ArrayList<BookReturnEntity> arrayEntity = bookReturnDao.getAll();
        ArrayList<BookReturnDto> arrayDto = new ArrayList<>();
        for (BookReturnEntity data : arrayEntity) {
            BookReturnDto dto = new BookReturnDto(data.getReturn_Id(), data.getReturn_Date(), data.getFine(), null,
                    null, null);
            arrayDto.add(dto);
        }
        return arrayDto;
    }

}
