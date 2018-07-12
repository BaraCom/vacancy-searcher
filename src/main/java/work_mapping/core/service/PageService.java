package work_mapping.core.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageService {

    public List<String> getStringByAttributeValueStarting(String url, String key, String valuePrefix) {
        try {
            List<String> list = new ArrayList<>();
            Document page = Jsoup.connect(url).get();
            Elements elementsByAttributeValueStarting = page.getElementsByAttributeValueStarting(key, valuePrefix);

            elementsByAttributeValueStarting.forEach(element -> list.add(element.text()));

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> getStringByClass(String url, String key, String valuePrefix) {
        try {
            List<String> list = new ArrayList<>();
            Document page = Jsoup.connect(url).get();
            Elements elementsByAttributeValueStarting = page.getElementsByAttributeValue(key, valuePrefix);

            elementsByAttributeValueStarting.forEach(element -> list.add(element.text()));

            return list;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getCountSearchPage(final String url, final String locationClass) {
        int count = 1;

        for (int i = 0; i < 10_000; i++) {
            List<String> list = getStringByClass(url + count, "class", locationClass);

            if (!list.isEmpty()) {
                count++;
            } else {
                break;
            }
            System.out.println("new COUNT -> " + (count - 1));
        }
        return count - 1;
    }
}