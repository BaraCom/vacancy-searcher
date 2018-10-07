package work_mapping.core;

import work_mapping.rabota_ua.service.RabotaService;
import java.util.LinkedList;
import java.util.List;

public class Vacancies {
    private static List<Vacancy> vacancies = new LinkedList<>();

    public void showVacancies() {
        new RabotaService().showVacancies();
    }

    public static List<Vacancy> getVacancies() {
        vacancies.addAll(new RabotaService().searchThroughPages());

        return vacancies;
    }
}
