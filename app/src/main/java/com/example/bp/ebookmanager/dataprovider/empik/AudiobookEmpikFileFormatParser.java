package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.WebBookDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import java.util.ArrayList;

/**
 * Created by Bartek on 2017-05-22.
 */

class AudiobookEmpikFileFormatParser implements EmpikFileFormatParser {
    @Override
    public void parse(HTMLScraper scraper, ArrayList<WebBookDetails> detailsList) {
        ArrayList<String> hrefs = parseDownloadLinks(scraper);
        if (detailsList.size() != hrefs.size()) {
            throw new RuntimeException("Error parsing audiobook download links");
        }

        fixLinks(hrefs);
        fillDetailsWithDownloadUrls(detailsList, hrefs);
    }

    private ArrayList<String> parseDownloadLinks(HTMLScraper scraper) {
        scraper.reset();
        scraper.evaluateXPathExpression("//a[@class=\"download\"]");
        return scraper.getAttributeValueList("href");
    }

    private void fillDetailsWithDownloadUrls(ArrayList<WebBookDetails> detailsList, ArrayList<String> hrefs) {
        for (int i = 0; i < detailsList.size(); i++) {
            Mp3Details details = new Mp3Details();
            details.setDownloadUrl(hrefs.get(i));
            detailsList.get(i).addFormat(details);
        }
    }

    private void fixLinks(ArrayList<String> hrefs) {
        for (int i = 0; i < hrefs.size(); i++) {
            String href = hrefs.get(i);
            href = href.replace(EmpikBookDataParser.LINE_ITEM_PLACEHOLDER, EmpikBookDataParser.LINE_ITEM);
            href = href.replace(EmpikBookDataParser.USER_ID_PLACEHOLDER, EmpikBookDataParser.USER_ID);
            hrefs.set(i, EmpikBookDataParser.EMPIK_COM + href);
        }
    }
}
