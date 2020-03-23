package com.example.library.app;


import java.lang.ref.WeakReference;


public abstract class BasePresenter<V extends IView> {
    private WeakReference actReference;
    protected V iView;

    public void attachView(IView iView) {
        actReference = new WeakReference(iView);
    }

    public void detachView() {
        if (actReference != null) {
            actReference.clear();
            actReference = null;
        }
    }

    public V getIView() {
        return (V) actReference.get();
    }

}