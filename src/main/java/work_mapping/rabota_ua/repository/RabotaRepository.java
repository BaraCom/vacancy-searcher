package work_mapping.rabota_ua.repository;

import lombok.Getter;
import lombok.Setter;
import work_mapping.rabota_ua.Rabota;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class RabotaRepository {
    private Map<Rabota, LocalDate> vacancies = new LinkedHashMap<>();
}
