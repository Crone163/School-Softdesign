package com.softdesign.school.utils;

import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;

/**
 * Небольшой класс, который блокирует скролл на AppBarLayout
 * в принципе блокирует также любые NestedScrollChild
 * Метод SetDrag:
 * boolean isDrag true - двигается, false - не двигается
 * appBar - AppBarLayout, который необходимо заблокировать/разблокировать
 *
 * В методе у appBar создаётся новый Behavior (поведение элемента).
 * В переменную lp записываются его LayoutParams, в behavior - только что созданный Behavior.
 * в Behavior добавляется переопределённый метод DragCallback, который всегда возвращает true/false,
 * соответственно разрешая/запрещая drag. Возвращаемое значение DragCallback определяется параметром isDrag
 */

public class BlockToolbar {
    public static void setDrag(boolean isDrag, AppBarLayout appBar){
        final boolean drag = isDrag;
        ((CoordinatorLayout.LayoutParams) appBar.getLayoutParams()).setBehavior(new AppBarLayout.Behavior() {});
        CoordinatorLayout.LayoutParams lp = (CoordinatorLayout.LayoutParams) appBar.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) lp.getBehavior();
        behavior.setDragCallback(new AppBarLayout.Behavior.DragCallback() {
            @Override
            public boolean canDrag(@NonNull AppBarLayout appBarLayout) {
                return drag;
            }
        });
    }
}
