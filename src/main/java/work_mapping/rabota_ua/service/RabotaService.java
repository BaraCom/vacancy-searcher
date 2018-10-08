package work_mapping.rabota_ua.service;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import work_mapping.core.IWorkSearcher;
import work_mapping.core.Vacancy;
import work_mapping.core.service.PageService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static org.jsoup.Jsoup.connect;

@Slf4j
public class RabotaService extends IWorkSearcher {
    private static final String mainUrl = "https://rabota.ua/jobsearch/vacancy_list?keyWords=Junior+Java+developer&pg=";
    private static final String titleClass = "f-visited-enable ga_listing";
    private static final String locationClass = "fd-merchant";
    private static final String companyNameClass = "f-vacancylist-companyname fd-merchant f-text-dark-bluegray";

    private List<Vacancy> vacanciesByRabota = new LinkedList<>();
    private PageService pageService = new PageService();

    @Override
    public List<Vacancy> searchThroughPages() {
        int countSearchPage = new PageService().getCountSearchingPage(mainUrl, "class", locationClass);

        for (int i = 0; i < countSearchPage; i++) {
            List<String> titles = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", titleClass);
            List<String> locations = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", locationClass);
            List<String> companyNames = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", companyNameClass);
            List<String> vacancyDescUrl = getVacancyDescUrl(mainUrl + (i + 1), "class", titleClass);

            saveVacancies(titles, locations, companyNames, vacancyDescUrl);
        }

        return vacanciesByRabota;
    }

    @Override
    protected void saveVacancies(final List<String> titles
                               , final List<String> locations
                               , final List<String> companyNames
                               , final List<String> vacancyUrls) {
        int count = (titles.size() + locations.size() + companyNames.size() + vacancyUrls.size()) / 4;

        if (!titles.isEmpty() && !locations.isEmpty() && !companyNames.isEmpty() && !vacancyUrls.isEmpty()) {
            for (int i = 0; i < count; i++) {
                vacanciesByRabota.add(new Vacancy(titles.get(i), locations.get(i), companyNames.get(i), vacancyUrls.get(i)));
            }
        } else {
            log.info("Some list is empty.");
        }
    }

    @Override
    public void showVacancies() {
        searchThroughPages();

        vacanciesByRabota.forEach(
                vacancy -> System.out.println(vacancy.getVacancyTitle() +  " | " +
                                              vacancy.getLocation()     +  " | " +
                                              vacancy.getCompanyName()  +  " | "
        ));
    }

    private List<String> getVacancyDescUrl(final String url, final String attrKey, final String attrValue) {
        List<String> vacancyDescUrls = new ArrayList<>();

        try {
            Document document = connect(url).get();

            Elements elementsByClass = document.getElementsByAttributeValue(attrKey, attrValue);
            elementsByClass.forEach(element -> vacancyDescUrls.add(element.attr("href")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vacancyDescUrls;
    }
}