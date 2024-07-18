// 

package service.custom;

import java.sql.Connection;
import java.util.ArrayList;

import connection.DBConnection;
import dao.DaoFactory;
import dao.custom.BookDao;
import dao.custom.BorrowDao;
import dto.BookDto;
import dto.BorrowDto;
import entity.BookEntity;
import entity.BorrowEntity;

public class BorrowServiceImpl implements BorrowService {

    private BookDao bookDao = (BookDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.BOOKS);
    private BorrowDao borrowDao = (BorrowDao) DaoFactory.getInstance().getDaoType(DaoFactory.DaoType.BORROW);

    @Override
    public String placeBorrow(BorrowDto borrowDto) throws Exception {

        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            BorrowEntity borrowEntity = new BorrowEntity(borrowDto.getBorrow_Id(), borrowDto.getMember_Id(),
                    borrowDto.getBook_Id(), borrowDto.getDueDate(), borrowDto.getBorrow_Date());
            String response = borrowDao.save(borrowEntity);
            if (response.equals("Successfully Save !!")) {
                boolean isBookUpdate = true;

                ArrayList<BookDto> bookArray = borrowDto.getBookDtos();

                for (BookDto data : bookArray) {

                    BookEntity bookEntity = bookDao.get(data.getBook_Id());

                    if (bookEntity.getBook_Count() > 0) {
                        bookEntity.setBook_Count(bookEntity.getBook_Count() - 1);
                        String updateResponse = bookDao.update(bookEntity);

                        if (!updateResponse.equals("Update Successfully !!")) {
                            isBookUpdate = false;
                            break;
                        }

                    } else {
                        isBookUpdate = false;
                        break;
                    }

                }
                if (isBookUpdate) {
                    connection.commit();
                    return "Success !!";
                } else {
                    connection.rollback();
                    return "Book update Fail !!";
                }

            } else {
                connection.rollback();
                return "Borrow save error !!";
            }
        } catch (Exception e) {
            connection.rollback();
            e.printStackTrace();
            return "Server Error !!";
        } finally {
            connection.setAutoCommit(true);
        }

    }

    @Override
    public ArrayList<BorrowDto> getAll() throws Exception {
        ArrayList<BorrowEntity> borrowEntity = borrowDao.getAll();
        ArrayList<BorrowDto> borrowArray = new ArrayList<>();
        for (BorrowEntity data : borrowEntity) {
            BorrowDto borrow = new BorrowDto(data.getBorrow_Id(), data.getMember_Id(), data.getBook_Id(),
                    data.getDueDate(), data.getBorrow_Date(), null);
            borrowArray.add(borrow);
        }
        return borrowArray;
    }

}
