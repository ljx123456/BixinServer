package com.ycwl.servebixin.cn.ui.message.util;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.ImageView;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;
import com.bigkoo.convenientbanner.holder.Holder;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.ycwl.servebixin.cn.R;
import com.ycwl.servebixin.cn.ui.image.ImageBannerInfo;
import com.ycwl.servebixin.cn.utils.image.ImageLoad;
import com.ycwl.servebixin.cn.utils.intent.intentUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BannerUtils {

    public static void setBanner(ConvenientBanner banner, ArrayList<ImageBannerInfo> list) {

        banner.setPages(new CBViewHolderCreator() {
            @Override
            public Holder createHolder(View itemView) {
                return new LocalImageHolderView(itemView);
            }

            @Override
            public int getLayoutId() {
                return R.layout.item_image;
            }
        }, list)
                .setPageIndicator(new int[]{R.drawable.gray_circle, R.drawable.white_circle})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.ALIGN_PARENT_LEFT)
                .startTurning(4000)
                .setOnItemClickListener(new OnItemClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onItemClick(int position) {
                        if (list.get(position).isAddButton()) {
                            intentUtils.INSTANCE.intentBanner(list.get(position).getTitle(),list.get(position).getUrl());
                        } else {
                            List imagelist = new ArrayList<ImageBannerInfo>();
                            list.forEach(new Consumer<ImageBannerInfo>() {
                                @Override
                                public void accept(ImageBannerInfo imageInfo) {
                                    imagelist.add(new ImageBannerInfo(imageInfo.getImageUrl(), imageInfo.isAddButton(), imageInfo.getImageId(),imageInfo.getUrl(),imageInfo.getTitle(),imageInfo.getDescription()));
                                }
                            });
//                            intentUtils.INSTANCE.intentImage(false, (ArrayList<ImageInfo>) imagelist, position);
                        }

                    }
                })
                .setCanLoop(true);
    }


    public static class LocalImageHolderView extends Holder<ImageBannerInfo> {
        private ImageView imageView;
        private ImageView textView;

        public LocalImageHolderView(View itemView) {
            super(itemView);
        }


        @Override
        protected void initView(View itemView) {
            imageView = itemView.findViewById(R.id.itemImage);
            textView = itemView.findViewById(R.id.textView);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }

        @Override
        public void updateUI(ImageBannerInfo data) {
            //1视频 2图片
            if (data.isAddButton()) {
                textView.setVisibility(View.GONE);
            } else {
                if (data.getImageId() == 1) {
                    textView.setVisibility(View.VISIBLE);
                } else {
                    textView.setVisibility(View.GONE);
                }
            }

            ImageLoad.INSTANCE.setImage(data.getImageUrl(), imageView);
        }
    }
}
