package com.hoanganhtuan95ptit.twolineseekbar.ui.view.crop;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.yalantis.ucrop.callback.CropBoundsChangeListener;
import com.yalantis.ucrop.util.RectUtils;

import java.util.Arrays;

/**
 * Created by Oleksii Shliama (https://github.com/shliama).
 * <p/>
 * This class adds crop feature, methods to draw crop guidelines, and keep image in correct state.
 * Also it extends parent class methods to add checks for ic_scale; animating zoom in/out.
 */
public class BrightnessImageView extends EditImageView {

    public static final int DEFAULT_MAX_BITMAP_SIZE = 0;
    public static final int DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION = 500;
    public static final float DEFAULT_MAX_SCALE_MULTIPLIER = 10.0f;
    public static final float SOURCE_IMAGE_ASPECT_RATIO = 0f;
    public static final float DEFAULT_ASPECT_RATIO = SOURCE_IMAGE_ASPECT_RATIO;

    private final RectF mCropRect = new RectF();

    private final Matrix mTempMatrix = new Matrix();

    private float mTargetAspectRatio;
    private float mMaxScaleMultiplier = DEFAULT_MAX_SCALE_MULTIPLIER;

    private CropBoundsChangeListener mCropBoundsChangeListener;

    private Runnable mWrapCropBoundsRunnable, mZoomImageToPositionRunnable = null;

    private float mMaxScale, mMinScale;
    private int mMaxResultImageSizeX = 0, mMaxResultImageSizeY = 0;
    private long mImageToWrapCropBoundsAnimDuration = DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;

    public BrightnessImageView(Context context) {
        this(context, null);
    }

    public BrightnessImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BrightnessImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    /**
     * When image is laid out it must be centered properly to fit current crop bounds.
     */
    @Override
    protected void onImageLaidOut() {
        super.onImageLaidOut();
        final Drawable drawable = getDrawable();
        if (drawable == null) {
            return;
        }

        float drawableWidth = drawable.getIntrinsicWidth();
        float drawableHeight = drawable.getIntrinsicHeight();

        if (mTargetAspectRatio == SOURCE_IMAGE_ASPECT_RATIO) {
            mTargetAspectRatio = drawableWidth / drawableHeight;
        }

        int height = (int) (mThisWidth / mTargetAspectRatio);
        if (height > mThisHeight) {
            int width = (int) (mThisHeight * mTargetAspectRatio);
            int halfDiff = (mThisWidth - width) / 2;
            mCropRect.set(halfDiff, 0, width + halfDiff, mThisHeight);
        } else {
            int halfDiff = (mThisHeight - height) / 2;
            mCropRect.set(0, halfDiff, mThisWidth, height + halfDiff);
        }

        calculateImageScaleBounds(drawableWidth, drawableHeight);
        setupInitialImagePosition(drawableWidth, drawableHeight);

        if (mCropBoundsChangeListener != null) {
            mCropBoundsChangeListener.onCropAspectRatioChanged(mTargetAspectRatio);
        }
        if (mTransformImageListener != null) {
//            mTransformImageListener.onScale(getCurrentScale());
//            mTransformImageListener.onRotate(getCurrentAngle());
            mTransformImageListener.onBrightness(getCurrentBrightness());
//            mTransformImageListener.onContrast(getCurrentContrast());
        }
    }


    /**
     * This method calculates image minimum and maximum ic_scale values for current {@link #mCropRect}.
     *
     * @param drawableWidth  - image width
     * @param drawableHeight - image height
     */
    private void calculateImageScaleBounds(float drawableWidth, float drawableHeight) {
        float widthScale = Math.min(mCropRect.width() / drawableWidth, mCropRect.width() / drawableHeight);
        float heightScale = Math.min(mCropRect.height() / drawableHeight, mCropRect.height() / drawableWidth);

        mMinScale = Math.min(widthScale, heightScale);
        mMaxScale = mMinScale * mMaxScaleMultiplier;
    }

    /**
     * This method calculates initial image position so it is positioned properly.
     * Then it sets those values to the current image matrix.
     *
     * @param drawableWidth  - image width
     * @param drawableHeight - image height
     */
    private void setupInitialImagePosition(float drawableWidth, float drawableHeight) {
        float cropRectWidth = mCropRect.width();
        float cropRectHeight = mCropRect.height();

        float widthScale = mCropRect.width() / drawableWidth;
        float heightScale = mCropRect.height() / drawableHeight;

        float initialMinScale = Math.max(widthScale, heightScale);

        float tw = (cropRectWidth - drawableWidth * initialMinScale) / 2.0f + mCropRect.left;
        float th = (cropRectHeight - drawableHeight * initialMinScale) / 2.0f + mCropRect.top;

        mCurrentImageMatrix.reset();
        mCurrentImageMatrix.postScale(initialMinScale, initialMinScale);
        mCurrentImageMatrix.postTranslate(tw, th);
        setImageMatrix(mCurrentImageMatrix);
    }

}
