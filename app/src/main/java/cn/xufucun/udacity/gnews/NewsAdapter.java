package cn.xufucun.udacity.gnews;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by MayiSenlin on 2017/12/8.
 */

public class NewsAdapter extends ArrayAdapter<News> {

    private int mColorResourceId;

    public NewsAdapter(@NonNull Context context, int resource, @NonNull List<News> objects) {
        super(context, resource, objects);
        mColorResourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;

        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        News news = getItem(position);

        TextView name = (TextView) listItemView.findViewById(R.id.tv_title);
        name.setText(news.getNewsTitle());

        TextView introduction = (TextView) listItemView.findViewById(R.id.tv_author);
        introduction.setText(news.getNewsAuthor());

        View textContainer = listItemView.findViewById(R.id.text_container);
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        textContainer.setBackgroundColor(color);

        return listItemView;
    }
}
