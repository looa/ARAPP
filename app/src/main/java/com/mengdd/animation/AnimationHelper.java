package com.mengdd.animation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;


public class AnimationHelper {

    public static ObjectAnimator getTranslateYAnimation(final View target,
                                                        final float startY, final float endY, final int duration,
                                                        final int startDelay, final boolean disappearLast,
                                                        final Animator.AnimatorListener animatorListener) {

        ObjectAnimator translateAnimator = ObjectAnimator.ofFloat(target,
                "translationY", startY, endY);
        translateAnimator.setDuration(duration);
        translateAnimator.setStartDelay(startDelay);
        translateAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                target.setVisibility(View.VISIBLE);
                target.setTranslationY(startY);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (disappearLast) {
                    target.setVisibility(View.GONE);
                }
            }
        });
        if (null != animatorListener) {
            translateAnimator.addListener(animatorListener);
        }
        return translateAnimator;
    }

    public static Animator getScaleAnimation(final View target,
                                             final int duration, final boolean disappearLast,
                                             final Animator.AnimatorListener animatorListener, final float... values) {

        // 使用ValueAnimator可以把XY两个方向的动画放在一个更新里
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(values);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {

                Float currentValue = (Float) valueAnimator.getAnimatedValue();
                target.setScaleX(currentValue);
                target.setScaleY(currentValue);
            }
        });
        scaleAnimator.setDuration(duration);
        scaleAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                target.setVisibility(View.VISIBLE);

                target.setScaleX(values[0]);
                target.setScaleY(values[0]);

            }

            @Override
            public void onAnimationEnd(Animator animation) {

                if (disappearLast) {
                    target.setVisibility(View.GONE);
                }
            }
        });
        if (null != animatorListener) {
            scaleAnimator.addListener(animatorListener);
        }
        return scaleAnimator;
    }

    public static ObjectAnimator getAlphaAnimation(final View target,
                                                   final int duration, final int startDelay,
                                                   final boolean disappearLast,
                                                   final Animator.AnimatorListener animatorListener, final float... values) {

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(target, "alpha",
                values);

        alphaAnimator.setDuration(duration);
        alphaAnimator.setStartDelay(startDelay);
        alphaAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                target.setVisibility(View.VISIBLE);
                target.setAlpha(values[0]);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (disappearLast) {
                    target.setVisibility(View.GONE);
                }
            }
        });
        if (null != animatorListener) {
            alphaAnimator.addListener(animatorListener);
        }

        return alphaAnimator;
    }

    public static Animator getFadeInAnimator(final View target,
                                             final int scaleDuration, final int alphaDuration,
                                             final int alphaDelay, final boolean disappearLast,
                                             final Animator.AnimatorListener animatorListener, final float... scaleValues) {

        Animator scaleAnimator = getScaleAnimation(target, scaleDuration,
                disappearLast, animatorListener, scaleValues);
        Animator alphaAnimator = getAlphaAnimation(target, alphaDuration,
                alphaDelay, false, null, 0, 1);

        AnimatorSet fadeInAnimatorSet = new AnimatorSet();
        fadeInAnimatorSet.playTogether(scaleAnimator, alphaAnimator);

        return fadeInAnimatorSet;

    }

    public static Animator getFadeOutAnimator(final View target,
                                              final int scaleDuration, final int alphaDuration,
                                              final int alphaDelay, final boolean disappearLast,
                                              final Animator.AnimatorListener animatorListener, final float... scaleValues) {

        Animator scaleAnimator = getScaleAnimation(target, scaleDuration,
                disappearLast, animatorListener, scaleValues);
        Animator alphaAnimator = getAlphaAnimation(target, alphaDuration,
                alphaDelay, false, null, 1, 0);

        AnimatorSet fadeInAnimatorSet = new AnimatorSet();
        fadeInAnimatorSet.playTogether(scaleAnimator, alphaAnimator);

        return fadeInAnimatorSet;

    }

    public static ObjectAnimator getRotationZAnimator(final View target,
                                                      final int duration, final int startDelay,
                                                      final boolean disappearLast,
                                                      final Animator.AnimatorListener animatorListener, final float... values) {

        ObjectAnimator rotationAnimator = ObjectAnimator.ofFloat(target,
                "rotation", values);

        rotationAnimator.setDuration(duration);
        rotationAnimator.setStartDelay(startDelay);
        rotationAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
                target.setVisibility(View.VISIBLE);
                target.setRotation(values[0]);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (disappearLast) {
                    target.setVisibility(View.GONE);
                }
            }
        });
        if (null != animatorListener) {
            rotationAnimator.addListener(animatorListener);
        }

        return rotationAnimator;

    }
}
