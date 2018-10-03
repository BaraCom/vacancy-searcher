package work_mapping.rabota_ua.repository;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Repository;
import work_mapping.rabota_ua.entity.Rabota;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
//@Repository
public class RabotaRepository {
    private Map<Rabota, LocalDate> vacancies = new LinkedHashMap<>();
}
