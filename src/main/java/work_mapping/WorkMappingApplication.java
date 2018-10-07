package work_mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work_mapping.core.Vacancies;
import work_mapping.services.Saver;

import java.util.Scanner;

@SpringBootApplication
public class WorkMappingApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;

    public static void main(String[] args) {
        SpringApplication.run(WorkMappingApplication.class, args);

        while (flag) {
            System.out.println("1. | Show search result.");
            System.out.println("2. | Write the result to file.");
            System.out.println("3. | Exit with program.");

            switch (scanner.nextInt()) {
                case 1: {
                    System.out.println("Getting search result...");

                    new Vacancies().showVacancies();
                    break;
                }
                case 2: {
                    new Saver().writesToFile();

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
