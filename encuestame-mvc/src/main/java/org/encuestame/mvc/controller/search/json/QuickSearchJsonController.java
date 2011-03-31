package org.encuestame.mvc.controller.search.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.encuestame.business.search.GlobalSearchItem;
import org.encuestame.mvc.controller.AbstractJsonController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class QuickSearchJsonController extends AbstractJsonController {

    /**
     * Log.
     */
    private Logger log = Logger.getLogger(this.getClass());

    /**
     * Default limit results.
     */
    private final Integer LIMIT_RESULTS = 10;

    /**
     * Quick suggestion search service.
     *
     * @param keyword
     * @param type
     * @param request
     * @param response
     * @return
     * @throws JsonGenerationException
     * @throws JsonMappingException
     * @throws IOException
     */
    @RequestMapping(value = "api/search/quick-suggest.json", method = RequestMethod.GET)
    public ModelMap quickSuggestionSearchService(
            @RequestParam(value = "keyword", required = true) String keyword,
            HttpServletRequest request,
            HttpServletResponse response) throws JsonGenerationException,
            JsonMappingException, IOException {
        try {
            final List<GlobalSearchItem> results = new ArrayList<GlobalSearchItem>();
            keyword = filterValue(keyword);
            if (!keyword.isEmpty()) {
                results.addAll(getSearchService()
                        .quickSearch(keyword, "English", 0, LIMIT_RESULTS));
            } else {
                log.debug("keyword is empty");
            }
            log.debug("GlobalSearchItem results " + results.size());
            setItemReadStoreResponse("itemSearchTitle", "id", results);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
            setError(e.getMessage(), response);
        }
        return returnData();
    }
}
