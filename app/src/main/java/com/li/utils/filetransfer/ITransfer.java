package com.li.utils.filetransfer;

/**
 * Create by Mingwei Li on 2017 2017-1-4 ����11:09:30
 */
public interface ITransfer {
	void init() throws Exception;

	void parseHeader() throws Exception;

	void parseBody() throws Exception;

	void onSuccess() throws Exception;

}
