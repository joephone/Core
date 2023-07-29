import com.chad.library.adapter.base.BaseQuickAdapter
import com.transcendence.core.publicModule.smartrefreshlayout.widget.BravhLoadMoreView
import com.transcendence.core.publicModule.smartrefreshlayout.widget.CommonBaseViewHolder

open abstract class BaseAdapterKt<T> : BaseQuickAdapter<T, CommonBaseViewHolder?> {

    constructor(layoutResId: Int) : super(layoutResId) {
        setLoadMoreView(BravhLoadMoreView())
        //0 关闭动画 1.渐显 2.缩放 3.从下到上 4.从左到右 5.从右到左
//        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[1]);
    }

    constructor(layoutResId: Int, data: List<T>?) : super(layoutResId, data) {
        setLoadMoreView(BravhLoadMoreView())
        //0 关闭动画 1.渐显 2.缩放 3.从下到上 4.从左到右 5.从右到左
//        this.setAnimationWithDefault(BaseQuickAdapter.AnimationType.values()[1]);
    }
}