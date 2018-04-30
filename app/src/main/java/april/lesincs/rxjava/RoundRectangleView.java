package april.lesincs.rxjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

/**
 *
 * @author cs丶
 * @date 2018/4/25 13:53
 * 　文件描述:
 */

public class RoundRectangleView extends View {

    private RectF mContentRect;
    private Paint mPaint;

    {
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.parseColor("#E8018E"));

        mContentRect = new RectF();
    }

    public RoundRectangleView(Context context) {
        super(context);
    }

    public RoundRectangleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mContentRect.set(0, 0, getMeasuredWidth(), getMeasuredHeight());
        canvas.drawRoundRect(mContentRect, 6f, 6f, mPaint);
    }
}
