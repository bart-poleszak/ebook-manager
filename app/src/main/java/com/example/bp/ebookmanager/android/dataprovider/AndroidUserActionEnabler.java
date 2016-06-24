package com.example.bp.ebookmanager.android.dataprovider;

import com.example.bp.ebookmanager.config.ConfigManager;
import com.example.bp.ebookmanager.dataprovider.WebClientFactory;
import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderStrategy;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class AndroidUserActionEnabler implements UserActionEnabler {

    @Override
    public void enableWebUserAction(WebDataProviderStrategy strategy) {
        WebClientFactory webClientFactory = ConfigManager.get().getWebClientFactory();
        strategy.changeResolverWebClient(webClientFactory.getVisualClient());
    }
}
