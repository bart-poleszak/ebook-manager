package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.WebDataProviderFactory;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class AudiobookEmpikWebDataProviderFactory extends EmpikWebDataProviderFactory {

    private static AudiobookEmpikWebDataProviderFactory inst;

    private AudiobookEmpikWebDataProviderFactory() {

    }

    public static WebDataProviderFactory instance() {
        if (inst == null)
            inst = new AudiobookEmpikWebDataProviderFactory();
        return inst;
    }

    @Override
    protected String getShelfName() {
        return EmpikWebActionContext.AUDIOBOOK_SHELF_NAME;
    }
}
