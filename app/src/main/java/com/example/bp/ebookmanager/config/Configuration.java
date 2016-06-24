package com.example.bp.ebookmanager.config;

import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;

/**
 * Ebook Manager
 * Created by bp on 24.06.16.
 */
public interface Configuration {
    WebClientFactory getWebClientFactory();
    UserActionEnabler getUserActionEnabler();
}
