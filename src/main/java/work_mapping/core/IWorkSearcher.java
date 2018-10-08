package work_mapping.core;

import java.util.List;

public abstract class IWorkSearcher {
    protected abstract List<?> searchThroughPages();
    protected abstract void saveVacancies(final List<String> titles
                                        , final List<String> locations
                                        , final List<String> companyNames
                                        , final List<String> vacancyUrls);
    protected abstract void showVacancies();
}
