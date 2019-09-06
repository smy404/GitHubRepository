package com.example.myrepository.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.myrepository.R;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter2<T> extends RecyclerView.Adapter {

    public static final int TYPE_TITLE = 0;
    public static final int TYPE_MOVIE_LIST = 1;

    public final Context mContext;

    private final LayoutInflater mInflater;

    public HomeFragmentAdapter2(Context context) {
        mContext = context;

        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {

        int type = getItemViewType(position);
        switch (type) {
            case TYPE_TITLE:
                return new TitleViewHolder(mInflater.inflate(R.layout.item_movie_title, viewGroup, false));
            case TYPE_MOVIE_LIST:
                return new MovieListViewHolder(mInflater.inflate(R.layout.item_movie_list, viewGroup, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        Object o = datas.get(position);
        if (o instanceof String) {
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.setData(o);
        } else {
            MovieListViewHolder movieListViewHolder = (MovieListViewHolder) viewHolder;
            movieListViewHolder.setData(o);
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    List<T> datas = new ArrayList<>();

    public void setData(List<T> data) {
        if (data != null) {
            datas.clear();
            datas.addAll(data);
        }
        notifyDataSetChanged();

    }

    @Override
    public int getItemViewType(int position) {
        Object o = datas.get(position);
        if (o instanceof String) {
            return TYPE_TITLE;
        } else {
            return TYPE_MOVIE_LIST;
        }

    }

    class TitleViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTvTitle;

        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTitle = itemView.findViewById(R.id.tv_title);
        }

        public void setData(Object o) {
            String title = (String) o;
            mTvTitle.setText(title);

        }
    }

    class MovieListViewHolder extends RecyclerView.ViewHolder {

        private final RecyclerView mRecyclerView;

        public MovieListViewHolder(@NonNull View itemView) {
            super(itemView);
            mRecyclerView = itemView.findViewById(R.id.recycleView);
        }


        public void setData(Object o) {

            List<MovieSubjects> subjects = (List<MovieSubjects>) o;
            MovieListAdapter adapter = new MovieListAdapter(R.layout.item_movie_list_image, subjects);
            mRecyclerView.setAdapter(adapter);

//            GridLayoutManager manager = new GridLayoutManager(mContext,4);
            //设置布局管理器
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
            linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);

//            mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));


            if (mRecyclerView.getItemDecorationCount() ==0){
                mRecyclerView.addItemDecoration(new SpacesItemDecoration(8));
            }
        }


    }


}
