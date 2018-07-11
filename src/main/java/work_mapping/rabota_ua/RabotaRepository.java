package work_mapping.rabota_ua;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Getter
@Setter
public class RabotaRepository {
    private Map<Rabota, LocalDate> vacancies = new LinkedHashMap<>();
}
