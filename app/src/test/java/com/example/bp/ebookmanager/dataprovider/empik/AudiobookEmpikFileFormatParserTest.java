package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.dataprovider.mock.TestHTMLCodeProvider;
import com.example.bp.ebookmanager.model.WebBookDetails;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by Bartek on 2017-05-22.
 */
public class AudiobookEmpikFileFormatParserTest {

    private static final int METRO_INDEX = 0;
    private static final int KLAMCA_INDEX = 1;

    private static String source;

    @BeforeClass
    public static void beforeClass() {
        source = TestHTMLCodeProvider.getCroppedEmpikAudiobookLibrarySource();
    }

    @Test
    public void parser_parsesMp3DownloadLinks() {
        ArrayList<WebBookDetails> detailsList = new ArrayList<>();
        detailsList.add(new WebBookDetails());
        detailsList.add(new WebBookDetails());
        HTMLScraper scraper = new HTMLScraper(source);
        AudiobookEmpikFileFormatParser parser = new AudiobookEmpikFileFormatParser();
        //when
        parser.parse(scraper, detailsList);
        String metroUrl = detailsList.get(METRO_INDEX).getFormats().get(0).getDownloadUrl();
        String klamcaUrl = detailsList.get(KLAMCA_INDEX).getFormats().get(0).getDownloadUrl();
        //then
        assertEquals("http://empik.com/media?OrderId=10100215233679&LineItemId=10100323991515&UserId=91733075", metroUrl);
        assertEquals("http://empik.com/media?OrderId=10100210623581&LineItemId=10100314262907&UserId=91733075", klamcaUrl);
    }

    @Test(expected = RuntimeException.class)
    public void parser_throwsIfWrongNumberOfBookDetailsProvided() {
        ArrayList<WebBookDetails> detailsList = new ArrayList<>();
        detailsList.add(new WebBookDetails());
        detailsList.add(new WebBookDetails());
        detailsList.add(new WebBookDetails());
        HTMLScraper scraper = new HTMLScraper(source);
        AudiobookEmpikFileFormatParser parser = new AudiobookEmpikFileFormatParser();
        //when
        parser.parse(scraper, detailsList);
        ///then throw RuntimeException
    }
}