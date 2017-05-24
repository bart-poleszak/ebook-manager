package com.example.bp.ebookmanager.dataprovider.empik;

import com.example.bp.ebookmanager.dataprovider.html.HTMLScraper;
import com.example.bp.ebookmanager.model.WebBookDetails;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Bartek on 2017-05-22.
 */

interface EmpikFileFormatParser {
    void parse(HTMLScraper scraper, ArrayList<WebBookDetails> detailsList);
}
