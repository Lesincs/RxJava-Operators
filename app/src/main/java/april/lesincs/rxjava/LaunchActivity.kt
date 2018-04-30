package april.lesincs.rxjava

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_launch.*

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        val animX = ObjectAnimator.ofFloat(iv_rxjava, "scaleX", 1f, 1.2f)
        animX.duration = 1000

        val animY = ObjectAnimator.ofFloat(iv_rxjava, "scaleY", 1f, 1.2f)
        animY.duration = 1000

        val animatorSet = AnimatorSet()
        animatorSet.playTogether(animX, animY)
        animatorSet.addListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                startActivity(Intent(this@LaunchActivity, MainActivity::class.java))
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                finish()
            }
        })

        animatorSet.start()
    }
}
