package com.example.bp.ebookmanager.dataprovider.android;

import android.content.Context;

import com.example.bp.ebookmanager.dataprovider.UserActionEnabler;
import com.example.bp.ebookmanager.dataprovider.WebDataProviderStrategy;

/**
 * Ebook Manager
 * Created by bp on 11.06.16.
 */
public class AndroidUserActionEnabler implements UserActionEnabler {
    Context context;

    public AndroidUserActionEnabler(Context context) {
        this.context = context;
    }

    @Override
    public void enableWebUserAction(WebDataProviderStrategy strategy) {
        strategy.changeResolverWebClient(new VisibleWebClient(context));
    }
}
