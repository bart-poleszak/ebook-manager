package com.example.bp.ebookmanager.config;

import android.content.Context;

import com.example.bp.ebookmanager.android.config.AndroidConfiguration;
import com.example.bp.ebookmanager.android.config.AndroidConfigurationFactory;

/**
 * ebook-manager
 * Created by bart-poleszak on 30.05.2017.
 */

public class TestConfigurationFactory implements AndroidConfigurationFactory {
    @Override
    public Configuration getConfiguration(Context context) {
        AndroidConfiguration androidConfiguration = new AndroidConfiguration(context);
        return new TestConfiguration(androidConfiguration);
    }
}
