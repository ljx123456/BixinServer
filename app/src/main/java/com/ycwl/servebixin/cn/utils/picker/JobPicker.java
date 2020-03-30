//package com.ycwl.servebixin.cn.utils.picker;
//
//import android.content.Context;
//import android.view.View;
//
//import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
//import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
//import com.bigkoo.pickerview.view.OptionsPickerView;
//
//import java.util.ArrayList;
//
//import com.ycwl.servebixin.cn.R;
//
///**
// * Created by Administrator on 2018/8/12 0012.
// * 用户情感状态
// */
//
//public class JobPicker {
//
//    private JobPickerFace jobPickerFace;
//    private OptionsPickerView jobPickerView;
////    private ArrayList<JobListItem> jobList = new ArrayList<>();
//
//
//    public JobPicker(JobPickerFace jobPickerFace) {
//        this.jobPickerFace = jobPickerFace;
//    }
//
////    public void setJobList(ArrayList<JobListItem> jobList) {
////        this.jobList = jobList;
////    }
//
//    public void jobPicker(Context context) {
//        jobPickerView = new OptionsPickerBuilder(context, new OnOptionsSelectListener() {
//            @Override
//            public void onOptionsSelect(int options1, int option2, int options3, View v) {
////                jobPickerFace.jobPickerData(jobList.get(options1));
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
////        jobPickerView.setPicker(jobList);
//        jobPickerView.show();
//    }
//
//
//    public interface JobPickerFace {
////        void jobPickerData(JobListItem item);
//    }
//}
