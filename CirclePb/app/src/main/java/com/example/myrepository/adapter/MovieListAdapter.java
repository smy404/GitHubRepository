package com.example.myrepository.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myrepository.R;
import com.example.myrepository.mvp.model.entity.MovieSubjects;

import java.util.List;

public class MovieListAdapter extends BaseQuickAdapter<MovieSubjects, BaseViewHolder> {

    public MovieListAdapter(int layoutResId, @Nullable List<MovieSubjects> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MovieSubjects item) {
        Glide.with(mContext).load(item.getImages().getMedium()).into((ImageView) helper.getView(R.id.iv));
    }
}
