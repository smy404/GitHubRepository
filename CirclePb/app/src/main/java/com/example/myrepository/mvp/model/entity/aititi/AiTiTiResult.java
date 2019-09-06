package com.example.myrepository.mvp.model.entity.aititi;

import java.util.List;

public class AiTiTiResult<T> {

    private List<T> banner;
    private List<T> results;


    public List<T> getBanner() {
        return banner;
    }

    public List<T> getResults() {
        return results;
    }
}
