package com.ycwl.servebixin.cn.ui.order.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ycwl.servebixin.cn.R;

public class SelectMapDialog extends Dialog {

    private View layoutView;
    private TextView gaodeBtn;
    private TextView baiduBtn;
    private TextView dialogOver;
    private SelectMapDialogFace dialogFace;

    public void setDialogFace(SelectMapDialogFace dialogFace) {
        this.dialogFace = dialogFace;
    }

    public SelectMapDialog(@NonNull Context context) {
        super(context, R.style.MyNewAlertDialog);
        initCameraDialog(context);
    }

    private void initCameraDialog(Context mContext) {
        layoutView = LayoutInflater.from(mContext).inflate(R.layout.dialog_select_map, null);
        gaodeBtn = (TextView) layoutView.findViewById(R.id.dialog_gaode);
        baiduBtn = (TextView) layoutView.findViewById(R.id.dialog_baidu);
        dialogOver = (TextView) layoutView.findViewById(R.id.dialogOver);
        setContentView(layoutView);
        initDialogWindow();
        setOnClickListener();
    }

    private void initDialogWindow() {
        Window window = getWindow();
        assert window != null;
        window.setGravity(Gravity.BOTTOM);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
    }

    public void showDialog() {
        show();
    }


    private void setOnClickListener() {
        gaodeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFace != null) {
                    dialogFace.gaodeBtn();
                    dismiss();
                }
            }
        });

        dialogOver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        baiduBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFace != null) {
                    dialogFace.baiduBtn();
                    dismiss();
                }
            }
        });
    }

    public interface SelectMapDialogFace {
        void gaodeBtn();

        void baiduBtn();
    }
}
