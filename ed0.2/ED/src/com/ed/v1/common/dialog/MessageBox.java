package com.ed.v1.common.dialog;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.ed.v1.R;

public abstract class MessageBox {

    public static void show(final MessageBox messageBox) {

        final BaseDialog dialog = new BaseDialog(messageBox.getContext());

        dialog.setTitle(messageBox.getTitle());

        View contentView = messageBox.getContentView();
        if (contentView == null) {
            dialog.setMessage(messageBox.getMessage());
        } else {
            dialog.setContentViewNoPadding(contentView);
        }

        String ok = messageBox.getOKButtonText();
        if (ok != null) {
            dialog.setPositiveButton(ok, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    messageBox.onOK();
                }
            });
        }

        String cancel = messageBox.getCancelButtonText();
        if (cancel != null) {
            dialog.setNegativeButton(cancel, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    messageBox.onCancel();
                }
            });
        }
        String retry = messageBox.getRetryButtonText();
        if (retry != null) {
            dialog.setNeutralButton(retry, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    messageBox.onRetry();
                }
            });
        }

        dialog.show();
    }

    private Context context;
    private Drawable icon;
    private String title;
    private View contentView;
    private String message;

    public MessageBox(Context context, Drawable icon, String title, View contentView) {
        this.context = context;
        this.icon = icon;
        this.title = title;
        this.contentView = contentView;
    }

    public MessageBox(Context context, Drawable icon, String title, String message) {
        this.context = context;
        this.icon = icon;
        this.title = title;
        this.message = message;
    }

    public Context getContext() {
        return this.context;
    }

    // message texts and buttons

    public Drawable getIcon() {
        return this.icon;
    }

    public String getTitle() {
        return this.title;
    }

    public String getMessage() {
        return this.message;
    }

    public View getContentView() {
        return this.contentView;
    }

    public String getOKButtonText() {
        if (this.hasOKButton()) {
            return this.context.getString(R.string.messagebox_ok);
        } else {
            return null;
        }
    }

    public String getCancelButtonText() {
        if (this.hasCancelButton()) {
            return this.context.getString(R.string.messagebox_cancel);
        } else {
            return null;
        }
    }

    public String getRetryButtonText() {
        if (this.hasRetryButton()) {
            return this.context.getString(R.string.messagebox_retry);
        } else {
            return null;
        }
    }

    public boolean hasOKButton() {
        return true;
    }

    public boolean hasCancelButton() {
        return false;
    }

    public boolean hasRetryButton() {
        return false;
    }

    // message handlers

    protected void onOK() {
    }

    protected void onCancel() {
    }

    protected void onRetry() {
    }

    public void show() {
        show(this);
    }

    public static class Error extends MessageBox {

        public Error(Context context, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_alert), context.getResources().getString(R.string.messagebox_error),
                    message);
        }

        public Error(Context context, String title, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_alert), title, message);
        }

        @Override
        protected final void onCancel() {
        }

        @Override
        protected final void onRetry() {
        }
    }

    public static class OKCancelWarning extends MessageBox {

        public OKCancelWarning(Context context, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_alert), context.getResources()
                    .getString(R.string.messagebox_warning), message);
        }

        public OKCancelWarning(Context context, String title, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_alert), title, message);
        }


        @Override
        public boolean hasCancelButton() {
            return true;
        }

        @Override
        protected final void onRetry() {
        }

    }

    public static class OKRetryWarning extends MessageBox {

        public OKRetryWarning(Context context, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), context.getResources().getString(
                    R.string.messagebox_information), message);
        }

        public OKRetryWarning(Context context, String title, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), title, message);
        }

        @Override
        public boolean hasRetryButton() {
            return true;
        }

        @Override
        protected final void onCancel() {
        }
    }

    public static class Information extends MessageBox {

        public Information(Context context, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), context.getResources().getString(
                    R.string.messagebox_information), message);
        }

        public Information(Context context, String title, String message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), title, message);
        }

        public Information(Context context, int message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), context.getResources().getString(
                    R.string.messagebox_information), context.getResources().getString(message));
        }

        public Information(Context context, int title, int message) {
            super(context, context.getResources().getDrawable(android.R.drawable.ic_dialog_info), context.getResources().getString(title), context
                    .getResources().getString(message));
        }

        @Override
        protected final void onCancel() {
        }

        @Override
        protected final void onRetry() {
        }
    }
}
