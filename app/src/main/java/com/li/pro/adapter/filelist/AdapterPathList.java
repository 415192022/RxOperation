package com.li.pro.adapter.filelist;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.li.fragmentutils.SupportFragment;
import com.li.pro.bean.filetransfer.FileInfo;
import com.li.pro.view.fragment.filetransfer.filelist.FragmentFileList;
import com.li.utils.fileutils.FileUtils;
import com.li.utils.ui.ripplelayout.MaterialRippleLayout;
import com.li.utils.ui.smoothcheckbox.SmoothCheckBox;
import com.nineoldandroids.animation.TypeEvaluator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;
import java.util.List;

import rxop.li.com.rxoperation.R;


/**
 * Created by Administrator on 2016/5/25.
 */
public class AdapterPathList extends RecyclerView.Adapter<AdapterPathList.ViewHolder> {
    private Context context = null;
    List<FileInfo> list = new ArrayList<>();
    List<Boolean> cbState = null;
    private static AdapterPathList adapterPathList = null;
    private SupportFragment supportFragment;

    /**
     * 构造器
     *
     * @param context
     */
    protected AdapterPathList(Context context, SupportFragment supportFragment) {
        this.supportFragment = supportFragment;
        this.context = context;
    }

    private AdapterPathList() {
    }

    /**
     * 单例模式
     *
     * @param context
     * @return
     */
    public static AdapterPathList getInstance(Context context, SupportFragment supportFragment) {
//        if (null == adapterPathList) {
//            synchronized (AdapterPathList.class) {
//                if (null == adapterPathList)
        adapterPathList = new AdapterPathList(context, supportFragment);
//            }
//        }
        return adapterPathList;
    }

    /**
     * 添加一个Dir对象
     */
    public void addDatas(FileInfo fileInfo) {
        list.add(fileInfo);
        cbState = new ArrayList<Boolean>();
        for (int i = 0; i < list.size(); i++) {
            cbState.add(false);
        }

    }

    /**
     * 清除所有数据
     */
    public void clearAllDatas() {
        list.clear();
    }

    public List<FileInfo> getDatas() {
        return this.list;
    }

    public List<Boolean> getCbState() {
        return cbState;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_fragment_filelist_item, parent, false);
        return new ViewHolder(view);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        //加载Item中的数据
        holder.tv_size.setText(list.get(position).getSize() + "");
        holder.ib_dir_properties.setColorFilter(Color.parseColor("#FF4081"));
        holder.tv_date.setText(FileUtils.getInstance().getFormatDate(list.get(position).getDate()) + "");
        holder.tv_path.setText(list.get(position).getName() + "");

