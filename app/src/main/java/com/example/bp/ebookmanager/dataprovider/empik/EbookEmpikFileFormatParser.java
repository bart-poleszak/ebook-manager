package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.WebBookDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Bartek on 2017-05-22.
 */

class EbookEmpikFileFormatParser implements EmpikFileFormatParser {
    @Override
    public void parse(HTMLScraper scraper, ArrayList<WebBookDetails> detailsList) {
        for (int i = 0; i < detailsList.size(); ++i) {
            scraper.reset();
            scraper.evaluateXPathExpression("(//div[@class=\"download-open\"])[" + String.valueOf(i + 1) + "]//a");
            ArrayList<String> hrefs = scraper.getAttributeValueList("href");
            ArrayList<String> formats = scraper.getFirstChildList();

            for (int formatIndex = 0; formatIndex < formats.size(); formatIndex++) {
                FormatDetails formatDetails = FormatDetails.instanceForFormatName(formats.get(formatIndex).trim());
                formatDetails.setDownloadUrl(hrefs.get(formatIndex));
                detailsList.get(i).addFormat(formatDetails);
            }
        }
    }
}
