package com.yazanjayousi.nytimesmostpopulararticles.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.yazanjayousi.nytimesmostpopulararticles.R;
import com.yazanjayousi.nytimesmostpopulararticles.adapters.ArticleAdapter;
import com.yazanjayousi.nytimesmostpopulararticles.utils.Constants;

public class DetailsActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    public int articlePosition,articlesListSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        initViews();
        getArticlesDetails();

    }


    private void initViews() {
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ImageButton btn_toolbar_back=findViewById(R.id.btn_toolbar_back);
        btn_toolbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        // Create the adapter that will return a fragment for each of the three
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = findViewById(R.id.container);
    }

    private void getArticlesDetails() {
        Bundle bundle=getIntent().getBundleExtra(Constants.Key);
        articlePosition=bundle.getInt(Constants.PositionKey,0);
        articlesListSize=bundle.getInt(Constants.ListSizeKey,1);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(articlePosition);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        //  private static final String ARG_SECTION_NUMBER = "section_number";
        ImageView imageView;
        TextView tvTitle,tvPDate,tvSection,tvByLine;
        TextView tvAbstract,tvCaption,tvUrl;

        public PlaceholderFragment() {
        }
        private  void init(View rootView) {
            imageView=rootView.findViewById(R.id.imgArticle);
            tvTitle=rootView.findViewById(R.id.tvTitle);
            tvPDate=rootView.findViewById(R.id.tvArticlePublishedDate);
            tvSection=rootView.findViewById(R.id.tvArticleSection);
            tvByLine=rootView.findViewById(R.id.tvArticleByLine);
            tvAbstract=rootView.findViewById(R.id.tvAbstract);
            tvCaption=rootView.findViewById(R.id.tvImgCaption);
            tvUrl=rootView.findViewById(R.id.tvUrl);


        }
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(Constants.PositionKey, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.loading)
                .error(R.drawable.errorloading);
        @SuppressLint("SetTextI18n")
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_details, container, false);
            init(rootView);
            tvTitle.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getTitle());
            tvPDate.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getPublishedDate());
            tvSection.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getSection());
            tvAbstract.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).get_abstract());
            tvByLine.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getByline());
            tvCaption.setText(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getCaption());
            tvUrl.setText(getString(R.string.forDetails) + " " + ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getUrl());
            tvUrl.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent openURL =new Intent(android.content.Intent.ACTION_VIEW);
                    openURL.setData(Uri.parse(ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getUrl()));
                    startActivity(openURL);
                }
            });
            String imgUrl=ArticleAdapter.articlesArrayList.get(getArguments().getInt(Constants.PositionKey)).getImageUrl();
            Glide.with(getContext())
                    .load(imgUrl).apply(options)
                    .into(imageView);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance( position);
        }

        @Override
        public int getCount() {
            // Show total pages.
            return articlesListSize;
        }
    }
}
