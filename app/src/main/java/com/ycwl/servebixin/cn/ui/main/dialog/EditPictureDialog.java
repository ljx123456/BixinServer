package com.ycwl.servebixin.cn.ui.main.dialog;

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

public class EditPictureDialog extends Dialog {

    private View layoutView;
    private TextView lookBtn;
    private TextView changeBtn;
    private TextView delBtn;
    private TextView dialogOver;
    private View v;
    private int size;
    private EditPictureDialogFace dialogFace;

    public void setDialogFace(EditPictureDialogFace dialogFace) {
        this.dialogFace = dialogFace;
    }

    public EditPictureDialog(@NonNull Context context) {
        super(context, com.muzhi.camerasdk.R.style.MyNewAlertDialog);
        initCameraDialog(context);
    }

    private void initCameraDialog(Context mContext) {
        layoutView = LayoutInflater.from(mContext).inflate(R.layout.pop_edit_picture, null);
        lookBtn = (TextView) layoutView.findViewById(R.id.dialog_look);
        changeBtn = (TextView) layoutView.findViewById(R.id.dialog_change);
        delBtn = (TextView) layoutView.findViewById(R.id.dialog_del);
        v=layoutView.findViewById(R.id.view);
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

    public void showDialog(int size) {
        this.size=size;
        if (size<2){
            delBtn.setVisibility(View.GONE);
            v.setVisibility(View.GONE);
        }else {
            delBtn.setVisibility(View.VISIBLE);
            v.setVisibility(View.VISIBLE);
        }
        show();
    }


    private void setOnClickListener() {
        lookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFace != null) {
                    dialogFace.lookBtn();
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

        changeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFace != null) {
                    dialogFace.changeBtn();
                    dismiss();
                }
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (dialogFace != null) {
                    dialogFace.delBtn();
                    dismiss();
                }
            }
        });
    }

    public interface EditPictureDialogFace {
        void lookBtn();

        void changeBtn();

        void delBtn();
    }
}