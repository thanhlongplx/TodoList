package com.example.todolist.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

public abstract class BaseFragment: Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        bindComponent()
        bindEvent()
        bindData()
    }
    protected open fun initData() {}
    protected open fun bindData() {}
    protected open fun bindComponent() {}
    protected open fun bindEvent() {}
}
