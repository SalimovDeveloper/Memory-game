package uz.snyder.memorygames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    val listCardOchiqYopiq =
        arrayOf(false, false, false, false, false, false, false, false, false, false, false, false)
    val imageIndex = arrayOfNulls<Int>(2)
    val rasmId = arrayOfNulls<Int>(2)
    var ochiqRasm = 0
    var animationDoing = false
    var pandaCount = 0
    var itCount = 0
    var filCount = 0
    var qushCount = 0
    var sakkizoyoqCount = 0
    var qushchaCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        image_1.setOnClickListener {
            imagePutter(randomCount(), image_1)
//            val count = 11
//            val random = Random.nextInt(count)
//            if (pandaCount > 2) {
//
//            }
//            when (random) {
//                0 -> {
//                    if (pandaCount < 2) {
//                        imageClick(image_1, R.drawable.panda, 0)
//                        pandaCount++
//                    } else {
//                        imageClick(image_1, R.drawable.it, 0)
//                        itCount++
//                    }
//                }
//                1 -> imageClick(image_1, R.drawable.panda, 0)
//                2 -> imageClick(image_1, R.drawable.panda, 0)
//                3 -> imageClick(image_1, R.drawable.panda, 0)
//                4 -> imageClick(image_1, R.drawable.panda, 0)
//            }
        }

        image_2.setOnClickListener {
            imageClick(image_2, R.drawable.qush, 1)

        }

        image_3.setOnClickListener {
            imageClick(image_3, R.drawable.qush, 2)
        }

        image_4.setOnClickListener {
            imageClick(image_4, R.drawable.panda, 3)
        }

        image_5.setOnClickListener {
            imageClick(image_5, R.drawable.it, 4)
        }

        image_6.setOnClickListener {
            imageClick(image_6, R.drawable.it, 5)
        }

        image_7.setOnClickListener {
            imageClick(image_7, R.drawable.sakkiz_oyog, 6)
        }

        image_8.setOnClickListener {
            imageClick(image_8, R.drawable.sakkiz_oyog, 7)
        }

        image_9.setOnClickListener {
            imageClick(image_9, R.drawable.fil, 8)
        }

        image_10.setOnClickListener {
            imageClick(image_10, R.drawable.fil, 9)
        }

        image_11.setOnClickListener {
            imageClick(image_11, R.drawable.qushcha, 10)
        }

        image_12.setOnClickListener {
            imageClick(image_12, R.drawable.qushcha, 11)
        }
    }

    private fun imagePutter(randomCount: Int, view: ImageView) {
        when (randomCount) {
            0 -> view.setBackgroundResource(R.drawable.panda)
            1 -> view.setBackgroundResource(R.drawable.it)
            2 -> view.setBackgroundResource(R.drawable.fil)
            3 -> view.setBackgroundResource(R.drawable.qush)
            4 -> view.setBackgroundResource(R.drawable.sakkiz_oyog)
            5 -> view.setBackgroundResource(R.drawable.qushcha)
        }
    }

    private fun checkAnimalsBoolean(clickCount: Int): Boolean {
        return if (clickCount < pandaCount) true
        else if (clickCount < itCount) true
        else if (clickCount < filCount) true
        else if (clickCount < qushCount) true
        else if (clickCount < sakkizoyoqCount) true
        else return false
    }

    private fun checkAnimalsCount(count: Int) {
        when (count) {
            0 -> pandaCount + 1
            1 -> itCount + 1
            2 -> filCount + 1
            3 -> qushCount + 1
            4 -> sakkizoyoqCount + 1
            5 -> qushchaCount + 1
        }
    }

    private fun randomCount(): Int {
        val count = 5
        val random = Random.nextInt(count)
        Log.d("@@@@",random.toString())
        return random
    }

    fun imageClick(imageView: ImageView, rasm: Int, index: Int) {
        if (!animationDoing) {
            if (!listCardOchiqYopiq[index]) {
                animationOchilish(imageView, rasm, index)
            } else {
                animationYopilishi(imageView, rasm, index)
            }
        }
    }

    fun animationOchilish(imageView: ImageView, rasm: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setBackgroundResource(rasm)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listCardOchiqYopiq[index] = true
                        imageIndex[ochiqRasm] = index
                        rasmId[ochiqRasm] = rasm
                        ochiqRasm++

                        if (ochiqRasm == 2) {
                            if (rasmId[0] == rasmId[1]) {
                                imageViewAniqla(imageIndex[0]).visibility = View.INVISIBLE
                                ochiqRasm--
                                imageViewAniqla(imageIndex[1]).visibility = View.INVISIBLE
                                ochiqRasm--
                            } else {
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[0]),
                                    -1,
                                    imageIndex[0]
                                )
                                animationYopilishi(
                                    imageViewAniqla(imageIndex[1]),
                                    -1,
                                    imageIndex[1]
                                )
                            }
                        }
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }

                })

            }

            override fun onAnimationRepeat(animation: Animation?) {

            }


        })

    }

    fun animationYopilishi(imageView: ImageView, rasm: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.anim_1)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animationDoing = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.anim_2)
                imageView.startAnimation(animation2)
                imageView.setBackgroundResource(R.drawable.ligh_icon)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        animationDoing = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listCardOchiqYopiq[index!!] = false
        ochiqRasm--
    }

    fun imageViewAniqla(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = image_1
            1 -> imageView = image_2
            2 -> imageView = image_3
            3 -> imageView = image_4
            4 -> imageView = image_5
            5 -> imageView = image_6
            6 -> imageView = image_7
            7 -> imageView = image_8
            8 -> imageView = image_9
            9 -> imageView = image_10
            10 -> imageView = image_11
            11 -> imageView = image_12
        }
        return imageView!!

    }

//    fun random(imageView: ImageView, rasm: Int, index: Int){
//        var randomImage = Random.nextInt(12)
//        when(randomImage){
//            0 -> {imageView.image_1 == R.drawable.fil}
//            1 ->
//        }
//    }
}