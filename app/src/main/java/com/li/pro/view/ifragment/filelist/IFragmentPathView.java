package com.li.pro.view.ifragment.filelist;


import com.li.pro.bean.filetransfer.FileInfo;

/**
 * Created by Administrator on 2016/5/26.
 */
public interface IFragmentPathView {
    void getDirList(FileInfo fileModel);
    void onStartLoadDirList();
    void onCompletLoadDirList();
    void onLoadDirError(String er);
}
