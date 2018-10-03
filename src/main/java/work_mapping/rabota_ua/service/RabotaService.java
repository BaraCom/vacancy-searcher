package work_mapping.rabota_ua.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import work_mapping.core.service.PageService;
import work_mapping.rabota_ua.entity.Rabota;
import work_mapping.rabota_ua.repository.RabotaRepository;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

//@Service
public class RabotaService {
    private static final String mainUrl = "https://rabota.ua/jobsearch/vacancy_list?keyWords=Junior+Java+developer&pg=";
    private static final String titleClass = "f-visited-enable ga_listing";
    private static final String locationClass = "fd-merchant";
    private static final String companyNameClass = "f-vacancylist-companyname fd-merchant f-text-dark-bluegray";

    @Getter
    private Map<Rabota, LocalDate> vacancies = new LinkedHashMap<>();

//    @Autowired
    private RabotaRepository rabotaRepository = new RabotaRepository();
//    @Autowired
    private PageService pageService = new PageService();

    public Map<Rabota, LocalDate> vacancySearching() {
        Map<Rabota, LocalDate> rabotaLocalDateMap = null;
        int countSearchPage = new PageService().getCountSearchingPage(mainUrl, "class", locationClass);

        for (int i = 0; i < countSearchPage; i++) {
            List<String> titles = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", titleClass);
            List<String> locations = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", locationClass);
            List<String> companyNames = pageService.getVacancyWithPage(mainUrl + (i + 1), "class", companyNameClass);

            rabotaLocalDateMap = saveVacancies(titles, locations, companyNames);
        }

        return rabotaLocalDateMap;
    }

    private Map<Rabota, LocalDate> saveVacancies(List<String> titles, List<String> locations, List<String> companyNames) {
        int count = (titles.size() + locations.size() + companyNames.size()) / 3;

//        Map<Rabota, LocalDate> m = new LinkedHashMap<>();

        if (!titles.isEmpty() && !locations.isEmpty() && !companyNames.isEmpty()) {
            for (int i = 0; i < count; i++) {
//                rabotaRepository.getVacancies().put(new Rabota(titles.get(i), locations.get(i), companyNames.get(i)), LocalDate.now());
                vacancies.put(new Rabota(titles.get(i), locations.get(i), companyNames.get(i)), LocalDate.now());
            }
//            rabotaRepository.setVacancies(m);
        } else {
            System.out.println("EMPTY!!!");
        }

        showVacancies(vacancies);

        return vacancies;
    }

    /*private void writesToFile() {
        AtomicInteger counter = new AtomicInteger();
        counter.lazySet(1);

        rabotaRepository.getVacancies().forEach((rabota, localDate) -> {

            try (FileWriter fileWriter = new FileWriter(localDate + ".docs", true)) {
                fileWriter.write("            " + counter + ".\n"
                                   + rabota.getVacancyTitle() + "\n"
                                   + rabota.getLocation()     + "\n"
                                   + rabota.getCompanyName()  + "\n"
//                                   + rabota.getCompanyLink() + "\n"
//                                   + rabota.getDescription() + "\n"
                                   + "-------------------------------------------------------------------------\n"
                                   + "\n"
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
    }*/
    private void showVacancies(final Map<Rabota, LocalDate> vacancies) {
        vacancies.forEach((rabota, date) ->
                    System.out.println(rabota.getVacancyTitle() +  " | " +
                                       rabota.getLocation()     +  " | " +
                                       rabota.getCompanyName()  +  " | " +
                                       date)
        );
    }
}