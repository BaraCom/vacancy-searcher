package work_mapping.rabota_ua;

import work_mapping.core.PageService;
import java.time.LocalDate;
import java.util.List;

public class RabotaService {
    private static final String mainUrl = "https://rabota.ua/jobsearch/vacancy_list?keyWords=Junior+Java+developer";
    private static final String titleClass = "f-visited-enable";
    private static final String locationClass = "fd-merchant";
    private static final String companyNameClass = "f-text-dark-bluegray f-visited-enable";

    private RabotaRepository rabotaRepository = new RabotaRepository();
    private PageService pageService = new PageService();

    public void createVacancy() {
        List<String> titles = pageService.getStringByAttributeValueStarting(mainUrl, "class", titleClass);
        List<String> locations = pageService.getStringByClass(mainUrl, "class", locationClass);
        List<String> companyNames = pageService.getStringByClass(mainUrl, "class", companyNameClass);

        int count = (titles.size() + locations.size() + companyNames.size()) / 3;

        for (int i = 0; i < count; i++) {
            rabotaRepository.getVacancies().put(new Rabota(titles.get(i), locations.get(i), companyNames.get(i)), LocalDate.now());
        }
    }

    public void getSearchResult() {
        rabotaRepository.getVacancies().forEach((rabota, s) -> System.out.println(rabota.getVacancyTitle() +  " | " +
                rabota.getLocation() +  " | " +
                rabota.getCompanyName() +  " | " +
                s)
        );
    }
}

