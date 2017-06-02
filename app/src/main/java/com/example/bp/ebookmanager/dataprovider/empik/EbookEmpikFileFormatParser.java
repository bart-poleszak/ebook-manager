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
            ArrayList<String> formats = scraper.getFirstChildList();
            String downloadLinkBase = scraper.getAttributeValue("href");
            downloadLinkBase = downloadLinkBase.substring(0, downloadLinkBase.lastIndexOf('/') + 1);

            for (String format : formats) {
                String trimmedFormat = format.trim();
                FormatDetails formatDetails = FormatDetails.instanceForFormatName(trimmedFormat);
                formatDetails.setDownloadUrl(downloadLinkBase + trimmedFormat);
                detailsList.get(i).addFormat(formatDetails);
            }
        }
    }
}
