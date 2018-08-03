package my.company.app

import android.arch.lifecycle.ViewModel
import android.os.Bundle
import android.support.annotation.CallSuper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewStub
import kotlinx.android.synthetic.main.fragment_viewstub.view.*

abstract class LazyFragment<T : ViewModel> : BaseFragment<T>() {
    private var mSavedInstanceState: Bundle? = null
    private var mHasInflated = false
    private var mViewStub: ViewStub? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_viewstub, container, false)
        mViewStub = view.fragmentViewStub
        view.fragmentViewStub.layoutResource = layoutId
        mSavedInstanceState = savedInstanceState

        if (userVisibleHint && !mHasInflated) {
            val inflatedView = view.fragmentViewStub.inflate()
            onCreateViewAfterViewStubInflated(inflatedView, mSavedInstanceState)
            afterViewStubInflated(view)
        }

        return view
    }

    protected abstract fun onCreateViewAfterViewStubInflated(inflatedView: View, savedInstanceState: Bundle?)

    @CallSuper
    protected fun afterViewStubInflated(originalViewContainerWithViewStub: View?) {
        mHasInflated = true
        if (originalViewContainerWithViewStub != null) {
            val pb = originalViewContainerWithViewStub.findViewById<View>(R.id.inflateProgressbar)
            pb.visibility = View.GONE
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)

        mViewStub?.let {
            if (isVisibleToUser && !mHasInflated) {
                val inflatedView = it.inflate()
                onCreateViewAfterViewStubInflated(inflatedView, mSavedInstanceState)
                afterViewStubInflated(view)
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        mHasInflated = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mHasInflated = false
    }
}
