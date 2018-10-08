package work_mapping;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work_mapping.core.Vacancies;
import work_mapping.services.Saver;

import java.io.IOException;
import java.util.Scanner;

import static org.jsoup.Jsoup.connect;

@SpringBootApplication
public class WorkMappingApplication {
    private static Scanner scanner = new Scanner(System.in);
    private static boolean flag = true;

    public static void main(String[] args) throws IOException {
        SpringApplication.run(WorkMappingApplication.class, args);

        /*Document document = connect("https://rabota.ua/jobsearch/vacancy_list?keyWords=Junior+Java+developer&pg=1").get();
        Elements elementsByClass = document.getElementsByAttributeValue("class", "f-visited-enable ga_listing");

        elementsByClass.forEach(element -> System.out.println(element.attr("href")));*/


        /*while (flag) {
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
        }*/
    }
}
