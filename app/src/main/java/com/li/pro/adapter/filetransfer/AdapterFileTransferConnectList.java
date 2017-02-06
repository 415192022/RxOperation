package com.li.pro.adapter.filetransfer;

import android.content.Context;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.li.fragmentutils.SupportActivity;
import com.li.fragmentutils.SupportFragment;
import com.li.pro.bean.filetransfer.FileInfo;
import com.li.pro.bean.filetransfer.IpPortInfo;
import com.li.utils.filetransfer.BaseTransfer;
import com.li.utils.filetransfer.CustomBlockingThreadPoolExecutor;
import com.li.utils.filetransfer.SocketSender;
import com.li.utils.filetransfer.SocketTransferConfig;
import com.li.utils.ui.progresslayout.ProgressLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;

import rxop.li.com.rxoperation.R;

/**
 * Created by Mingwei Li on 2017/1/9 0009.
 */

public class AdapterFileTransferConnectList extends RecyclerView.Adapter<AdapterFileTransferConnectList.MyViewHodler> {
    private List<IpPortInfo> ipPortInfos = new ArrayList<IpPortInfo>();
    private Context context;
    private CustomBlockingThreadPoolExecutor customBlockingThreadPoolExecutor;
    private ExecutorService executorService;
    private SupportFragment supportFragment;
    private static volatile AdapterFileTransferConnectList adapterFileTransferConnectLis = null;

    public AdapterFileTransferConnectList(Context context, SupportFragment supportFragment) {
        this.context = context;
        this.supportFragment = supportFragment;
        init();
    }

    public static synchronized AdapterFileTransferConnectList newInstance(Context context, SupportFragment supportFragment) {
        adapterFileTransferConnectLis = new AdapterFileTransferConnectList(context, supportFragment);
        return adapterFileTransferConnectLis;
    }

    public AdapterFileTransferConnectList init() {
        customBlockingThreadPoolExecutor = new CustomBlockingThreadPoolExecutor();
        customBlockingThreadPoolExecutor.init();
        executorService = customBlockingThreadPoolExecutor.getCustomThreadPoolExecutor();
        return this;
    }

    public AdapterFileTransferConnectList addData(IpPortInfo ipPortInfo) {
        this.ipPortInfos.add(ipPortInfo);
        return this;
    }

    public boolean isContainer(IpPortInfo ipPortInfo) {
        boolean flag = false;
        for (IpPortInfo info : ipPortInfos) {
            if (info.getIpAddress().equals(ipPortInfo.getIpAddress())) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    public List<IpPortInfo> getIpPortInfos() {
        return ipPortInfos;
    }


    @Override

    public MyViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_fatagment_c_filetransfer_item, parent, false);
        return new MyViewHodler(view);
    }

    @Override
    public void onBindViewHolder(MyViewHodler holder, int position) {
        if (null == ipPortInfos) {
            return;
        }
        holder.tv_filetransfer_host.setText(ipPortInfos.get(position).getHostName());
        holder.tv_filetransfer_ip.setText(ipPortInfos.get(position).getIpAddress());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocketSender socketSender = SocketSender.newInstance(SocketTransferConfig.newInstance().setIp(ipPortInfos.get(position).getIpAddress()).setPort(9999).setFileInfo(new FileInfo(Environment.getExternalStorageDirectory() + "/temp.mp3")));
                socketSender.setOnSenderListenner(new BaseTransfer.OnSenderListenner() {
                    @Override
                    public void onTransferStart(FileInfo fileInfo) {
                        holder.progressLayout.setMaxProgress(100);
                    }

                    @Override
                    public void onProgress(FileInfo fileInfo, long totleSize, long currentSize) {
                        double rating = (double) currentSize / (double) totleSize * 100;
                        ((SupportActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.progressLayout.setCurrentProgress((int) rating);
                            }
                        });
                        System.out.println("当前:" + rating + "%");
                    }

                    @Override
                    public void onSuccess(FileInfo fileInfo) {
                        ((SupportActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.progressLayout.setCurrentProgress(0);
                            }
                        });

                    }

                    @Override
                    public void onError(Exception e) {
                        ((SupportActivity) context).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                holder.progressLayout.setCurrentProgress(0);
                            }
                        });

                    }
                });
                executorService.execute(socketSender);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ipPortInfos.size();
    }

    public class MyViewHodler extends RecyclerView.ViewHolder {
        public ProgressLayout progressLayout;
        public TextView tv_filetransfer_host;
        public TextView tv_filetransfer_ip;

        public MyViewHodler(View itemView) {
            super(itemView);
            progressLayout = (ProgressLayout) itemView.findViewById(R.id.progressLayout);
            tv_filetransfer_host = (TextView) itemView.findViewById(R.id.tv_filetransfer_host);
            tv_filetransfer_ip = (TextView) itemView.findViewById(R.id.tv_filetransfer_ip);
        }
    }
}
