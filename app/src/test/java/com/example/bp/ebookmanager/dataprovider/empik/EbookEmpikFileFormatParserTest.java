package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.dataprovider.mock.TestHTMLCodeProvider;
import com.example.bp.ebookmanager.model.WebBookDetails;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.model.formats.MobiDetails;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Bartek on 2017-05-22.
 */
public class EbookEmpikFileFormatParserTest {
    private static String source;
    private HTMLScraper scraper;
    private ArrayList<WebBookDetails> detailsList;

    @BeforeClass
    public static void beforeClass() {
        source = TestHTMLCodeProvider.getCroppedEmpikEbookLibrarySource();
    }

    @Before
    public void before() {
        detailsList = new ArrayList<>();
        detailsList.add(new WebBookDetails());
        scraper = new HTMLScraper(source);
    }

    @Test
    public void parser_parsesProperNumberOfEbookFormats() {
        EbookEmpikFileFormatParser parser = new EbookEmpikFileFormatParser();
        //when
        parser.parse(scraper, detailsList);
        //then
        assertEquals(2, detailsList.get(0).getFormats().size());
    }

    @Test
    public void parser_recognizesEbookFormats() {
        EbookEmpikFileFormatParser parser = new EbookEmpikFileFormatParser();
        parser.parse(scraper, detailsList);
        //when
        ArrayList<String> formatNames = new ArrayList<>();
        for (FormatDetails formatDetails : detailsList.get(0).getFormats()) {
            formatNames.add(formatDetails.getFormatName());
        }
        //then
        assertTrue(formatNames.contains(EpubDetails.FORMAT_NAME));
        assertTrue(formatNames.contains(MobiDetails.FORMAT_NAME));
    }

    @Test
    public void parser_parsesEbookDownloadLinks() {
        EbookEmpikFileFormatParser parser = new EbookEmpikFileFormatParser();
        parser.parse(scraper, detailsList);
        //when
        ArrayList<String> downloadLinks = new ArrayList<>();
        for (FormatDetails formatDetails : detailsList.get(0).getFormats()) {
            downloadLinks.add(formatDetails.getDownloadUrl());
        }
        //then
        assertEquals(2, downloadLinks.size());
        assertTrue(downloadLinks.contains("http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/epub"));
        assertTrue(downloadLinks.contains("http://ebook.empik.com/transakcja/10100271438551/pozycja/10100452954443/licencja/1/plik/1/format/mobi"));
    }

}