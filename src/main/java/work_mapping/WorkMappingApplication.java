package work_mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work_mapping.rabota_ua.Rabota;
import work_mapping.rabota_ua.RabotaService;

@SpringBootApplication
public class WorkMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMappingApplication.class, args);

        RabotaService rabota = new RabotaService();
        rabota.createVacancy();
        rabota.getSearchResult();
    }
}
