package com.example.bp.ebookmanager.dataprovider.woblink;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

/**
 * Created by bp on 11.06.16.
 */
public class ShelfWoblinkWebActionState implements WebActionState {
    private String shelfUrl;
    private boolean actionCompleted = false;

    public void setShelfUrl(String shelfUrl) {
        this.shelfUrl = shelfUrl;
    }

    @Override
    public String getTargetSiteURL() {
        return "https://woblink.com" + shelfUrl;
    }

    @Override
    public void processRecievedData(String url, String source) {
        Log.d("WoblinkShelf", url);

        source = prepareSource(source);
        HTMLScraper scraper = new HTMLScraper(source);
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_tytul\"]/a");
        if (scraper.evaluationSuccessful())
            for (String str : scraper.getAttributeValueList("title"))
                Log.d("Shelf", str);

        scraper.reset(source);
        scraper.evaluateXPathExpression("//p[@class=\"nw_profil_polka_ksiazka_opcje_autor\"]/a");
        if (scraper.evaluationSuccessful())
            for (String str : scraper.getFirstChildList())
                Log.d("Shelf", str);
        scraper.reset(source);
        scraper.evaluateXPathExpression("//div[@class=\"nw_profil_polka_ksiazka_okladka nw_okladka\"]/img");
        if (scraper.evaluationSuccessful())
            for (String str : scraper.getAttributeValueList("src"))
                Log.d("Shelf", str);
        actionCompleted = true;

    }

    @NonNull
    private String prepareSource(String source) {
        int start = source.indexOf("<div id=\"nw_profil_polka\"");
        int end = source.indexOf("<aside id=\"nw_paginacja\">", start);
        source = source.substring(start, end);
//        source = source.replaceAll("<img\\b[^>]*>", "");
        String imgTag = "<img";
        int imgIndex = source.indexOf(imgTag);
        while (imgIndex >= 0) {
            int imgEndIndex = source.indexOf(">", imgIndex) + 1;
            String substring = source.substring(imgIndex, imgEndIndex);
            source = source.replace(substring, substring.replace(">", "/>"));
            imgIndex = source.indexOf(imgTag, imgEndIndex);
        }
        source = source.replaceAll("<input\\b[^>]*>", "");
        return source;
    }

    @Override
    public boolean isActionCompleted() {
        return actionCompleted;
    }

    @Override
    public boolean isUserActionNeeded() {
        return false;
    }

    @Override
    public List<Book> getBooks() {
        ArrayList<Book> result = new ArrayList<>();
        Book book = new Book();
        book.setTitle("Zamokowana ksiazka z Woblink");
        Person dGlukhowsky = new Person();
        dGlukhowsky.setName("Dmitry Glukhovsky");
        book.setAuthor(dGlukhowsky);
        book.getFormats().add(new PdfSpecificData());
        result.add(book);
        return result;
    }
}
