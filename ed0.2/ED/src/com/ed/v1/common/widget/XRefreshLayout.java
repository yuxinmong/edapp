package com.ed.v1.common.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Scroller;
import android.widget.TextView;

import com.ed.v1.R;
import com.ed.v1.common.NewFormatter;

public class XRefreshLayout extends LinearLayout {
    private final static int SCROLL_DURATION = 400;

    private Context context;
    private Scroller scroller;

    private float lastY;
    private ScrollView scrollView;
    private XListViewHeader headerView;
    private TextView txtTime;
    private int headerViewHeight;

    private OnRefreshListener listener;

    public XRefreshLayout(Context context) {
        super(context);
        init(context);
    }

    public XRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public XRefreshLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        scroller = new Scroller(context, new DecelerateInterpolator());
        addHeaderView();
    }

    public void stopRefresh() {
        headerView.setState(XListViewHeader.STATE_NORMAL);
        resetRefreshHeader();
    }

    public void setOnRefreshListener(OnRefreshListener listener) {
        this.listener = listener;
    }

    public void setRefreshTime(long time) {
        txtTime.setText(NewFormatter.formatDateTime(time));
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                lastY = ev.getY();
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaY = ev.getY() - lastY;
                if (isRefreshViewScroll(deltaY)) {
                    return true;
                }
            }
            default:
                break;
        }
        return super.onInterceptTouchEvent(ev);
    }

    private boolean isRefreshViewScroll(float deltaY) {
        if (isRefreshing()) {
            return false;
        }

        if (deltaY > 0 && scrollView.getScrollY() == 0 || headerView.getVisiableHeight() > 0) {
            return true;
        }
        return false;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                float deltaY = ev.getY() - lastY;
                lastY = ev.getY();
                updateRefreshHeader(deltaY);
                break;
            }
            default: {
                if (headerView.getVisiableHeight() > headerViewHeight) {
                    headerView.setState(XListViewHeader.STATE_REFRESHING);
                    if (listener != null) {
                        listener.onRefresh(this);
                    }
                }
                resetRefreshHeader();
                break;
            }
        }
        return super.onTouchEvent(ev);
    }

    private void updateRefreshHeader(float deltaY) {

        headerView.setVisiableHeight((int) (deltaY / 2) + headerView.getVisiableHeight());

        if (!isRefreshing()) {
            if (headerView.getVisiableHeight() < headerViewHeight) {
                headerView.setState(XListViewHeader.STATE_NORMAL);
            } else {
                headerView.setState(XListViewHeader.STATE_READY);
            }
        }
    }

    private boolean isRefreshing() {
        return headerView.getState() == XListViewHeader.STATE_REFRESHING;
    }

    private void resetRefreshHeader() {
        int height = headerView.getVisiableHeight();
        if (height == 0)
            return;
        if (isRefreshing() && height <= headerViewHeight) {
            return;
        }
        int finalHeight = 0;
        if (isRefreshing() && height > headerViewHeight) {
            finalHeight = headerViewHeight;
        }
        scroller.startScroll(0, height, 0, finalHeight - height, SCROLL_DURATION);
        invalidate();
    }

    @Override
    protected void onFinishInflate() {
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);
            if (view instanceof ScrollView) {
                scrollView = (ScrollView) view;
            }
        }
        if (scrollView == null) {
            throw new IllegalArgumentException("not found ScrollView in the PullToRefreshLayout!");
        }
        super.onFinishInflate();
    }

    private void addHeaderView() {
        headerView = new XListViewHeader(context);
        txtTime = (TextView) headerView.findViewById(R.id.xlistview_header_time);
        final View headerViewContent = headerView.findViewById(R.id.xlistview_header_content);
        addView(headerView);
        headerView.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onGlobalLayout() {
                headerViewHeight = headerViewContent.getHeight();
                headerView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            }
        });
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            headerView.setVisiableHeight(scroller.getCurrY());
            invalidate();
        }
    }

    public static interface OnRefreshListener {
        void onRefresh(XRefreshLayout pullToRefreshLayout);
    }
}
