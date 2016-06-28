package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.WebDataProviderFactory;

/**
 * Ebook Manager
 * Created by bp on 28.06.16.
 */
public class EbookEmpikWebDataProviderFactory extends EmpikWebDataProviderFactory {
    private static EbookEmpikWebDataProviderFactory inst;

    private EbookEmpikWebDataProviderFactory() {

    }

    public static WebDataProviderFactory instance() {
        if (inst == null)
            inst = new EbookEmpikWebDataProviderFactory();
        return inst;
    }

    @Override
    protected String getShelfName() {
        return EmpikWebActionContext.EBOOK_SHELF_NAME;
    }
}
