package com.rafalwesolowski.testapp;

import com.rafalwesolowski.testapp.api.NetworkService;
import com.rafalwesolowski.testapp.datamodel.SearchResponse;
import com.rafalwesolowski.testapp.mvp.presenters.GithubPresenter;
import com.rafalwesolowski.testapp.mvp.views.GithubView;
import com.rafalwesolowski.testapp.test_utils.TestSchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class GithubPresenterTest {

    @Mock
    private GithubView mMockView;
    @Mock private NetworkService mNetworkService;
    private GithubPresenter mPresenter;

    @Before
    public void setUp() throws Exception {

        mPresenter = new GithubPresenter(new TestSchedulerProvider(), mNetworkService);
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.onViewDestroyed();
    }

    @Test
    public void onViewCreated_initAdapterWithEmptyList() {
        // given

        // when
        mPresenter.onViewCreated(mMockView);

        // then
        verify(mMockView).initAdapter(new ArrayList<>());
    }

    @Test
    public void whenSearchForUsers_AndDontExist_showProgress_HideProgress_AndShowEmptyView() {
        String query = "query";

        SearchResponse emptySearchResponse = mock(SearchResponse.class);

        // given
        when(mNetworkService.getUsers(query)).thenReturn(Single.just(emptySearchResponse));

        // when
        mPresenter.onViewCreated(mMockView);
        mPresenter.searchUser(query);

        // then
        verify(mMockView).showProgress();
        verify(mMockView).hideProgress();
        verify(mMockView).showEmptyView();
    }
}
