package work_mapping.core.service;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.jsoup.Jsoup.connect;

public class PageService {
    public List<String> getAttrValueByClass(String url, String key, String valuePrefix) {
        try {
            List<String> list = new ArrayList<>();
            Document page = connect(url).get();
            Elements elementsByAttributeValueStarting = page.getElementsByAttributeValue(key, valuePrefix);

            elementsByAttributeValueStarting.forEach(element -> list.add(element.text()));

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCountSearchingPage(final String url, final String tagKey, final String locationClass) {
        int count = 1;

        for (int i = 0; i < 10_000; i++) {
            List<String> list = getAttrValueByClass(url + count, tagKey, locationClass);

            if (!list.isEmpty()) {
                count++;
            } else {
                break;
            }
        }
        return count - 1;
    }

    public List<String> getIdValuesByTag(String url, String classValue, String tagName) {
        List<String> idValues = new ArrayList<>();

        try {
            Document document = connect(url).get();
            Elements table = document.getElementsByClass(classValue);

            table.forEach(element -> {
                Elements tr = element.getElementsByTag(tagName);

                tr.forEach(element1 -> element1.attributes().forEach(attribute -> {
                    if (attribute.getKey().equals("id")) {
                        idValues.add(attribute.getValue());
                    }
                }));
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
        return idValues;
    }

    public List<String> getVacancyWithPage(final String url, final String attrKey, final String attrValue) {
        return getAttrValueByClass(url, attrKey, attrValue);
    }
}