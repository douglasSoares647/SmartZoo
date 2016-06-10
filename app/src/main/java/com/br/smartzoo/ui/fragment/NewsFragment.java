package com.br.smartzoo.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.smartzoo.R;
import com.br.smartzoo.model.business.NewsFeedBusiness;
import com.br.smartzoo.model.entity.Animal;
import com.br.smartzoo.model.entity.New;
import com.br.smartzoo.model.interfaces.OnNewClick;
import com.br.smartzoo.model.interfaces.OnNewFeedUpdate;
import com.br.smartzoo.presenter.NewsPresenter;
import com.br.smartzoo.ui.activity.MainActivity;
import com.br.smartzoo.ui.adapter.AnimalListAdapter;
import com.br.smartzoo.ui.adapter.DividerItemDecoration;
import com.br.smartzoo.ui.adapter.NewListAdapter;
import com.br.smartzoo.ui.adapter.VerticalSpaceItemDecoration;
import com.br.smartzoo.ui.view.NewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adenilson on 05/06/16.
 */
public class NewsFragment extends Fragment implements NewsView, OnNewClick, OnNewFeedUpdate {
    private static final int VERTICAL_SPACE = 30;
    private NewsPresenter mPresenter;
    private RecyclerView mRecyclerViewNews;
    private NewListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        bindFragmentToBusiness();
        bindPresenter();
        bindRecyclerViewNews(view);
        bindToolbarName();
        loadNews();

        return view;
    }

    private void bindToolbarName() {
        ((MainActivity)getActivity()).changeToolBarText(getString(R.string.option_news));
    }

    private void bindFragmentToBusiness() {
        NewsFeedBusiness.mOnNewFeedUpdate = this;
    }

    private void loadNews() {
        mPresenter.loadNews();
    }

    private void bindRecyclerViewNews(View view) {
        mRecyclerViewNews = (RecyclerView) view.findViewById(R.id.recycler_view_news);
        mAdapter = new NewListAdapter(getActivity(), new ArrayList<New>());
        mAdapter.addOnNewClick(this);
        LinearLayoutManager llManager =
                new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mRecyclerViewNews.setLayoutManager(llManager);
        mRecyclerViewNews.addItemDecoration(new DividerItemDecoration(getContext()
                , R.drawable.divider_recycler_view));
        mRecyclerViewNews.addItemDecoration(new VerticalSpaceItemDecoration(VERTICAL_SPACE));
        mRecyclerViewNews.setAdapter(mAdapter);
    }

    private void bindPresenter() {
        mPresenter = new NewsPresenter(getActivity());
        mPresenter.attachView(this);
    }

    @Override
    public void newClicked(String fragment) {
        mPresenter.startTransaction(fragment);
    }

    @Override
    public void onLoadNews(List<New> news) {
        mAdapter.setNewList(news);
    }

    @Override
    public void showSnackBar(String message) {
        ((MainActivity) getActivity()).showSnackBar(message);
    }

    @Override
    public void update(New news) {
        NewListAdapter adapter = (NewListAdapter) mRecyclerViewNews.getAdapter();
        adapter.addNewToList(news);
        mRecyclerViewNews.scrollToPosition(0);
    }


}
