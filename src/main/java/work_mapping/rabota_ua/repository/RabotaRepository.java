package work_mapping.rabota_ua.repository;

import work_mapping.core.Vacancy;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class RabotaRepository {
    private Map<Vacancy, LocalDate> vacancies = new LinkedHashMap<>();
}
