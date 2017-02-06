package com.li.pro.view.fragment.filetransfer;

import android.os.Build;
import android.os.Environment;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.li.fragmentutils.base.BaseLazySwipFragment;
import com.li.pro.adapter.filetransfer.AdapterFileTransferConnectList;
import com.li.pro.bean.filetransfer.FileInfo;
import com.li.pro.bean.filetransfer.IpPortInfo;
import com.li.pro.view.fragment.filetransfer.filelist.FragmentFileList;
import com.li.utils.IpUtils;
import com.li.utils.LoadFragmentThread;
import com.li.utils.SystemBarHelper;
import com.li.utils.filetransfer.BaseTransfer;
import com.li.utils.filetransfer.CustomBlockingThreadPoolExecutor;
import com.li.utils.filetransfer.SocketReceiver;
import com.li.utils.filetransfer.SocketTcpIpReceiveThread;
import com.li.utils.filetransfer.SocketTransferConfig;
import com.li.utils.filetransfer.SocketUdpIpReceiverThread;
import com.li.utils.filetransfer.SocketUdpIpSenderThread;
import com.li.utils.ui.fablayout.FABRevealLayout;
import com.li.utils.ui.fablayout.OnRevealChangeListener;
import com.li.utils.ui.progressbar.FreshDownloadView;
import com.li.utils.ui.widget.XRecyclerView;

import java.util.concurrent.ExecutorService;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2016/11/1 0001.
 */

