package work_mapping.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import work_mapping.rabota_ua.entity.Rabota;
import work_mapping.rabota_ua.repository.RabotaRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;


public class Saver {

//    @Autowired
    private RabotaRepository rabotaRepository = new RabotaRepository();

    public void writesToFile(final Map<Rabota, LocalDate> vacancies) {
        AtomicInteger counter = new AtomicInteger();
        counter.lazySet(1);

//        Map<Rabota, LocalDate> vacancies = rabotaRepository.getVacancies();

//        rabotaRepository.getVacancies().forEach((rabota, localDate) -> {
        vacancies.forEach((rabota, localDate) -> {

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

        /*rabotaRepository.getVacancies()
                .forEach((rabota, date) ->
                    System.out.println(rabota.getVacancyTitle() +  " | " +
                                       rabota.getLocation()     +  " | " +
                                       rabota.getCompanyName()  +  " | " +
                                       date)
        );*/
    }
}
