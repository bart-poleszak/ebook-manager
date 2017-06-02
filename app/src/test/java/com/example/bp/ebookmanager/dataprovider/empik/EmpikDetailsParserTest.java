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
            "    </div>\n" +
            "<div class=\"productComments\" data-title=\"Recenzje\" data-tab-name=\"reviewsTab\" >";

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
            "    </div>\n" +
            "<div class=\"productComments\" data-title=\"Recenzje\" data-tab-name=\"reviewsTab\" >";

    private String wegnerSource = "<div data-title=\"Dane szczegółowe\">\n" +
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
            "          <span class=\"attributeDetailsValue\">Gdybym miała brata. Opowieści z meekhańskiego pogranicza</span>\n" +
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
            "        Autor: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">      <a href=\"/szukaj/produkt?author=Wegner+Robert+M.\" >Wegner&nbsp;Robert&nbsp;M.</a>\n" +
            "</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
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
            "        Wydawnictwo: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">Wydawnictwo Powergraph</span>\n" +
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
            " \n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Ilość stron: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">65</span>\n" +
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
            "        Data premiery: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">2015-10-29</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "\n" +
            "\n" +
            "    <tr class=\"row--text row--text attributeName\" >\n" +
            "\n" +
            "\n" +
            "<td >\n" +
            "        Rok wydania: \n" +
            "      </td>\n" +
            "\n" +
            "<td >\n" +
            "          <span class=\"attributeDetailsValue\">2015</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
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
            "          <span class=\"attributeDetailsValue\">    <span class=\"ebookFormatInfo\">EPUB</span>\n" +
            "  <div class=\"showHint hintSet\" type=\"text\" data-trigger=\"manual\" data-toggle=\"tooltip\" data-container=\".hintSet\" data-html=\"true\" data-title=\"Ebook chroniony przy pomocy watermark - więcej informacji w sekcji &lt;a href=&quot;http://www.empik.com/pomoc/faq-ebook&quot; target=&quot;_blank&quot; &gt;Pomocy ›&lt;/a&gt;\" data-placement=\"right\">\n" +
            "<img src=\"/b/mp/img/svg/info.svg\"   alt=\"\" />    </div>\n" +
            "        <!-- gry on-line-->\n" +
            "\n" +
            "    <span class=\"ebookLicenseInfo\">\n" +
            "<strong>UWAGA!</strong> Ebook chroniony przez watermark.             <a href=\"/pomoc/faq-ebook\" target=\"_blank\" ><span>więcej ›</span></a>\n" +
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
            "          <span class=\"attributeDetailsValue\">bez ograniczeń</span>\n" +
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
            "          <span class=\"attributeDetailsValue\">bez ograniczeń</span>\n" +
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
            "          <span class=\"attributeDetailsValue\">18465563</span>\n" +
            "        </td>\n" +
            "    </tr>\n" +
            "\n" +
            "            </tbody>\n" +
            "        </table>\n" +
            "    </div>\n" +
            "<div class=\"productComments\" data-title=\"Recenzje\" data-tab-name=\"reviewsTab\" >";

    @Test
    public void parser_parsesPublisherCorrectly() {
        EmpikDetailsParser parser = new EmpikDetailsParser();
        //when
        BookDetails details = parser.parse(wegnerSource);
        //then
        assertEquals("Wydawnictwo Powergraph", details.getPublisher().getName());
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
        BookDetails details = parser.parse(wegnerSource);
        //then
        assertNull(details.getTranslator());
    }
}
