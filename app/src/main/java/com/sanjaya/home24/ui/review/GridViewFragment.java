package com.sanjaya.home24.ui.review;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjaya.home24.R;
import com.sanjaya.home24.adapter.GridViewAdapter;
import com.sanjaya.home24.adapter.ListViewAdapter;
import com.sanjaya.home24.di.Injectable;
import com.sanjaya.home24.ui.selection.ArticleViewModel;

import javax.inject.Inject;

public class GridViewFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    //@Inject
    GridViewAdapter gridViewAdapter;

    private ArticleViewModel articleViewModel;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    public GridViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_grid_view, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.grid_view_mode);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new GridLayoutManager(getContext(), 3);
        recyclerView.setLayoutManager(mLayoutManager);

        // recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        // recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        gridViewAdapter = new GridViewAdapter(getContext());
        recyclerView.setAdapter(gridViewAdapter);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);
        articleViewModel.getArticles().observe(this, resource -> {
                    if(resource != null && resource.data != null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                gridViewAdapter.setArticleList(resource.data);
                                gridViewAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }
        );
    }

}
