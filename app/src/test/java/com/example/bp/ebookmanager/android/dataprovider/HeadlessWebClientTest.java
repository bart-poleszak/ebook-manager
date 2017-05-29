package com.example.bp.ebookmanager.android.dataprovider;

import android.webkit.CookieManager;

import com.example.bp.ebookmanager.dataprovider.WebClient;
import com.example.bp.ebookmanager.dataprovider.mock.FakeCookieManager;
import com.example.bp.ebookmanager.dataprovider.mock.FakeUrlWrapper;
import com.example.bp.ebookmanager.dataprovider.mock.SynchroniousHeadlessWebClientActionsRunner;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;

import static org.junit.Assert.*;

/**
 * Created by Bartek on 2017-05-28.
 */

public class HeadlessWebClientTest {
    private boolean onLoadingFailedHasBeenCalled;
    private boolean onPageFinishedHasBeenCalled;
    private HeadlessWebClient client;
    private CookieManager cookieManager = new FakeCookieManager();

    @Before
    public void before() {
        onLoadingFailedHasBeenCalled = false;
        onPageFinishedHasBeenCalled = false;
        client = new HeadlessWebClient(new SynchroniousHeadlessWebClientActionsRunner(), cookieManager);
        client.setCallbacks(new WebClient.Callbacks() {
            @Override
            public void onPageFinished(String url, String source) {
                onPageFinishedHasBeenCalled = true;
            }

            @Override
            public void onLoadingFailed(String url) {
                onLoadingFailedHasBeenCalled = true;
            }
        });
    }

    @Test
    public void client_callsOnLoadingFailedCallbackWhenReceivedErrorResponseCode() throws MalformedURLException {
        UrlWrapper fakeUrl = new FakeUrlWrapper(HttpURLConnection.HTTP_FORBIDDEN);
        //when
        client.loadUrl(fakeUrl);
        //then
        assertEquals(true, onLoadingFailedHasBeenCalled);
    }

    @Test
    public void client_callsOnLoadingFailedCallbackWhenFailedToConnect() throws MalformedURLException {
        FakeUrlWrapper fakeUrl = new FakeUrlWrapper(HttpURLConnection.HTTP_OK);
        fakeUrl.setThrowIOExceptionOnConnect(true);
        //when
        client.loadUrl(fakeUrl);
        //then
        assertEquals(true, onLoadingFailedHasBeenCalled);
    }

    @Test
    public void client_callsOnPageFinishedCallbackWhenReceivedOkResponseCode() throws MalformedURLException {
        UrlWrapper fakeUrl = new FakeUrlWrapper(HttpURLConnection.HTTP_OK);
        //when
        client.loadUrl(fakeUrl);
        //then
        assertEquals(true, onPageFinishedHasBeenCalled);
    }
}
