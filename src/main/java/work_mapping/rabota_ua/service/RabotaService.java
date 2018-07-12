package work_mapping.rabota_ua.service;

import work_mapping.core.service.PageService;
import work_mapping.rabota_ua.Rabota;
import work_mapping.rabota_ua.repository.RabotaRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RabotaService {
    private static final String mainUrl = "https://rabota.ua/jobsearch/vacancy_list?keyWords=Junior+Java+developer&pg=";
    private static final String titleClass = "f-visited-enable";
    private static final String locationClass = "fd-merchant";
    private static final String companyNameClass = "f-vacancylist-companyname fd-merchant f-text-dark-bluegray";

    private RabotaRepository rabotaRepository = new RabotaRepository();
    private PageService pageService = new PageService();

    public void startSearching() {
        int countSearchPage = new PageService().getCountSearchPage(mainUrl, locationClass);

        for (int i = 0; i < countSearchPage; i++) {
            createVacancy(i + 1);
        }

        getSearchResult();
    }

    private void createVacancy(int countPage) {
        List<String> titles = pageService.getStringByAttributeValueStarting(mainUrl + countPage, "class", titleClass);
        List<String> locations = pageService.getStringByClass(mainUrl + countPage, "class", locationClass);
        List<String> companyNames = pageService.getStringByClass(mainUrl + countPage, "class", companyNameClass);

        int count = (titles.size() + locations.size() + companyNames.size()) / 3;

        System.out.println("COUNT -->> " + count);

        if (!titles.isEmpty() && !locations.isEmpty() && !companyNames.isEmpty()) {
            for (int i = 0; i < count; i++) {
                rabotaRepository.getVacancies().put(new Rabota(titles.get(i), locations.get(i), companyNames.get(i)), LocalDate.now());
            }
        } else {
            System.out.println("EMPTY!!!");
        }
    }

    private void getSearchResult() {
        AtomicInteger counter = new AtomicInteger();
        counter.lazySet(1);

        rabotaRepository.getVacancies().forEach((rabota, localDate) -> {

            try (FileWriter fileWriter = new FileWriter(localDate + ".docs", true)) {
                fileWriter.write(counter + ". "
                                   + rabota.getVacancyTitle() + " | "
                                   + rabota.getLocation()     + " | "
                                   + rabota.getCompanyName()  + "\n"
                );
                counter.incrementAndGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        rabotaRepository.getVacancies().forEach((rabota, date) -> System.out.println(rabota.getVacancyTitle() +  " | " +
                                                                                     rabota.getLocation()     +  " | " +
                                                                                     rabota.getCompanyName()  +  " | " +
                                                                                     date)
        );
    }
}