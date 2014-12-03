package com.vine.vinemars.view;

import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by chengfei on 14/12/1.
 */
public class CrossInEditTouchListener implements View.OnTouchListener {
    private int fuzz = 10;

    private EditText editText;

    public CrossInEditTouchListener(EditText editText) {
        this.editText = editText;
        this.editText.addTextChangedListener(new CrossInEditTextWatcher(editText));
    }

    /*
             * (non-Javadoc)
             *
             * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
             */
    @Override
    public boolean onTouch(final View v, final MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            final Drawable[] drawables = ((TextView) v).getCompoundDrawables();
            if (drawables != null && drawables.length == 4 && drawables[2] != null) {
                Drawable drawable = drawables[2];
                final int x = (int) event.getX();
                final int y = (int) event.getY();
                final Rect bounds = drawable.getBounds();
                if (x >= (v.getRight() - bounds.width() - fuzz) && x <= (v.getRight() - v.getPaddingRight() + fuzz)
                        && y >= (v.getPaddingTop() - fuzz) && y <= (v.getHeight() - v.getPaddingBottom()) + fuzz) {
                    editText.getEditableText().clear();
                    return true;
                }
            }

        }
        return false;
    }

}
