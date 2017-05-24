package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.model.BookDetails;
import com.example.bp.ebookmanager.model.formats.FormatDetails;
import com.example.bp.ebookmanager.model.formats.Mp3Details;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Bartek on 2017-04-23.
 */

public class EmpikDetailsParserTest {
    private String klamcaSource = "<div data-title=\"Dane szczegółowe\">\n" +
            "        <h4 class=\"tab-temporary-title\">\n" +
            "            Dane szczegółowe\n" +
            "        </h4>\n" +
            "        \n" +
            "        <table class=\"productDataTable\">\n" +
            "            <tbody>\n" +
            "                \n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Tytuł: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Kłamca. Tom 1</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Seria: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\"><a href=\"/szukaj/produkt?seriesFacet=Ku%C5%BAnia+Fantast%C3%B3w\" >Kuźnia Fantastów</a></span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Autor: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">      <a href=\"/szukaj/produkt?author=%C4%86wiek+Jakub\" >Ćwiek&nbsp;Jakub</a>\n" +
            "</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Lektor: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Banaszyk Krzysztof</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Wydawnictwo: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Fabryka Słów Sp. z o.o.</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język wydania: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">polski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język oryginału: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">polski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język lektora: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">polski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Format: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">    <span class=\"ebookFormatInfo\">MP3</span>\n" +
            "\n" +
            "    <span class=\"ebookLicenseInfo\">\n" +
            "    </span>\n" +
            "</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Liczba urządzeń: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">bez ograniczeń</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Drukowanie: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">nie dotyczy</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Kopiowanie: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">nie dotyczy</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Indeks: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">13082512</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n";

    private String metro2033Source = "<div data-title=\"Dane szczegółowe\">\n" +
            "        <h4 class=\"tab-temporary-title\">\n" +
            "            Dane szczegółowe\n" +
            "        </h4>\n" +
            "        \n" +
            "        <table class=\"productDataTable\">\n" +
            "            <tbody>\n" +
            "                \n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Tytuł: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Uniwersum Metro 2033. Metro 2033</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Tytuł oryginalny: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Метро 2033</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Seria: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\"><a href=\"/szukaj/produkt?seriesFacet=Uniwersum+Metro+2033\" >Uniwersum Metro 2033</a></span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Autor: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">      <a href=\"/szukaj/produkt?author=Glukhovsky+Dmitry\" >Glukhovsky&nbsp;Dmitry</a>\n" +
            "</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Tłumacz: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Opracowanie zbiorowe</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Lektor: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Gosztyła Krzysztof</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Wydawnictwo: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Wydawnictwo Insignis</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język wydania: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">polski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język oryginału: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">rosyjski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Język lektora: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">polski</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            " \n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Format: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">    <span class=\"ebookFormatInfo\">MP3</span>\n" +
            "\n" +
            "    <span class=\"ebookLicenseInfo\">\n" +
            "    </span>\n" +
            "</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Liczba urządzeń: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">bez ograniczeń</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Drukowanie: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">nie dotyczy</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Kopiowanie: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">nie dotyczy</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Indeks: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">13096724</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n";

    @Test
    public void parser_parsesPublisherCorrectly() {
        EmpikDetailsParser parser = new EmpikDetailsParser();
        //when
        BookDetails details = parser.parse(klamcaSource);
        //then
        assertEquals("Fabryka Słów Sp. z o.o.", details.getPublisher().getName());
    }

    @Test
    public void parser_parsesTranslatorCorrectly() {
        EmpikDetailsParser parser = new EmpikDetailsParser();
        //when
        BookDetails details = parser.parse(metro2033Source);
        //then
        assertEquals("Opracowanie zbiorowe", details.getTranslator().getName());
    }

    @Test
    public void parser_parsesNarratorCorrectly() {
        EmpikDetailsParser parser = new EmpikDetailsParser();
        //when
        BookDetails details = parser.parse(metro2033Source);
        Mp3Details mp3Details = (Mp3Details) details.getFormat(Mp3Details.FORMAT_NAME);
        //then
        assertEquals("Gosztyła Krzysztof", mp3Details.getNarrator().getName());
    }

    @Test
    public void parser_setsTranslatorToNullIfThereAreNotAny() {
        EmpikDetailsParser parser = new EmpikDetailsParser();
        //when
        BookDetails details = parser.parse(klamcaSource);
        //then
        assertNull(details.getTranslator());
    }
}