public class FragmentFileTransfer
        extends BaseLazySwipFragment
        implements BaseTransfer.OnSenderListenner,//发送文件监听
        View.OnClickListener,
        SocketUdpIpReceiverThread.OnSocketIpReceive,//接受IP信息
        SocketTcpIpReceiveThread.OnTcpIpReceiveConfirmListenner {
    private XRecyclerView xrv_uer_list;
    private FreshDownloadView fdv_progress;
    private Button btn_sendfile;
    private FABRevealLayout fab_reveal_layout;
    private View thisView;

    @Override
    public int ftagmentLayout() {
        return R.layout.layout_fragment_file_trancsfer;
    }

    @Override
    public void initView(View view) {
        //状态栏间隔高度
        SystemBarHelper.setHeightAndPadding(getActivity(), view.findViewById(R.id.detail_toolbar));
        this.thisView = view;
        xrv_uer_list = (XRecyclerView) view.findViewById(R.id.xrv_uer_list);
        fdv_progress = (FreshDownloadView) view.findViewById(R.id.fdv_progress);
        btn_sendfile = (Button) view.findViewById(R.id.btn_sendfile);
        btn_sendfile.setOnClickListener(this);
        fab_reveal_layout = (FABRevealLayout) view.findViewById(R.id.fab_reveal_layout);
        fdv_progress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab_reveal_layout.revealSecondaryView();
            }
        });
        fab_reveal_layout.setOnRevealChangeListener(new OnRevealChangeListener() {
            @Override
            public void onMainViewAppeared(FABRevealLayout fabRevealLayout, View mainView) {
            }

            @Override
            public void onSecondaryViewAppeared(FABRevealLayout fabRevealLayout, View secondaryView) {
                secondaryView.findViewById(R.id.fdv_progress2).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        fabRevealLayout.revealMainView();
                    }
                });
            }
        });


        xrv_uer_list.setLayoutManager(new LinearLayoutManager(getActivity()));
        xrv_uer_list.setHasFixedSize(true);
        adapterFileTransferConnectList = AdapterFileTransferConnectList.newInstance(getActivity(), this);
        xrv_uer_list.setAdapter(adapterFileTransferConnectList);
        startReceive();
        executorService.execute(LoadFragmentThread.newInstance(this, FragmentFileList.newInstance(Environment.getExternalStorageDirectory() + ""), R.id.file_path_list, LoadFragmentThread.FragmentStartMode.LOAD_ROOT));
    }

    private AdapterFileTransferConnectList adapterFileTransferConnectList;

    @Override
    public void onMDetach() {
        super.onMDetach();
        if (null != customBlockingThreadPoolExecutor) {
            customBlockingThreadPoolExecutor.destory();
        }
        socketReceiver.destroy();
        socketUdpIpReceiverThread.destroy();
        socketTcpIpReceiveThread.destroy();
        adapterFileTransferConnectList.getIpPortInfos().clear();
        customBlockingThreadPoolExecutor = null;
        socketReceiver = null;
        socketUdpIpReceiverThread = null;
    }

    private SocketTcpIpReceiveThread socketTcpIpReceiveThread;

    private void startReceive() {
        FileInfo fileInfo = new FileInfo(Environment.getExternalStorageDirectory() + "/111LMW");
        socketReceiver = SocketReceiver.newInstance(SocketTransferConfig.newInstance().setFileInfo(fileInfo).setPort(9999));
        socketReceiver.setOnSenderListenner(this);
        customBlockingThreadPoolExecutor = new CustomBlockingThreadPoolExecutor();
        customBlockingThreadPoolExecutor.init();
        executorService = customBlockingThreadPoolExecutor.getCustomThreadPoolExecutor();
        executorService.execute(socketReceiver);
        executorService.execute(SocketUdpIpSenderThread.newInstance());
        socketUdpIpReceiverThread = SocketUdpIpReceiverThread.newInstance();
        socketUdpIpReceiverThread.setOnSocketIpReceiveListenner(this);
        executorService.execute(socketUdpIpReceiverThread);
        socketTcpIpReceiveThread = SocketTcpIpReceiveThread.newInstance();
        socketTcpIpReceiveThread.setOnTcpIpReceiveConfirmListenner(this);
        executorService.execute(socketTcpIpReceiveThread);
    }

    private CustomBlockingThreadPoolExecutor customBlockingThreadPoolExecutor;
    private SocketReceiver socketReceiver;
    private ExecutorService executorService;
    private SocketUdpIpReceiverThread socketUdpIpReceiverThread;

    @Override
    public String setToolBarTitle() {
        return "";
    }

    @Override
    public boolean isHideActionBar() {
        return false;
    }

    @Override
    public boolean isShowBackArrow() {
        return false;
    }

    @Override
    public int setLeftCornerLogo() {
        return 0;
    }

    @Override
    public void onTransferStart(FileInfo fileInfo) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始传输");
                Toast.makeText(getActivity(), "空文件已经创建完毕等待接收文件流,路径：" + fileInfo.getFilePath() + "文件大小:" + fileInfo.getSize(), Toast.LENGTH_LONG).show();
                fdv_progress.reset();
            }
        });
        System.out.println("开始传输");
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onProgress(FileInfo fileInfo, long totleSize, long currentSize) {
        double rating = (double) currentSize / (double) totleSize * 100;
        System.out.println("总大小: " + totleSize + "   当前进度:" + currentSize + " 百分比" + rating + "%" + currentSize / totleSize * 100);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                fdv_progress.upDateProgress((int) rating);
                if (rating >= 100) {
                    fdv_progress.reset();
                }
            }
        });

    }

    @Override
    public void onSuccess(FileInfo fileInfo) {
        System.out.println("传输完成" + fileInfo.getFilePath());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(thisView,"传输完成,路径：" + fileInfo.getFilePath() + "文件大小:" + fileInfo.getSize(),Snackbar.LENGTH_SHORT).show();
            }
        });
        executorService.shutdown();
        customBlockingThreadPoolExecutor.destory();
        executorService = null;
        customBlockingThreadPoolExecutor = null;
        startReceive();
        fdv_progress.reset();

    }

    @Override
    public void onError(Exception e) {
        System.out.println("传输错误" + e.getMessage());
//        customBlockingThreadPoolExecutor.destory();
//        socketReceiver.destroy();
//        executorService = null;
//        customBlockingThreadPoolExecutor = null;
//        socketReceiver = null;
//        fdv_progress.reset();
//        startReceive();
    }

    boolean flag = false;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sendfile:
                flag = !flag;
                socketReceiver.setSuspend(flag);
                if (flag) {
                    Snackbar.make(thisView,"暂停",Snackbar.LENGTH_SHORT).show();
                    btn_sendfile.setText("暂停");
                } else {
                    Snackbar.make(thisView,"正在运行",Snackbar.LENGTH_SHORT).show();
                    btn_sendfile.setText("正在运行");
                }

                break;
        }
    }

    @Override
    public void onSocketIpReceiveStart() {

    }

    private void updateHostList(IpPortInfo ipPortInfo) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Snackbar.make(thisView,"66666666666" + ipPortInfo,Snackbar.LENGTH_SHORT).show();
                btn_sendfile.setText(ipPortInfo + " ==>");
                if (!IpUtils.getInstance().getLocalHostIp().equals(ipPortInfo.getIpAddress()) && !adapterFileTransferConnectList.isContainer(ipPortInfo)) {
                    adapterFileTransferConnectList.addData(ipPortInfo);
                }
                adapterFileTransferConnectList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSocketIpReceiveGetIp(IpPortInfo ipPortInfo) {
        System.out.println("====回调收到IP：" + ipPortInfo);
        updateHostList(ipPortInfo);
    }


    @Override
    public void OnTcpIpReceiveConfirm(IpPortInfo ipPortInfo) {
        System.out.println("TTTTTTTTTTTTTTTTTTTTTT" + ipPortInfo);
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (!IpUtils.getInstance().getLocalHostIp().equals(ipPortInfo.getIpAddress()) && !adapterFileTransferConnectList.isContainer(ipPortInfo)) {
                    adapterFileTransferConnectList.addData(ipPortInfo);
                }
                adapterFileTransferConnectList.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onSocketIpReceiveSuccess() {
    }

    @Override
    public void onSocketIpReceiveError(Exception e) {
        Snackbar.make(thisView,"对方收到广播后发送响应IP接受失败!",Snackbar.LENGTH_SHORT).show();
        socketTcpIpReceiveThread.destroy();
    }
}
