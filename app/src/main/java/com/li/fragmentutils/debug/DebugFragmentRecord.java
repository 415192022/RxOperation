package com.li.fragmentutils.debug;

import java.util.List;

/**
 * 为了调试时 查看栈视图
 */
public class DebugFragmentRecord {
    public String fragmentName;
    public List<DebugFragmentRecord> childFragmentRecord;

    public DebugFragmentRecord(String fragmentName, List<DebugFragmentRecord> childFragmentRecord) {
        this.fragmentName = fragmentName;
        this.childFragmentRecord = childFragmentRecord;
    }
}
