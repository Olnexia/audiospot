package by.belstu.losik.audiospot.controller;

import by.belstu.losik.audiospot.controller.utils.PaginationUtils;
import by.belstu.losik.audiospot.dto.TrackSearchDto;
import by.belstu.losik.audiospot.entity.AudioTrack;
import by.belstu.losik.audiospot.entity.Genre;
import by.belstu.losik.audiospot.pagination.PaginationDetails;
import by.belstu.losik.audiospot.service.AudioService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/tracks")
@ComponentScan("by.belstu.losik.audiospot")
public class TracksController {
    private static final String PAGINATION_DETAILS_ATTR = "paginationDetails";
    private static final String TRACKS_ATTR = "tracks";
    private static final String SEARCH_DTO_ATTR = "searchDto";
    private static final String TYPES_ATTR = "trackTypes";
    private static final String TARGET_VIEW = "tracks";
    private AudioService audioService;

    public TracksController(AudioService audioService) {
        this.audioService = audioService;
    }

    @GetMapping("")
    public ModelAndView showTracksFirstPage() {
        return showPaginatedTracksPage(new TrackSearchDto(), 1, PaginationUtils.DEFAULT_PAGE_SIZE);
    }

    @GetMapping(value = "", params = {PaginationUtils.PAGE_ATTR, PaginationUtils.PAGE_SIZE_ATTR})
    public ModelAndView showPaginatedTracksPage(@Valid @ModelAttribute("trackSearchDto") TrackSearchDto searchDto,
                                               @RequestParam(PaginationUtils.PAGE_ATTR) int page,
                                               @RequestParam(PaginationUtils.PAGE_SIZE_ATTR) int pageSize) {
        int size = audioService.getTracksCount();
        int lastPage = PaginationUtils.getLastPage(size, pageSize);
        PaginationDetails paginationDetails = new PaginationDetails(page, lastPage, pageSize);

        List<AudioTrack> tours = audioService.findTracksPage(page, pageSize);

        return buildTracksPageModelAndView(tours, searchDto, paginationDetails);
    }

    private ModelAndView buildTracksPageModelAndView(List<AudioTrack> tracks, TrackSearchDto searchDto,
                                                    PaginationDetails paginationDetails) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PAGINATION_DETAILS_ATTR, paginationDetails);
        modelAndView.addObject(TRACKS_ATTR, tracks);
        modelAndView.addObject(SEARCH_DTO_ATTR, searchDto);
        modelAndView.addObject(TYPES_ATTR, Genre.values());
        modelAndView.setViewName(TARGET_VIEW);
        return modelAndView;
    }
}
