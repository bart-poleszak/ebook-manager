package com.example.bp.ebookmanager.dataprovider.html;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Ebook Manager
 * Created by bp on 12.06.16.
 */
public class HTMLScraperTest {
    String html = "<div id=\"nw_profil_polka\" class=\"system-collection\">\n" +
            "\t\t\t<div class=\"nw_profil_polka_ksiazka \" tabindex=\"0\" aria-haspopup=\"true\">\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_okladka nw_okladka\"><img alt=\"Biblia. Pismo Święte Starego Testamentu. Kliknij by otworzyć opcje pobierania\" title=\"Biblia. Pismo Święte Starego Testamentu. Kliknij by otworzyć opcje pobierania\" width=\"190\" height=\"285\" src=\"/storable/pub_photos/191262-biblia-pismo-swiete-starego-testamentu.jpg\" /></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_strzalka\"></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_opcje\" aria-hidden=\"true\">\n" +
            "\t\t<div class=\"nw_profil_polka_ksiazka_opcje_zamknij\" aria-label=\"zamknij\">X</div>\n" +
            "\t\t<p class=\"nw_profil_polka_ksiazka_opcje_tytul\"><a title=\"AAA\" href=\"/e-book,religia-wydawnictwo-m-biblia-pismo-swiete-starego-testamentu-autor-zbiorowy,8487\">Biblia. Pismo Święte...</a></p>   \n" +
            "\t\t\t\t<p class=\"nw_profil_polka_ksiazka_opcje_autor\"><a href=\"/autor,autor-zbiorowy,3827\">qqq</a></p>\n" +
            "\t\t\t\t\t\t\t    <div class=\"nw_profil_polka_ksiazka_opcje_przyciski\">\n" +
            "\t\t\t    \t\t\t\t<div class=\"nw_profil_polka_ksiazka_opcje_przyciski_inhalf\">\n" +
            "    \t\t\t\t<a class=\"download_blue\" title=\"Pobierz plik w formacie EPUB\" href=\"/user_profile/downloadepub/id/1152612\">EPUB</a><a class=\"download_blue\" title=\"Pobierz plik w formacie MOBI\" href=\"/user_profile/downloadmobi/id/1152612\">MOBI</a>\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToKindle\" rel=\"1152612\" title=\"Wyślij na Kindle\">KINDLE</a>\n" +
            "\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToPocketBook\" rel=\"1152612\" title=\"Wyślij do Pocketbook\">POCKETBOOK</a>\n" +
            "\t\t\t\t\t\t</div><div class=\"nw_profil_polka_ksiazka_opcje_dodajdokolekcji\"><form method=\"post\" action=\"/profile/shelfCollectionBookEdit?cop=1152612\"><select name=\"copy[collection_has_copy_list]\" id=\"copy_collection_has_copy_list\">\n" +
            "\n" +
            "</select><input type=\"hidden\" name=\"copy[_csrf_token]\" value=\"7968751a7a9f257ee2fe1fa13dc797e1\" id=\"copy__csrf_token\" /><button class=\"nw_simplebutton cyan\" type=\"submit\">></button></form></div><button class=\"nw_simplebutton cyan\" onclick=\"$(this).css('display', 'none');$(this).parent().children('.nw_profil_polka_ksiazka_opcje_dodajdokolekcji').css('display', 'inline-block'); return false;\">DODAJ DO KOLEKCJI</button><form data-title=\"Biblia. Pismo Święte Starego Testamentu\" style=\"display:inline;\" class=\"delete\" method=\"post\" action=\"/profile/shelfCollectionBookDelete\"><input type=\"hidden\" value=\"1152612\" name=\"copy\" /><a class=\"nw_profil_polka_ksiazka_opcje_usun\" href=\"#\">Usuń</a></form>\t</div>\n" +
            "</div><div class=\"nw_profil_polka_ksiazka \" tabindex=\"0\" aria-haspopup=\"true\">\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_okladka nw_okladka\"><img alt=\"Korea Północna. Tajna misja w kraju wielkiego blefu. Kliknij by otworzyć opcje pobierania\" title=\"Korea Północna. Tajna misja w kraju wielkiego blefu. Kliknij by otworzyć opcje pobierania\" width=\"190\" height=\"285\" src=\"/storable/pub_photos/434384-n-a.jpg\" /></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_strzalka\"></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_opcje\" aria-hidden=\"true\">\n" +
            "\t\t<div class=\"nw_profil_polka_ksiazka_opcje_zamknij\" aria-label=\"zamknij\">X</div>\n" +
            "\t\t<p class=\"nw_profil_polka_ksiazka_opcje_tytul\"><a title=\"BBB\" href=\"/e-book,literatura-faktu-muza-sa-korea-polnocna-tajna-misja-w-kraju-wielkiego-blefu-john-sweeney,15545\">Korea Północna. Tajna misja...</a></p>   \n" +
            "\t\t\t\t<p class=\"nw_profil_polka_ksiazka_opcje_autor\"><a href=\"/autor,john-sweeney,10639\">www</a></p>\n" +
            "\t\t\t\t\t\t\t    <div class=\"nw_profil_polka_ksiazka_opcje_przyciski\">\n" +
            "\t\t\t    \t\t\t\t<div class=\"nw_profil_polka_ksiazka_opcje_przyciski_inhalf\">\n" +
            "    \t\t\t\t<a class=\"download_blue\" title=\"Pobierz plik w formacie MOBI\" href=\"/user_profile/downloadmobi/id/1528907\">MOBI</a>\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToKindle\" rel=\"1528907\" title=\"Wyślij na Kindle\">KINDLE</a>\n" +
            "\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToPocketBook\" rel=\"1528907\" title=\"Wyślij do Pocketbook\">POCKETBOOK</a>\n" +
            "\t\t\t\t\t\t</div><div class=\"nw_profil_polka_ksiazka_opcje_dodajdokolekcji\"><form method=\"post\" action=\"/profile/shelfCollectionBookEdit?cop=1528907\"><select name=\"copy[collection_has_copy_list]\" id=\"copy_collection_has_copy_list\">\n" +
            "\n" +
            "</select><input type=\"hidden\" name=\"copy[_csrf_token]\" value=\"7968751a7a9f257ee2fe1fa13dc797e1\" id=\"copy__csrf_token\" /><button class=\"nw_simplebutton cyan\" type=\"submit\">></button></form></div><button class=\"nw_simplebutton cyan\" onclick=\"$(this).css('display', 'none');$(this).parent().children('.nw_profil_polka_ksiazka_opcje_dodajdokolekcji').css('display', 'inline-block'); return false;\">DODAJ DO KOLEKCJI</button><form data-title=\"Korea Północna. Tajna misja w kraju wielkiego blefu\" style=\"display:inline;\" class=\"delete\" method=\"post\" action=\"/profile/shelfCollectionBookDelete\"><input type=\"hidden\" value=\"1528907\" name=\"copy\" /><a class=\"nw_profil_polka_ksiazka_opcje_usun\" href=\"#\">Usuń</a></form>\t</div>\n" +
            "</div><div class=\"nw_profil_polka_ksiazka \" tabindex=\"0\" aria-haspopup=\"true\">\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_okladka nw_okladka\"><img alt=\"Uczta. Kliknij by otworzyć opcje pobierania\" title=\"Uczta. Kliknij by otworzyć opcje pobierania\" width=\"185\" height=\"285\" src=\"/storable/pub_photos/61502-uczta.jpg\" /></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_strzalka\"></div>\n" +
            "\t<div class=\"nw_profil_polka_ksiazka_opcje\" aria-hidden=\"true\">\n" +
            "\t\t<div class=\"nw_profil_polka_ksiazka_opcje_zamknij\" aria-label=\"zamknij\">X</div>\n" +
            "\t\t<p class=\"nw_profil_polka_ksiazka_opcje_tytul\"><a title=\"CCC\" href=\"/e-book,klasyka-literatury-fsnz-uczta-platon,4450\">Uczta</a></p>   \n" +
            "\t\t\t\t<p class=\"nw_profil_polka_ksiazka_opcje_autor\"><a href=\"/autor,platon-,4548\">eee</a></p>\n" +
            "\t\t\t\t\t\t\t    <div class=\"nw_profil_polka_ksiazka_opcje_przyciski\">\n" +
            "\t\t\t    \t\t\t\t<div class=\"nw_profil_polka_ksiazka_opcje_przyciski_inhalf\">\n" +
            "    \t\t\t\t<a class=\"download_blue\" title=\"Pobierz plik w formacie EPUB\" href=\"/user_profile/downloadepub/id/1275098\">EPUB</a><a class=\"download_blue\" title=\"Pobierz plik w formacie MOBI\" href=\"/user_profile/downloadmobi/id/1275098\">MOBI</a>\t\t\t\t</div>\n" +
            "\t\t\t\t\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToKindle\" rel=\"1275098\" title=\"Wyślij na Kindle\">KINDLE</a>\n" +
            "\t\t\t\t\t<a href=\"#\" class=\"download_orange sendToPocketBook\" rel=\"1275098\" title=\"Wyślij do Pocketbook\">POCKETBOOK</a>\n" +
            "\t\t\t\t\t\t</div><div class=\"nw_profil_polka_ksiazka_opcje_dodajdokolekcji\"><form method=\"post\" action=\"/profile/shelfCollectionBookEdit?cop=1275098\"><select name=\"copy[collection_has_copy_list]\" id=\"copy_collection_has_copy_list\">\n" +
            "\n" +
            "</select><input type=\"hidden\" name=\"copy[_csrf_token]\" value=\"7968751a7a9f257ee2fe1fa13dc797e1\" id=\"copy__csrf_token\" /><button class=\"nw_simplebutton cyan\" type=\"submit\">></button></form></div><button class=\"nw_simplebutton cyan\" onclick=\"$(this).css('display', 'none');$(this).parent().children('.nw_profil_polka_ksiazka_opcje_dodajdokolekcji').css('display', 'inline-block'); return false;\">DODAJ DO KOLEKCJI</button><form data-title=\"Uczta\" style=\"display:inline;\" class=\"delete\" method=\"post\" action=\"/profile/shelfCollectionBookDelete\"><input type=\"hidden\" value=\"1275098\" name=\"copy\" /><a class=\"nw_profil_polka_ksiazka_opcje_usun\" href=\"#\">Usuń</a></form>\t</div>\n" +
            "</div>\t\t</div>";
    private HTMLScraper scraper;

