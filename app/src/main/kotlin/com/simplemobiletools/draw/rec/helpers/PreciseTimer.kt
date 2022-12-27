package com.simplemobiletools.draw.rec.helpers

import kotlinx.coroutines.*

/** Timer with sub milliseconds precision
 *
 * @param delay     - Delay in milliseconds.
 * @param precision - Precision in microseconds. Should be greater than 10, depends on a lot
 *                    of factors.
 * @param task      - A task which will be invoked by the Timer. Should be as easy as possible
 *                    to keep timer accuracy.
 * */
class PreciseTimer(val delay: Long = 0, val precision: Long, val task: (nanoTime: Long) -> Unit) {
    companion object {
        private val TAG = Companion::class.java.simpleName
    }
//    fun run() {
//        Thread {
//            if (precision > 0) {
//                var startTimestamp = System.nanoTime()
//                var currentTimeStamp = startTimestamp
//                while (true) {
//                    currentTimeStamp = System.nanoTime()
//                    val timeDiff = currentTimeStamp - startTimestamp
//                    if (timeDiff >= precision) {
//                        startTimestamp = currentTimeStamp
//                        task(System.nanoTime())
//                    } else {
//                        break
//                    }
//                }
//            } else {
//                task(System.nanoTime())
//            }
//        }.start()
//    }
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun startCoroutineTimer(delayMillis: Long = 0,
                                    precision: Long = 0,
                                    action: (nanoTime: Long) -> Unit) =
        scope.launch(Dispatchers.IO.limitedParallelism(1)) {
            delay(delayMillis)
            if (precision > 0) {
                var startTimestamp = System.nanoTime()
                var currentTimeStamp = startTimestamp
                while (true) {
                    currentTimeStamp = System.nanoTime()
                    val timeDiff = currentTimeStamp - startTimestamp
                    if (timeDiff >= precision) {
                        startTimestamp = currentTimeStamp
                        action(System.nanoTime())
                    } else {
                        break
                    }
                }
            } else {
                action(System.nanoTime())
            }
        }

    private val timer: Job = startCoroutineTimer(delayMillis = delay, precision = precision * 1000, task)


    fun start() = timer.start()
    fun stop() = timer.cancel()
}