        //每个item的菜单
        holder.ib_dir_properties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                CommonUtils.showPopupMenu(context, v, new PopupMenu.OnMenuItemClickListener() {
//                    @Override
//                    public boolean onMenuItemClick(MenuItem item) {
//                        switch (item.getItemId()) {
//                            case R.id.menu_copy:
//
//                                break;
//                            case R.id.menu_cut:
//                                break;
//                            case R.id.menu_properties:
//                                break;
//                            case R.id.menu_rename:
//                                break;
//                            case R.id.menu_delete:
//                                if (FileUtils.getInstance().deleteQuietly(new File(list.get(position).getAllPath())))
//                                    Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT);
//                                break;
//                            case R.id.menu_share:
//                                break;
//                            case R.id.menu_favorite:
//                                break;
//                        }
//                        return false;
//                    }
//                });
            }
        });
        //复选按钮监听
        holder.scb_item_fragment3_checked.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                if (isChecked) {
                    holder.scb_item_fragment3_checked.clearText();
                    holder.scb_item_fragment3_checked.clearImageBitmap();
                    getCbState().set(position, isChecked);
                } else {
                    if (list.get(position).isDirectory()) {
                        holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                        getCbState().set(position, isChecked);
                    } else {
                        holder.scb_item_fragment3_checked.setText(list.get(position).getFileType());
                        getCbState().set(position, isChecked);
                    }

                }
            }
        });


        //如果复选状态为被选中状态
        if (getCbState().get(position)) {
            //设置按钮为被选中状态
            holder.scb_item_fragment3_checked.setChecked(true);
            holder.scb_item_fragment3_checked.clearImageBitmap();
            holder.scb_item_fragment3_checked.clearText();
        } else {
            //如果不是复选状态
            //设置按钮为没有被选中状态
            holder.scb_item_fragment3_checked.setChecked(false);
            //如果是目录
            if (list.get(position).isDirectory()) {
                //设置背景图片为文件夹图片
                holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                //清除文字
                holder.scb_item_fragment3_checked.clearText();
            } else {
                //如果是文件
//                if (ApiConstants.Suffix.SUFFIX_JPG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_image));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_OOG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_audio_am));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_PDF.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_pdf));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_PNG.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_image));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_TXT.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_text_am));
//                    holder.scb_item_fragment3_checked.clearText();
//                } else if (ApiConstants.Suffix.SUFFIX_XML.equals(list.get(position).getSuffix())) {
//                    holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_doc_codes));
//                    holder.scb_item_fragment3_checked.clearText();
//                }
//                else {
                //设置复选按钮为文件后缀
                holder.scb_item_fragment3_checked.setText(list.get(position).getFileType());
                //为防止叠加效果，清除Bitmap
                holder.scb_item_fragment3_checked.clearImageBitmap();
//                }

            }
        }


        //真格Item的长按事件
        holder.rl_item_fragment3_root.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (getCbState().get(position)) {
                    getCbState().set(position, true);
                    holder.scb_item_fragment3_checked.setChecked(false, true);
                } else {
                    getCbState().set(position, true);
                    holder.scb_item_fragment3_checked.setChecked(true, true);
                }
                return true;
            }
        });
        //整个Item的点击事件
        holder.rl_item_fragment3_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.scb_item_fragment3_checked.clearText();
                holder.scb_item_fragment3_checked.clearImageBitmap();
                if (isMultiple()) {
                    //在判断复选框是否是被选状态
                    if (getCbState().get(position)) {
                        //如果是被选状态
                        if (list.get(position).isDirectory()) {
                            holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                            getCbState().set(position, false);
                            holder.scb_item_fragment3_checked.setChecked(false, true);
                        } else {
                            holder.scb_item_fragment3_checked.setText(list.get(position).getFileType());
                            getCbState().set(position, false);
                            holder.scb_item_fragment3_checked.setChecked(false, true);
                        }

                    } else {
                        //如果不是被选状态
                        if (list.get(position).isDirectory()) {
                            holder.scb_item_fragment3_checked.setImageBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_grid_folder_new));
                            getCbState().set(position, true);
                            holder.scb_item_fragment3_checked.setChecked(true, true);
                        } else {
                            holder.scb_item_fragment3_checked.clearText();
                            getCbState().set(position, true);
                            holder.scb_item_fragment3_checked.setChecked(true, true);
                        }

                    }
                } else {
                    if (list.get(position).isDirectory()) {
                        supportFragment.start(FragmentFileList.newInstance(list.get(position)
                                .getFilePath()));
//                        FragmentPathPresenter
//                                .getInstance((IFragmentPathView) supportFragment)
//                                .getDirsList(list.get(position)
//                                        .getFilePath());
                    } else {
                    }
                }

            }
        });
    }

    /**
     * 判断现在是否为多选状态
     *
     * @return
     */
    public boolean isMultiple() {
        for (boolean b : getCbState()) {
            if (b)
                return true;
        }
        return false;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        //        CircleImageView civ_icon;
//        TextView tv_path;
        SmoothCheckBox scb_item_fragment3_checked;
        public TextView tv_path;
        public TextView tv_date;
        public TextView tv_size;
        public ImageButton ib_dir_properties;
        MaterialRippleLayout rl_item_fragment3_root;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_path = (TextView) itemView.findViewById(R.id.tv_path);
            tv_date = (TextView) itemView.findViewById(R.id.tv_date);
            tv_size = (TextView) itemView.findViewById(R.id.tv_size);
            scb_item_fragment3_checked = (SmoothCheckBox) itemView.findViewById(R.id.scb_item_fragment3_checked);
            rl_item_fragment3_root = (MaterialRippleLayout) itemView.findViewById(R.id.rl_item_fragment3_root);
            ib_dir_properties = (ImageButton) itemView.findViewById(R.id.ib_dir_properties);
        }
    }


    /**
     * 抛物线
     *
     * @param view
     */
    public void paowuxian(final View view) {
        ValueAnimator valueAnimator = new ValueAnimator();
        valueAnimator.setDuration(500);
        valueAnimator.setObjectValues(new PointF(0, 0));
        valueAnimator.setInterpolator(new AccelerateInterpolator());
        valueAnimator.setEvaluator(new TypeEvaluator<PointF>() {
            // fraction = t / duration
            @Override
            public PointF evaluate(float fraction, PointF startValue,
                                   PointF endValue) {
                // x方向200px/s ，则y方向0.5 * 10 * t
                PointF point = new PointF();
                point.x = 200 * fraction * 3;
                point.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return point;
            }
        });

        valueAnimator.start();
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF point = (PointF) animation.getAnimatedValue();
//                view.setX(point.x);
//                view.setY(point.y);

                ImageView imageView = new ImageView(context);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(layoutParams);
                imageView.setImageResource(R.drawable.ic_grid_folder_new);

                imageView.setX(point.x);
                imageView.setY(point.y);

            }
        });
    }
}
