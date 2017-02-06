package com.li.pro.model.filetransfer.filelist;

import com.li.pro.bean.filetransfer.FileInfo;

import java.io.IOException;

import rx.Observable;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface IFragmentPathListInteractor {
    Observable<FileInfo> getDirsList(String path) throws IOException;
}
