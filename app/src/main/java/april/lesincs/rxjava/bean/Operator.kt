package april.lesincs.rxjava.bean

import android.annotation.SuppressLint
import android.graphics.drawable.AdaptiveIconDrawable
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by cs丶 on 2018/4/25 15:29.
　文件描述:
 */
@SuppressLint("ParcelCreator")
@Parcelize
data class Operator(
        val name:String,
        val shortTip:String,
        val officialExplain:String,
        val gemDrawableId:Int,
        val code:String
):Parcelable
