package cn.xufucun.udacity.gnews.fragment;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cn.xufucun.udacity.gnews.News;
import cn.xufucun.udacity.gnews.NewsAdapter;
import cn.xufucun.udacity.gnews.NewsLoader;
import cn.xufucun.udacity.gnews.R;

/**
 * Created by xufuc on 2017/12/7.
 */

public class ThemeFragment extends Fragment implements LoaderManager.LoaderCallbacks<List<News>> {

    private static final String TAG = "ThemeFragment";

    private static String ENDPOINT_URL = "https://content.guardianapis.com/search";
    private static final int SEARCH_LOADER_ID = 1;

    private NewsAdapter newsAdapter;
    private TextView emptyTextView;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.content_list,container,false);

        ListView listView = view.findViewById(R.id.list);
        emptyTextView = view.findViewById(R.id.empty_view);
        listView.setEmptyView(emptyTextView);
        newsAdapter = new NewsAdapter(getContext(), R.color.category_1,new ArrayList<News>());
        listView.setAdapter(newsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                News news = newsAdapter.getItem(position);
                Uri uri = Uri.parse(news.getNewsUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(websiteIntent);
            }
        });


        ConnectivityManager connMgr = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getActivity().getLoaderManager();
            loaderManager.initLoader(SEARCH_LOADER_ID, null, this);
        } else {
            View loadingIndicator = view.findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            emptyTextView.setText("无网络链接");
        }


        return view;
    }

    @Override
    public Loader<List<News>> onCreateLoader(int id, Bundle args) {

        Uri baseUri = Uri.parse(ENDPOINT_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("api-key", "c4c4969c-cdf9-49f0-8da0-613a72b77002");
        uriBuilder.appendQueryParameter("format", "json");
        uriBuilder.appendQueryParameter("q", "json");

        Log.d(TAG, "onCreateLoader: "+uriBuilder.toString());

        return new NewsLoader(getContext(), uriBuilder.toString());
    }


    @Override
    public void onLoadFinished(Loader<List<News>> loader, List<News> data) {
        View loadingIndicator = view.findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
        emptyTextView.setText("无数据");
        newsAdapter.clear();
        if (data != null && !data.isEmpty()) {
            newsAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<News>> loader) {
        newsAdapter.clear();
    }
}
