package com.example.bp.ebookmanager.dataprovider.mock;

import com.example.bp.ebookmanager.dataprovider.WebActionContext;
import com.example.bp.ebookmanager.dataprovider.WebActionState;
import com.example.bp.ebookmanager.model.Book;
import com.example.bp.ebookmanager.model.Person;
import com.example.bp.ebookmanager.model.formats.PdfSpecificData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bp on 11.06.16.
 */
public class MockWebActionContext implements WebActionContext{
    private WebActionState state = new StartMockWebActionState();

    @Override
    public String getTargetSiteURL() {
        return state.getTargetSiteURL();
    }

    @Override
    public void processRecievedData(String url, String source) {
        state.processRecievedData(url, source);
        if (!state.isActionCompleted())
            state = new FinalMockWebActionState();
    }

    @Override
    public boolean isActionCompleted() {
        return state.isActionCompleted();
    }

    @Override
    public boolean isUserActionNeeded() {
        return state.isUserActionNeeded();
    }

    @Override
    public List<Book> getBooks() {
        return state.getBooks();
    }
}
