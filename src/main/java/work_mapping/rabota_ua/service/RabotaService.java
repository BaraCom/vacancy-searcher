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
    private static final String titleClass = "f-visited-enable ga_listing";
    private static final String locationClass = "fd-merchant";
    private static final String companyNameClass = "f-vacancylist-companyname fd-merchant f-text-dark-bluegray";

    private RabotaRepository rabotaRepository = new RabotaRepository();
    private PageService pageService = new PageService();

    public void startSearching() {
        int countSearchPage = new PageService().getCountSearchingPage(mainUrl, "class", locationClass);

        for (int i = 0; i < countSearchPage; i++) {
            List<String> titles = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", titleClass);
            List<String> locations = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", locationClass);
            List<String> companyNames = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", companyNameClass);

            saveVacancies(titles, locations, companyNames);
        }

        getSearchResult();
    }

    private void saveVacancies(List<String> titles, List<String> locations, List<String> companyNames) {
        int count = (titles.size() + locations.size() + companyNames.size()) / 3;

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