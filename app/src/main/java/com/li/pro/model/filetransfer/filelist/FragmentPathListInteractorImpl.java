package com.li.pro.model.filetransfer.filelist;


import com.li.pro.bean.filetransfer.FileInfo;
import com.li.utils.fileutils.FileUtils;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/5/26.
 */
public class FragmentPathListInteractorImpl implements IFragmentPathListInteractor {
    @Override
    public Observable<FileInfo> getDirsList(final String path) {
        return Observable
                .from(
                        FileUtils.getInstance()
                                .getDirBelowPath(path)
                )
                .observeOn(
                        Schedulers.newThread()
                )
                .subscribeOn(
                        Schedulers.newThread()
                );
    }
}
