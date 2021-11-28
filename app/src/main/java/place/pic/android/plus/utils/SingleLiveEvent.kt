package place.pic.android.plus.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created By kimdahyee
 * on 11월 28일, 2020
 */

class SingleLiveEvent <T> : MutableLiveData<T>() {

    // AtomicBoolean - multiThread 환경에서 동시성 보장
    private val pending = AtomicBoolean(false)

    // view가 활성화 상태가 되거나 setValue를 통해 값이 바뀌었을 때 호출되는 함수
    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(
            owner,
            { t ->
                if (pending.compareAndSet(true, false)) {
                    observer.onChanged(t)
                }
            }
        )
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        value = null
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}
