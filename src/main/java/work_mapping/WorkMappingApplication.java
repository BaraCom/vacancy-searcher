package work_mapping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import work_mapping.rabota_ua.service.RabotaService;

@SpringBootApplication
public class WorkMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkMappingApplication.class, args);

        new RabotaService().startSearching();
    }
}
