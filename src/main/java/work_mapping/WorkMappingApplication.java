package work_mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work_mapping.rabota_ua.entity.Rabota;
import work_mapping.rabota_ua.service.RabotaService;
import work_mapping.services.Saver;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

@SpringBootApplication
public class WorkMappingApplication {
    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(WorkMappingApplication.class, args);

//        new RabotaService().vacancySearching();


        Map<Rabota, LocalDate> vacancies = new LinkedHashMap<>();

        Scanner scanner = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            System.out.println("1. | Get search result.");
            System.out.println("2. | Write the result to file.");
            System.out.println("3. | Exit with program.");

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Getting search result...");

                    vacancies = new RabotaService().vacancySearching();
                    break;
                }
                case 2: {
                    new Saver().writesToFile(vacancies);

                    break;
                }
                case 3: {
                    flag = false;
                    System.out.println("See you...   ^_^  .");
                    break;
                }
                default: {
                    flag = false;
                    System.out.println("break!!");
                    break;
                }
            }
        }
    }
}
