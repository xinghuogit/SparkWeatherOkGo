/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.library.common.api;

import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpMethod;
import com.lzy.okgo.model.HttpParams;

import java.lang.reflect.Type;

import io.reactivex.Observable;

/**
 * ================================================
 * 作    者：jeasonlzy（廖子尧）Github地址：https://github.com/jeasonlzy
 * 版    本：1.0
 * 创建日期：16/9/30
 * 描    述：
 * 修订历史：
 * ================================================
 */
public class ServerApi {

    public static Observable<String> getString(HttpMethod method, String header, String url, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("token", header);
        HttpParams params = new HttpParams();
//        params.put("bbb", param);
        return RxUtils.request(method, url, String.class, params, headers);
    }

    public static <T> Observable<T> getData(HttpMethod method, Type type, String url, String header, String param) {
        HttpHeaders headers = new HttpHeaders();
        headers.put("token", header);
        HttpParams params = new HttpParams();
//        params.put("bbb", param);
        return RxUtils.request(method, url, type, params, headers);
    }



//    public static Observable<Response<Bitmap>> getBitmap(String header, String param) {
//        return OkGo.<Bitmap>post(Urls.URL_IMAGE)//
//                .headers("aaa", header)//
//                .params("bbb", param)//
//                .converter(new BitmapConvert())//
//                .adapt(new ObservableResponse<Bitmap>());
//    }
//
//    public static Observable<Response<File>> getFile(String header, String param) {
//        return OkGo.<File>get(Urls.URL_DOWNLOAD)//
//                .headers("aaa", header)//
//                .params("bbb", param)//
//                .converter(new FileConvert())//
//                .adapt(new ObservableResponse<File>());
//    }
}
