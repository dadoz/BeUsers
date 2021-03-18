package com.davidelmn.application.frenzspots.views

import android.view.View
import androidx.annotation.IntDef
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.ViewCompat
import androidx.customview.widget.ViewDragHelper
import com.google.android.material.bottomsheet.BottomSheetBehavior


class AnchorSheetBehavior : BottomSheetBehavior<View>() {
//    /**
//    * The bottom sheet is anchor.
//    */
//    public static final int STATE_ANCHOR = 6;
//
//    /**
//     * @hide
//     */
//    @IntDef({ STATE_EXPANDED, STATE_COLLAPSED, STATE_DRAGGING, STATE_SETTLING, STATE_HIDDEN, STATE_ANCHOR })
//
//    open fun onLayoutChild(parent: CoordinatorLayout, child: V, layoutDirection: Int): Boolean {
//        // First let the parent lay it out
//        if (mState !== BottomSheetBehavior.STATE_DRAGGING && mState !== BottomSheetBehavior.STATE_SETTLING) {
//            if (ViewCompat.getFitsSystemWindows(parent) && !ViewCompat.getFitsSystemWindows(child)) {
//                ViewCompat.setFitsSystemWindows(child, true)
//            }
//            parent.onLayoutChild(child, layoutDirection)
//        }
//        // Offset the bottom sheet
//        mParentHeight = parent.height
//        mMinOffset = Math.max(0, mParentHeight - child.getHeight())
//        mMaxOffset = Math.max(mParentHeight - mPeekHeight, mMinOffset)
//        mAnchorOffset = Math.max(mParentHeight * mAnchorThreshold, mMinOffset) as Int
//        if (mState === BottomSheetBehavior.STATE_EXPANDED) {
//            ViewCompat.offsetTopAndBottom(child, mMinOffset)
//        } else if (mState === STATE_ANCHOR) {
//            ViewCompat.offsetTopAndBottom(child, mAnchorOffset)
//        } else if (mHideable && mState === BottomSheetBehavior.STATE_HIDDEN) {
//            ViewCompat.offsetTopAndBottom(child, mParentHeight)
//        } else if (mState === BottomSheetBehavior.STATE_COLLAPSED) {
//            ViewCompat.offsetTopAndBottom(child, mMaxOffset)
//        }
//        if (mViewDragHelper == null) {
//            mViewDragHelper = ViewDragHelper.create(parent, mDragCallback)
//        }
//        mViewRef = WeakReference(child)
//        mNestedScrollingChildRef = WeakReference(findScrollingChild(child))
//        return true
//    }
//
//    override fun setState(@State state: Int) {
//        if (state == mState) {
//            return
//        }
//        if (mViewRef == null) {
//            // The view is not laid out yet; modify mState and let onLayoutChild handle it later
//            if (state == STATE_COLLAPSED || state == STATE_EXPANDED || state == STATE_ANCHOR ||
//                mHideable && state == STATE_HIDDEN
//            ) {
//                mState = state
//            }
//            return
//        }
//        val child: V = mViewRef.get() ?: return
//        val top: Int
//        if (state == STATE_COLLAPSED) {
//            top = mMaxOffset
//            val scroll: View = mNestedScrollingChildRef.get()
//            if (scroll != null && ViewCompat.canScrollVertically(scroll, -1)) {
//                scroll.scrollTo(0, 0)
//            }
//        } else if (state == STATE_EXPANDED) {
//            top = mMinOffset
//        } else if (state == STATE_ANCHOR) {
//            top = mAnchorOffset
//        } else if (mHideable && state == STATE_HIDDEN) {
//            top = mParentHeight
//        } else {
//            throw IllegalArgumentException("Illegal state argument: $state")
//        }
//        setStateInternal(STATE_SETTLING)
//        if (mViewDragHelper.smoothSlideViewTo(child, child.getLeft(), top)) {
//            ViewCompat.postOnAnimation(child, SettleRunnable(child, state))
//        }
//    }
}