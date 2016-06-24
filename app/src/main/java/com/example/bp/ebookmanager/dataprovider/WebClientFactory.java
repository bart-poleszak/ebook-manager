package com.example.bp.ebookmanager.dataprovider;

import com.example.bp.ebookmanager.dataprovider.WebClient;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public interface WebClientFactory {
    WebClient getHeadlessClient();
    WebClient getVisualClient();
}
