//package com.ycwl.servebixin.cn.utils.picker;
//
//import android.content.Context;
//import android.view.View;
//
//import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
//import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
//import com.bigkoo.pickerview.view.OptionsPickerView;
//
//import com.ycwl.servebixin.cn.R;
//import cn.yoyo.com.utils.picker.PickerInfo;
//
//import java.util.ArrayList;
//
///**
// * Created by Administrator on 2018/8/12 0012.
// * 用户情感状态
// */
//
//public class EmotionPicker {
//
//    private EmotionPickerFace emotionPickerFace;
//    private OptionsPickerView pvCustomOptions;
//    private ArrayList<PickerInfo> emotionList = new ArrayList<>();
//
//
//    public EmotionPicker(EmotionPickerFace emotionPickerFace) {
//        this.emotionPickerFace = emotionPickerFace;
//        emotionList.add(new PickerInfo("未婚", "1"));
//        emotionList.add(new PickerInfo("已婚", "2"));
//        emotionList.add(new PickerInfo("保密", "3"));
//        emotionList.add(new PickerInfo("离婚", "4"));
//        emotionList.add(new PickerInfo("分居", "5"));
//        emotionList.add(new PickerInfo("不婚", "8"));
//        emotionList.add(new PickerInfo("恋爱中", "6"));
//        emotionList.add(new PickerInfo("同性恋", "9"));
//        emotionList.add(new PickerInfo("刚分手", "10"));
//    }
//
//
//    public void emotionPicker(Context context) {
//        pvCustomOptions = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
//                PickerInfo itemInfo = emotionList.get(options1);
//                emotionPickerFace.emotionPickerData(itemInfo.getName(), itemInfo.getValue());
//            }
//        })
//                .setSubmitColor(context.getResources().getColor(R.color.white))
//                .setCancelColor(context.getResources().getColor(R.color.white))
//                .setTitleBgColor(context.getResources().getColor(R.color.colorPrimary))
//                .setTitleColor(context.getResources().getColor(R.color.white))
//                .setDividerColor(context.getResources().getColor(R.color.colorPrimary))
//                .setTextColorCenter(context.getResources().getColor(R.color.colorPrimary))
//                .isDialog(true)
//                .build();
//        pvCustomOptions.setPicker(emotionList);
//        pvCustomOptions.show();
//    }
//
//
//    public interface EmotionPickerFace {
//        void emotionPickerData(String emotion, String emotionType);
//    }
//}