    @Before
    public void initialize() {
        scraper = new HTMLScraper(html);
    }

    @Test
    public void scraper_returnsAttributeValueList() {
        //when
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_tytul\"]/a");
        ArrayList<String> titles = scraper.getAttributeValueList("title");
        //then
        assertTrue(checkTitles(titles));
    }

    private boolean checkTitles(ArrayList<String> titles) {
        return titles.contains("AAA") && titles.contains("BBB") && titles.contains("CCC");
    }

    @Test
    public void scraper_returnsNodeFirstChildList() {
        //when
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_autor\"]/a");
        ArrayList<String> authors = scraper.getFirstChildList();
        //then
        assertTrue(checkAuthors(authors));
    }

    private boolean checkAuthors(ArrayList<String> authors) {
        return authors.contains("qqq") && authors.contains("www") && authors.contains("eee");
    }

    @Test
    public void scraper_resturnsNulledValueInListWhenMissing() {
        //when
        scraper.evaluateXPathExpression("//div[@class=\"nw_profil_polka_ksiazka_opcje_przyciski_inhalf\"]");
        scraper.switchToChildrenWith("a", "title", "Pobierz plik w formacie EPUB");
        ArrayList<String> list = scraper.getAttributeValueList("class");
        //then
        assertTrue(checkIfMissingIsNull(list));
    }

    private boolean checkIfMissingIsNull(ArrayList<String> list) {
        return list.get(1) == null && list.get(0).equals(list.get(2)) && list.get(0).equals("download_blue");
    }
}
