package com.example.learnenglish;

import com.example.learnenglish.Models.ApiResponse;

public interface OnFetchDataListener {
    void onFetchData(ApiResponse apiResponse,String message);
    void onError(String message);
}
