package com.sanjaya.home24.ui.review;


import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sanjaya.home24.R;
import com.sanjaya.home24.adapter.ListViewAdapter;
import com.sanjaya.home24.datamodel.Article;
import com.sanjaya.home24.di.Injectable;
import com.sanjaya.home24.ui.selection.ArticleViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListViewFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    //@Inject
    ListViewAdapter listViewAdapter;

    private ArticleViewModel articleViewModel;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;

    public ListViewFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_list_view, container, false);
        initView(rootView);
        return rootView;
    }

    private void initView(View view){
        recyclerView = view.findViewById(R.id.list_view_mode);
        recyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listViewAdapter = new ListViewAdapter(getContext());
        recyclerView.setAdapter(listViewAdapter);
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
                            listViewAdapter.setArticleList(resource.data);
                            listViewAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        );
    }

}
