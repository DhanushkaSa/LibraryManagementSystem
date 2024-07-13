package service;

import service.custom.BookCategoryServiceImpl;
import service.custom.BookServiceImpl;
import service.custom.MemberServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {

    }

    public static ServiceFactory getInstance() {
        if (serviceFactory == null) {
            serviceFactory = new ServiceFactory();
        }

        return serviceFactory;
    }

    public enum ServiceType {
        MEMBERS, BOOK_CATEGORY, BOOKS;
    }

    public SuperService getService(ServiceType type) {
        switch (type) {
            case MEMBERS:
                return new MemberServiceImpl();
            case BOOK_CATEGORY:
                return new BookCategoryServiceImpl();
            case BOOKS:
                return new BookServiceImpl();

            default:
                return null;
        }
    }
}
