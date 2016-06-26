package com.example.bp.ebookmanager.dataprovider.woblink;

import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.formats.EpubDetails;
import com.example.bp.ebookmanager.model.formats.FormatSpecificData;
import com.example.bp.ebookmanager.model.formats.MobiDetails;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Ebook Manager
 * Created by bp on 27.06.16.
 */
public class WoblinkDetailsParserTest {
    String src = "<p class=\"nw_kartaproduktowa_ksiazka_info_wydawnictwo\">Wydawnictwo: <a itemprop=\"publisher\" href=\"/wydawnictwa/muza-sa/22485\">MUZA SA</a></p>" +
            "<tr><td>Rozmiary plików</td><td>\t\t\t\t\t\t\t\t\t\t\t\t\t\t7.36 MB (MOBI),\t\t\t\t\t\t\t\t\t\t\t\t\t\t3.61 MB (EPUB)\t\t\t\t\t\t\t\t\t\t\t\t\t\t\n</td></tr>";

    @Test
    public void parser_ParsesCorrectNumberOfFormats() {
        WoblinkBookDetailsParser parser = new WoblinkBookDetailsParser();
        //when
        BookDetails details = parser.parse(src);
        //then
        assertEquals(2, details.getFormatSpecificDataList().size());
    }

    @Test
    public void parser_parsesSizesProperly() {
        WoblinkBookDetailsParser parser = new WoblinkBookDetailsParser();
        //when
        BookDetails details = parser.parse(src);
        //then
        assertTrue(checkFormatSizes(details));
    }

    private boolean checkFormatSizes(BookDetails details) {
        if (details.getFormatSpecificDataList().size() != 2)
            return false;
        for (FormatSpecificData formatData : details.getFormatSpecificDataList()) {
            if (formatData.getFormatName().equals(MobiDetails.FORMAT_NAME)) {
                if (formatData.getSizeInMb() != 7.36)
                    return false;
            }
            else if (formatData.getFormatName().equals(EpubDetails.FORMAT_NAME)) {
                if (formatData.getSizeInMb() != 3.61)
                    return false;
            }
            else
                return false;
        }
        return true;
    }
}
