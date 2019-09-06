package com.example.myrepository.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.myrepository.R;
import com.example.myrepository.mvp.model.entity.MovieSubjects;
import com.example.myrepository.utils.LogUtil;

import java.util.List;


public class HomeFragmentAdapter3 extends BaseQuickAdapter<Object, BaseViewHolder> {

    public HomeFragmentAdapter3() {
        super(R.layout.item_movie_horizontal_list);
    }

    @Override
    protected void convert(BaseViewHolder helper, Object item) {


        MovieSubjects item1 = (MovieSubjects) item;




        LogUtil.d("");


    }
}
