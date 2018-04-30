package april.lesincs.rxjava

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import april.lesincs.rxjava.bean.Operator
import com.protectsoft.webviewcode.Codeview
import com.protectsoft.webviewcode.Settings
import kotlinx.android.synthetic.main.frament_operator.view.*

/**
 * Created by cs丶 on 2018/4/25 11:42.
　文件描述:
 */
class OperatorFragment : Fragment() {

    lateinit var mOperator: Operator

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.frament_operator, container, false)
    }

    override fun setArguments(args: Bundle?) {
        super.setArguments(args)
        setHasOptionsMenu(true)
        mOperator = args?.get(ARGUMENT_OPERATOR) as Operator
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.title = mOperator.name

        with(view) {
            tv_operator_name.text = mOperator.name
            tv_operator_short_tip.text = mOperator.shortTip
            iv_gem_image.setImageResource(mOperator.gemDrawableId)
            tv_official_explain.text = mOperator.officialExplain
            codeView.settings.supportZoom()
            codeView.settings.textZoom = 105
            Codeview.with(context)
                    .withCode(mOperator.code)
                    .into(codeView)
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            (activity as AppCompatActivity).supportActionBar?.title = mOperator.name
        }
    }


}