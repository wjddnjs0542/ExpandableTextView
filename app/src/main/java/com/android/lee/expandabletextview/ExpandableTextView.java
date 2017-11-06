package com.android.lee.expandabletextview;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.android.lee.expandabletextview.databinding.ExpandTextViewBinding;

/**
 * <PRE>
 * <p>
 * </PRE>
 * Created by Lee on 2017. 11. 06..
 */

public class ExpandableTextView extends LinearLayout{

    private static final int GRAVITY_LEFT = 0;
    private static final int GRAVITY_CENTER = 1;
    private static final int GRAVITY_RIGHT = 2;
    private static final int DEFAULT_MAX_LINE = 3;
    private static final int DEFAULT_ELLIPSIZE = 4;
    private static final int DEFAULT_WIDTH = LayoutParams.MATCH_PARENT;
    private static final int DEFAULT_HEIGHT = LayoutParams.WRAP_CONTENT;

    private Context mContext;
    private ExpandTextViewBinding mExpandBinding;

    /**
     * TextView Setting Reference
     */
    private int mMaxLines;
    private String mText;
    private float mTextWidth;
    private float mTextHeight;
    private int mTextGravity;
    private int mTextEllipsize;

    /**
     * Expand View Reference
     */
    private int mViewType;
    private int mViewWidth;
    private int mViewHeight;
    private String mViewText;
    private int mViewSrc;
    private int mViewGravity;

    public ExpandableTextView(Context context) {
        this(context, null);
    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }

    public ExpandableTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableTextView);
        mMaxLines = typedArray.getInt(R.styleable.ExpandableTextView_expandable_max_line, DEFAULT_MAX_LINE);
        mText = typedArray.getString(R.styleable.ExpandableTextView_expandable_text);
        mTextWidth = typedArray.getDimension(R.styleable.ExpandableTextView_expandable_text_width, DEFAULT_WIDTH);
        mTextHeight = typedArray.getDimension(R.styleable.ExpandableTextView_expandable_text_height, DEFAULT_HEIGHT);
        mTextGravity = typedArray.getInt(R.styleable.ExpandableTextView_expandable_text_gravity, 0);
        mTextEllipsize = typedArray.getInt(R.styleable.ExpandableTextView_expandable_text_ellipsize, -1);

        mViewType = typedArray.getInt(R.styleable.ExpandableTextView_expandable_view_type, -1);
        mViewWidth = typedArray.getInt(R.styleable.ExpandableTextView_expandable_view_width, -3);
        mViewHeight = typedArray.getInt(R.styleable.ExpandableTextView_expandable_view_height, -3);
        mViewText = typedArray.getString(R.styleable.ExpandableTextView_expandable_view_text);
        mViewSrc = typedArray.getInt(R.styleable.ExpandableTextView_expandable_view_src, -1);
        mViewGravity = typedArray.getInt(R.styleable.ExpandableTextView_expandable_view_gravity, 0);

        typedArray.recycle();
        init();
    }

    public void init(){
        mExpandBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.expand_text_view, null, false);

        mExpandBinding.text.setMaxLines(mMaxLines);
        mExpandBinding.text.setText(mText);
        switch (mTextEllipsize){
            case 0 : mExpandBinding.text.setEllipsize(TextUtils.TruncateAt.END); break;
            case 1 : mExpandBinding.text.setEllipsize(TextUtils.TruncateAt.MARQUEE); break;
            case 2 : mExpandBinding.text.setEllipsize(TextUtils.TruncateAt.MIDDLE); break;
            case 3 : mExpandBinding.text.setEllipsize(TextUtils.TruncateAt.START); break;
            default: break;
        }

        LinearLayout.LayoutParams ll = (LayoutParams) mExpandBinding.text.getLayoutParams();
        ll.height = (int) mTextHeight;
        ll.width = (int) mTextWidth;
        mExpandBinding.text.setLayoutParams(ll);

        addView(mExpandBinding.getRoot());
        requestLayout();
        invalidate();
    }
}
