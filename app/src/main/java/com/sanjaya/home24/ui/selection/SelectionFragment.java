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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.sanjaya.home24.MainActivity;
import com.sanjaya.home24.R;
//import com.sanjaya.home24.binding.FragmentDataBindingComponent;
//import com.sanjaya.home24.databinding.SelectionFragmentBinding;
import com.sanjaya.home24.binding.FragmentDataBindingComponent;
import com.sanjaya.home24.constant.AppValue;
import com.sanjaya.home24.datamodel.Article;
import com.sanjaya.home24.datamodel.Resource;
import com.sanjaya.home24.di.Injectable;
import com.sanjaya.home24.ui.common.NavigationController;
import com.sanjaya.home24.ui.common.SharedPeferenceController;
import com.sanjaya.home24.util.AutoClearedValue;

import java.util.List;

import javax.inject.Inject;

import static com.sanjaya.home24.constant.AppValue.COMPLETED_IMG;
import static com.sanjaya.home24.constant.AppValue.PREF_ARTICAL_COUNT;
import static com.sanjaya.home24.constant.AppValue.PREF_ARTICAL_RATED;

/**
 * A simple {@link Fragment} subclass.
 */
public class SelectionFragment extends Fragment implements Injectable {

    @Inject
    ViewModelProvider.Factory viewModelFactory;

    private ArticleViewModel articleViewModel;

    @Inject
    NavigationController navigationController;

    @Inject
    SharedPeferenceController sharedPeferenceController;

//    DataBindingComponent dataBindingComponent = new FragmentDataBindingComponent(this);
//    @VisibleForTesting
//    AutoClearedValue<SelectionFragmentBinding> binding;

    ImageView imageView;
    TextView textView, rateCount, buttonNext;
    RadioButton likeButton, dislikeButton;
    ImageButton reviewButton;
    RelativeLayout buttonPanel;
    ProgressBar progressBar;
    RadioGroup radioGroup;

    public SelectionFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_selection, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        progressBar.setVisibility(View.VISIBLE);
        buttonPanel.setVisibility(View.INVISIBLE);
        articleViewModel = ViewModelProviders.of(this, viewModelFactory).get(ArticleViewModel.class);
        articleViewModel.getArticles().observe(this, resource -> {
            if(resource != null && resource.data != null){

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        progressBar.setVisibility(View.INVISIBLE);
                        if(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) < 10) {
                            setRatedCount(resource.data);
                            loadArticle(resource.data.get(
                                    sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT)));
                        } else {
                            // TODO
                            loadCompleted(COMPLETED_IMG, "Completed");
                        }
                    }
                });
            }
        });

    }

    private void initView(View view){
        textView = view.findViewById(R.id.title);
        rateCount = view.findViewById(R.id.rate_count);
        imageView = view.findViewById(R.id.item_image);
        likeButton = view.findViewById(R.id.button_like);
        dislikeButton = view.findViewById(R.id.button_dislike);
        reviewButton = view.findViewById(R.id.button_review);
        buttonPanel = view.findViewById(R.id.button_panel);
        progressBar = view.findViewById(R.id.progressBar);
        radioGroup = view.findViewById(R.id.button_group);
        buttonNext = view.findViewById(R.id.button_next);

        navigationController.enableUpNavigation(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) > 0);
        view.findViewById(R.id.button_back).setEnabled(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) > 0);
        view.findViewById(R.id.button_back).setOnClickListener(clickListener);
        view.findViewById(R.id.button_next).setOnClickListener(clickListener);
        view.findViewById(R.id.button_review).setOnClickListener(clickListener);
    }

    private void loadArticle(Article article){
        textView.setText(article.getTitle());
        likeButton.setChecked(article.isLike());
        dislikeButton.setChecked(article.isDislike());
        buttonPanel.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
        radioGroup.setOnCheckedChangeListener(selectListener);

        Glide.with(getActivity())
                .load(article.getMedia().get(0).getUri())
                .into(imageView);
    }

    private void setRatedCount(List<Article> articles){
        int count = 0;
        for(Article article : articles){
            if(article.isLike() || article.isDislike())
                count += 1;
        }

        rateCount.setText(String.valueOf(count) + "/10");
        buttonNext.setEnabled(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) < count
                || sharedPeferenceController.getBooleanValue(PREF_ARTICAL_RATED));
        reviewButton.setEnabled(false);
        if(count == 10){
            reviewButton.setEnabled(true);
            sharedPeferenceController.saveBooleanValue(PREF_ARTICAL_RATED, true);
        }
    }

    private void loadCompleted(String img, String title){
        textView.setText(title);
        buttonPanel.setVisibility(View.INVISIBLE);
        buttonNext.setVisibility(View.INVISIBLE);

        Glide.with(getActivity())
                .load(img)
                .into(imageView);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button_back:
                    goToBack();
                    break;
                case R.id.button_next:
                    goToNext();
                    break;
                case R.id.button_review:
                    navigationController.navigationToReview();
                    break;
            }
        }
    };

    private void goToBack(){
        Log.e("TAG", "back button pressed!");
        int currrentVal = sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT);
        sharedPeferenceController.saveIntegerValue(PREF_ARTICAL_COUNT, currrentVal - 1);
        navigationController.navigationToSelection();
    }

    private void goToNext(){
        Log.e("TAG", "next button pressed!");
        if(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT) < 10)
            moveToNext(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT));
    }

    private void moveToNext(int currrentVal){
        sharedPeferenceController.saveIntegerValue(PREF_ARTICAL_COUNT, currrentVal + 1);
        navigationController.navigationToSelection();
    }

    private RadioGroup.OnCheckedChangeListener selectListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.button_like:
                    articleViewModel.updateArticleWithLike(
                            sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT), likeButton.isChecked());
                    if(!sharedPeferenceController.getBooleanValue(PREF_ARTICAL_RATED))
                        moveToNext(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT));
                    break;
                case R.id.button_dislike:
                    articleViewModel.updateArticleWithDislike(
                            sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT), dislikeButton.isChecked());
                    if(!sharedPeferenceController.getBooleanValue(PREF_ARTICAL_RATED))
                        moveToNext(sharedPeferenceController.getIntegerValue(PREF_ARTICAL_COUNT));
                    break;
            }
        }
    };

}
