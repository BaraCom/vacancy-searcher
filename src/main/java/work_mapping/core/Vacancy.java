package work_mapping.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vacancy {
    private String vacancyTitle;
    private String location;
    private String companyName;
}
