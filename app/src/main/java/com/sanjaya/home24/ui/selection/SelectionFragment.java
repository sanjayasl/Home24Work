package com.sanjaya.home24.ui.selection;


import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
//import android.databinding.DataBindingComponent;
//import android.databinding.DataBindingUtil;
import android.databinding.DataBindingComponent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanjaya.home24.R;
//import com.sanjaya.home24.binding.FragmentDataBindingComponent;
//import com.sanjaya.home24.databinding.SelectionFragmentBinding;
import com.sanjaya.home24.binding.FragmentDataBindingComponent;
import com.sanjaya.home24.datamodel.Article;
import com.sanjaya.home24.datamodel.Resource;
import com.sanjaya.home24.di.Injectable;
import com.sanjaya.home24.ui.common.NavigationController;
import com.sanjaya.home24.util.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ArticleViewModel articleViewModel;

    @Inject
    NavigationController navigationController;

//    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
//    @VisibleForTesting
//    AutoClearedValue<SelectionFragmentBinding> binding;

    ImageView imageView;
    TextView textView;

    public SelectionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_selection, container, false);
//        SelectionFragmentBinding dataBinding = DataBindingUtil
//                .inflate(inflater, R.layout.fragment_selection, container, false,
//                        dataBindingComponent);
//        binding = new AutoClearedValue<>(this, dataBinding);
//        return dataBinding.getRoot();
        View rootView = inflater.inflate(R.layout.fragment_selection, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);
        //LiveData<Resource<List<Article>>> articles = articleViewModel.getArticles();
        articleViewModel.getArticles().observe(this, resource -> {
            if(resource != null && resource.data != null){
                for(Article article : resource.data){
                    Log.e("ATG", "Article title : " + article.getTitle());
                    Log.e("ATG", "");
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadArticle(resource.data.get(0));
                    }
                });
            }
        });

    }

    private void initView(View view){
        textView = view.findViewById(R.id.textView);
        imageView = view.findViewById(R.id.item_image);

        view.findViewById(R.id.button_back).setOnClickListener(clickListener);
        view.findViewById(R.id.button_next).setOnClickListener(clickListener);
    }

    private void loadArticle(Article article){
        textView.setText(article.getTitle());

        Glide.with(getActivity())
                .load(article.getMedia().get(0).getUri())
                .into(imageView);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_back:
                    break;
                case R.id.button_next:
                    break;
            }
        }
    };

}
