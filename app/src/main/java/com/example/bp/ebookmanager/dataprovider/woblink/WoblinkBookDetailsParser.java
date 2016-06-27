package com.example.bp.ebookmanager.dataprovider.woblink;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.BookDetailsImpl;
import com.example.bp.ebookmanager.model.BookDetailsParser;
import com.example.bp.ebookmanager.model.Publisher;
import com.example.bp.ebookmanager.model.formats.EbookSpecificData;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiDetails;
import com.example.bp.ebookmanager.model.formats.PdfDetails;

/**
 * Ebook Manager
 * Created by bp on 13.06.16.
 */
public class WoblinkBookDetailsParser implements BookDetailsParser {

    private String originalSource;
    private BookDetailsImpl result;

    @Override
    public BookDetails parse(String source) {
        this.originalSource = source;
        source = cropPublisherSource(originalSource);
        HTMLScraper scraper = new HTMLScraper(source);
        result = new BookDetailsImpl();
        scraper.evaluateXPathExpression("//a[@itemprop=\"publisher\"]");
        Publisher publisher = Publisher.named(scraper.getFirstChild());
        result.setPublisher(publisher);
        Log.d("WoblinkBDParser", "Publisher: " + publisher.getName());
        
        source = cropFileSizesSource(originalSource);
        Log.d("WoblinkBDParser", "Formats: " + source);

        parseFileSizes(source);
        return result;
    }

    private void parseFileSizes(String source) {
        int formatStringStart = source.indexOf('(');
        while (formatStringStart >= 0) {
            int formatStringEnd = source.indexOf(')', formatStringStart);
            String formatString = source.substring(formatStringStart+1, formatStringEnd);
            EbookSpecificData ebookData = determineFormat(formatString);

            String sizeString = findSizeString(source, formatStringStart);
            double size = Double.parseDouble(sizeString);
            ebookData.setSizeInMb(size);
            result.getFormatSpecificDataList().add(ebookData);

            formatStringStart = source.indexOf('(', formatStringEnd);
        }
    }

    @NonNull
    private String findSizeString(String source, int formatStringStart) {
        int sizeStringEnd = formatStringStart - 4;
        int sizeStringStart = sizeStringEnd - 1;
        while (source.charAt(sizeStringStart) != '\t' && source.charAt(sizeStringStart) != ' ')
            sizeStringStart--;

        return source.substring(sizeStringStart, sizeStringEnd);
    }

    private EbookSpecificData determineFormat(String formatString) {
        return (EbookSpecificData) FormatSpecificData.instanceForFormatName(formatString);
    }

    private String cropFileSizesSource(String source) {
        String startTag = "<td>Rozmiary plików</td><td>";
        int start = source.indexOf(startTag) + startTag.length();
        String pEndTag = "</td></tr>";
        int end = source.indexOf(pEndTag, start);
        return source.substring(start, end);
    }

    private String cropPublisherSource(String source) {
        int start = source.indexOf("<p class=\"nw_kartaproduktowa_ksiazka_info_wydawnictwo\">");
        String pEndTag = "</p>";
        int end = source.indexOf(pEndTag, start) + pEndTag.length();
        return source.substring(start, end);
    }
}
