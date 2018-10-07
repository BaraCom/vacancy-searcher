package work_mapping.services;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;
import static work_mapping.core.Vacancies.getVacancies;

public class Saver {
    public void writesToFile() {
        AtomicInteger counter = new AtomicInteger();
        counter.lazySet(1);

        getVacancies().forEach((vacancy) -> {
            try (FileWriter fileWriter = new FileWriter(LocalDate.now() + ".docs", true)) {
                fileWriter.write("            " + counter + ".\n"
                                + vacancy.getVacancyTitle() + "\n"
                                + vacancy.getLocation()     + "\n"
                                + vacancy.getCompanyName()  + "\n"
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
    }
}
