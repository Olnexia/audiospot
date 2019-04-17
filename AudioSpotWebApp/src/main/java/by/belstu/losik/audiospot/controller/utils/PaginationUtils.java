package by.belstu.losik.audiospot.controller.utils;

public class PaginationUtils {
    public static final int DEFAULT_PAGE_SIZE = 10;
    public static final String PAGE_ATTR = "page";
    public static final String PAGE_SIZE_ATTR = "pageSize";

    private PaginationUtils() {
    }

    public static int getLastPage(int size, int perPage) {
        return (int) Math.ceil((double) size / perPage);
    }
}
