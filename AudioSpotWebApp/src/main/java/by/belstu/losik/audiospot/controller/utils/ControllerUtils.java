package by.belstu.losik.audiospot.controller.utils;

import by.belstu.losik.audiospot.pagination.PaginationDetails;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

public class ControllerUtils {
    private static final String ENTITIES_ATTR = "entities";
    private static final String PAGINATION_DETAILS_ATTR = "paginationDetails";

    private ControllerUtils() {
    }


    public static <T> ModelAndView buildEntityPageModelAndView(List<T> entities, String targetView,
                                                               PaginationDetails paginationDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PAGINATION_DETAILS_ATTR, paginationDetails);
        modelAndView.addObject(ENTITIES_ATTR, entities);
        modelAndView.setViewName(targetView);
        return modelAndView;
    }
}
