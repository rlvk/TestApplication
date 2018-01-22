package com.rafalwesolowski.testapp;

import com.rafalwesolowski.testapp.mvp.presenters.TodoPresenter;
import com.rafalwesolowski.testapp.mvp.views.TodoView;
import com.rafalwesolowski.testapp.realm.RealmTask;
import com.rafalwesolowski.testapp.realm.RxRealmManager;
import com.rafalwesolowski.testapp.utils.SchedulerProvider;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by rafalwesolowski on 21/01/2018.
 */

@RunWith(MockitoJUnitRunner.class)
public class TodoPresenterTest {

    @Mock private TodoView mMockView;
    @Mock private SchedulerProvider mSchedulerProvider;
    @Mock private RxRealmManager mRxRealmManager;
    private TodoPresenter mPresenter;

    private ArrayList<RealmTask> taskList = new ArrayList<>();

    @Before
    public void setUp() throws Exception {

        mPresenter = new TodoPresenter(mSchedulerProvider, mRxRealmManager);
    }

    @After
    public void tearDown() throws Exception {
        mPresenter.onViewDestroyed();
    }

    @Test
    public void onViewCreated_withNoTasks() {
        // given
        when(mRxRealmManager.getAllTasks()).thenReturn(Single.just(taskList));

        // when
        mPresenter.onViewCreated(mMockView);

        // then
        verify(mMockView).initAdapter(taskList);
        verify(mMockView).refreshView();
    }
}
