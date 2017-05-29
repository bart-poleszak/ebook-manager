package com.example.bp.ebookmanager.android.dataprovider;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

/**
 * Created by Bartek on 2017-05-28.
 */

public interface UrlWrapper {
    HttpURLConnection openConnection()  throws IOException;
    String getHost();
}
