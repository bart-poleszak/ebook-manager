package com.example.bp.ebookmanager.android.config;

import android.content.Context;

import com.example.bp.ebookmanager.config.Configuration;

/**
 * ebook-manager
 * Created by bart-poleszak on 30.05.2017.
 */

public class AndroidConfigurationFactoryImpl implements AndroidConfigurationFactory {
    @Override
    public Configuration getConfiguration(Context context) {
        return new AndroidConfiguration(context);
    }
}
