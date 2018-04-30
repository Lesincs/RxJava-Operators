package april.lesincs.rxjava

import april.lesincs.rxjava.bean.Operator

/**
 * Created by cs丶 on 2018/4/25 15:44.
　文件描述:
 */
object OperatorFactory {

    val OPERATOR_CREATE by lazyOf(Operator(
            "Create",
            "create an Observable from scratch by means of a function",
            "You can create an Observable from scratch by using the Create operator. You pass this operator a function that accepts the observer as its parameter. Write this function so that it behaves as an Observable — by calling the observer’s onNext, onError, and onCompleted methods appropriately.\n" +
                    "\n" +
                    "A well-formed finite Observable must attempt to call either the observer’s onCompleted method exactly once or its onError method exactly once, and must not thereafter attempt to call any of the observer’s other methods.",
            R.drawable.operator_create,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.create<Int> {\n" +
                    "        it.onNext(1)\n" +
                    "        it.onNext(2)\n" +
                    "        it.onNext(3)\n" +
                    "        it.onError(Throwable(\"error!\"))\n" +
                    "        it.onComplete()\n" +
                    "    }.subscribe(\n" +
                    "            {\n" +
                    "                println(it)\n" +
                    "            },\n" +
                    "            {\n" +
                    "                println(it)\n" +
                    "            },\n" +
                    "            {\n" +
                    "                println(\"onComplete\")\n" +
                    "            })\n" +
                    "}\n" +
                    "\n//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "java.lang.Throwable: error!\n" +
                    "\n//tips\n" +
                    "error事件和complete事件互斥\n先发送error再发送complete会收不到complete事件\n" +
                    "反之会抛出异常"
    ))

    val OPERATOR_DEFER by lazyOf(Operator(
            "Defer",
            "do not create the Observable until the observer subscribes, and create a fresh Observable for each observer",
            "The Defer operator waits until an observer subscribes to it, and then it generates an Observable, typically with an Observable factory function. It does this afresh for each subscriber, so although each subscriber may think it is subscribing to the same Observable, in fact each subscriber gets its own individual sequence.\n" +
                    "\n" +
                    "In some circumstances, waiting until the last minute (that is, until subscription time) to generate the Observable can ensure that this Observable contains the freshest data.",
            R.drawable.operator_defer,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.defer {\n" +
                    "        return@defer ObservableSource<Int> {\n" +
                    "            it.onNext(1)\n" +
                    "            it.onNext(2)\n" +
                    "            it.onNext(3)\n" +
                    "            it.onComplete()\n" +
                    "        }\n" +
                    "    }.subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "\n" +
                    "//tips\n" +
                    "defer会在每次被订阅时创建一个新的Observable发送事件"
    ))

    val OPERTOR_EMPTY by lazyOf(Operator(
            "Empty",
            "create an Observable that emits no items but terminates normally",
            "create an Observable that emits no items but terminates normally",
            R.drawable.operator_empty,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.empty<Int>()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "顾名思义,发送空事件,不过会回调onComplete"
    ))

    val OPERTOR_NEVER by lazyOf(Operator(
            "Never",
            "create an Observable that emits no items and does not terminate",
            "create an Observable that emits no items and does not terminate",
            R.drawable.operator_never,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.never<Int>()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "\n" +
                    "//tips\n" +
                    "使用该操作符不会回调任何事件"
    ))

    val OPERTOR_ERROR by lazyOf(Operator(
            "Error",
            "create an Observable that emits no items and terminates with an error",
            "create an Observable that emits no items and terminates with an error",
            R.drawable.operator_throw,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.error<Throwable>(Throwable(\"error\"))\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "java.lang.Throwable: error\n" +
                    "\n" +
                    "//tips\n" +
                    "由RxJava1.x Throw操作符改名而来\n" +
                    "使用该操作符只回调onError"
    ))
    val OPERTOR_FROM by lazyOf(Operator(
            "From",
            "convert various other objects and data types into Observables",
            "When you work with Observables, it can be more convenient if all of the data you mean to work with can be represented as Observables, rather than as a mixture of Observables and other types. This allows you to use a single set of operators to govern the entire lifespan of the data stream.\n" +
                    "\n" +
                    "Iterables, for example, can be thought of as a sort of synchronous Observable; Futures, as a sort of Observable that always emits only a single item. By explicitly converting such objects to Observables, you allow them to interact as peers with other Observables.",
            R.drawable.operator_from,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    //from在2.x的重载有:fromArray fromCallable fromFuture fromIterable fromPublisher\n" +
                    "\n" +
                    "    //fromArray 和just类似\n" +
                    "    Observable.fromArray(1, 2, 3)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "\n" +
                    "    //fromIterable\n" +
                    "    val list = mutableListOf(1, 2, 3)\n" +
                    "    Observable.fromIterable(list)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "\n" +
                    "    //fromCallable\n" +
                    "    Observable.fromCallable {\n" +
                    "        Thread.sleep(1000)\n" +
                    "        return@fromCallable 1\n" +
                    "    }.subscribe(\n" +
                    "            {\n" +
                    "                println(it)\n" +
                    "            },\n" +
                    "            {\n" +
                    "                println(it)\n" +
                    "            },\n" +
                    "            {\n" +
                    "                println(\"complete\")\n" +
                    "            }\n" +
                    "    )\n" +
                    "\n" +
                    "    //剩余的几个用得较少 代码就不写了\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "1\n" +
                    "complete\n" +
                    "        \n" +
                    "//tips\n" +
                    "当使用fromArray时切记不可直接传一个array,否则下游只会收到一个array"
    ))

    val OPERTOR_INTERVAL by lazyOf(Operator(
            "Interval",
            "create an Observable that emits a sequence of integers spaced by a given time interval",
            "The Interval operator returns an Observable that emits an infinite sequence of ascending integers, with a constant interval of time of your choosing between emissions.",
            R.drawable.operator_interval,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    //表示每间隔1s,向下游发送一个自增的long型数字,也就是会依次发送,0 1 2 3 ....\n" +
                    "    Observable.interval(1, TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "\n" +
                    "    //避免过早程序停止\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "7\n" +
                    "8\n" +
                    "9\n" +
                    "\n" +
                    "//tips\n" +
                    "interval是一个比较有意思的操作符,可用来进行轮播图片"
    ))

    val OPERTOR_JUST by lazyOf(Operator(
            "Just",
            "create an Observable that emits a particular item",
            "The Just operator converts an item into an Observable that emits that item.\n" +
                    "\n" +
                    "Just is similar to From, but note that From will dive into an array or an iterable or something of that sort to pull out items to emit, while Just will simply emit the array or iterable or what-have-you as it is, unchanged, as a single item.\n" +
                    "\n" +
                    "Note that if you pass null to Just, it will return an Observable that emits null as an item. Do not make the mistake of assuming that this will return an empty Observable (one that emits no items at all). For that, you will need the Empty operator.",
            R.drawable.operator_just,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "和fromArray类似,凭个人喜好使用"
    ))

    val OPERTOR_RANGE by lazyOf(Operator(
            "Range",
            "create an Observable that emits a particular range of sequential integers",
            "The Range operator emits a range of sequential integers, in order, where you select the start of the range and its length.",
            R.drawable.operator_range,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.range(1,5)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "5\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "发送n到n+m-1的整形区间的数字到下游"
    ))

    val OPERTOR_TIMER by lazyOf(Operator(
            "Timer",
            "create an Observable that emits a particular item after a given delay",
            "The Timer operator creates an Observable that emits one particular item after a span of time that you specify.",
            R.drawable.operator_timer,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.timer(2,TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "    Thread.sleep(3000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "延迟指定的时间向下游发送一个事件,可用于定时任务"
    ))

    val OPERATOR_BUFFER = Operator(
            "Buffer",
            "periodically gather items emitted by an Observable into bundles and emit these bundles rather than emitting the items one at a time",
            "The Buffer operator transforms an Observable that emits items into an Observable that emits buffered collections of those items. There are a number of variants in the various language-specific implementations of Buffer that differ in how they choose which items go in which buffers.\n" +
                    "\n" +
                    "Note that if the source Observable issues an onError notification, Buffer will pass on this notification immediately without first emitting the buffer it is in the process of assembling, even if that buffer contains items that were emitted by the source Observable before it issued the error notification.\n" +
                    "\n" +
                    "The Window operator is similar to Buffer but collects items into separate Observables rather than into data structures before reemitting them.",
            R.drawable.operator_buffer,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .buffer(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "[1, 2]\n" +
                    "[3, 4]\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "buffer会把事件合并成一个指定长度的集合再依次发送到下游,下游收到的事件为集合\n")

    val OPERATOR_FLATMAP = Operator(
            "FlatMap",
            "transform the items emitted by an Observable into Observables, then flatten the emissions from those into a single Observable",
            "The FlatMap operator transforms an Observable by applying a function that you specify to each item emitted by the source Observable, where that function returns an Observable that itself emits items. FlatMap then merges the emissions of these resulting Observables, emitting these merged results as its own sequence.\n" +
                    "\n" +
                    "This method is useful, for example, when you have an Observable that emits a series of items that themselves have Observable members or are in other ways transformable into Observables, so that you can create a new Observable that emits the complete collection of items emitted by the sub-Observables of these items.\n" +
                    "\n" +
                    "Note that FlatMap merges the emissions of these Observables, so that they may interleave.\n" +
                    "\n" +
                    "In several of the language-specific implementations there is also an operator that does not interleave the emissions from the transformed Observables, but instead emits these emissions in strict order, often called ConcatMap or something similar.",
            R.drawable.operator_flat_map,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .flatMap {\n" +
                    "                return@flatMap Observable.just(\"\${it}A\",\"\${it}B\")\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1A\n" +
                    "1B\n" +
                    "2A\n" +
                    "2B\n" +
                    "3A\n" +
                    "3B\n" +
                    "\n" +
                    "//tips\n" +
                    "flatMap将一个observable转换为一个或者多个observable,在将事件平铺最后汇聚成一个observable发送到下游")

    val OPERATOR_GROUPBY = Operator(
            "GroupBy",
            "divide an Observable into a set of Observables that each emit a different subset of items from the original Observable",
            "The GroupBy operator divides an Observable that emits items into an Observable that emits Observables, each one of which emits some subset of the items from the original source Observable. Which items end up on which Observable is typically decided by a discriminating function that evaluates each item and assigns it a key. All items with the same key are emitted by the same Observable.",
            R.drawable.operator_group_by,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(\"A\", \"B\", \"C\",\"A\")\n" +
                    "            .groupBy {\n" +
                    "                return@groupBy when (it) {\n" +
                    "                    \"A\" -> 1\n" +
                    "                    \"B\" -> 2\n" +
                    "                    \"C\" -> 3\n" +
                    "                    else -> 4\n" +
                    "                }\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        /*onNext返回一个GroupedObservable\n" +
                    "                        * 需要手动订阅获取事件\n" +
                    "                        * */\n" +
                    "                        val groupedObservable = it\n" +
                    "                        it.subscribe {\n" +
                    "                            println(\"key=\${groupedObservable.key}\")\n" +
                    "                            println(\"value=\$it\")\n" +
                    "                            println()\n" +
                    "                        }\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "key=1\n" +
                    "value=A\n" +
                    "\n" +
                    "key=2\n" +
                    "value=B\n" +
                    "\n" +
                    "key=3\n" +
                    "value=C\n" +
                    "\n" +
                    "key=1\n" +
                    "value=A\n" +
                    "\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "将每一个事件按规则分组\n" +
                    "组号为整形\n" +
                    "发送给下游的事件为可以得到组号(key)的GroupedObservable")

    val OPERATOR_MAP = Operator(
            "Map",
            "transform the items emitted by an Observable by applying a function to each item",
            "The Map operator applies a function of your choosing to each item emitted by the source Observable, and returns an Observable that emits the results of these function applications.",
            R.drawable.operator_map,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .map {\n" +
                    "                when (it) {\n" +
                    "                    1 -> \"A\"\n" +
                    "                    else -> \"Not A\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "A\n" +
                    "Not A\n" +
                    "Not A\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "最常用的变换操作符了,为事件提供一对一的变换\n")

    val OPERATOR_SCAN = Operator(
            "Scan",
            "apply a function to each item emitted by an Observable, sequentially, and emit each successive value",
            "The Scan operator applies a function to the first item emitted by the source Observable and then emits the result of that function as its own first emission. It also feeds the result of the function back into the function along with the second item emitted by the source Observable in order to generate its second emission. It continues to feed back its own subsequent emissions along with the subsequent emissions from the source Observable in order to create the rest of its sequence.\n" +
                    "\n" +
                    "This sort of operator is sometimes called an “accumulator” in other contexts.",
            R.drawable.operator_scan,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .scan { i1: Int, i2: Int ->\n" +
                    "                //i1为上一次发送到下游的事件,如果是第一个元素,不执行这个函数直接发送\n" +
                    "                println(\"i1=\${i1} i2=\${i2}\")\n" +
                    "                //返回值将发送到下游\n" +
                    "                return@scan i1 * i2\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "i1=1 i2=2\n" +
                    "2\n" +
                    "i1=2 i2=3\n" +
                    "6\n" +
                    "i1=6 i2=4\n" +
                    "24\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "对于第一个事件,不执行scan接受的函数,直接发送到下游\n" +
                    "对于后面的事件,将上一次发送到下游的事件以及本身作为参数传给scan函数,然后将返回值作为事件发送到下游\n" +
                    "和reduce操作符很相似")

    val OPERATOR_WINDOW = Operator(
            "Window",
            "periodically subdivide items from an Observable into Observable windows and emit these windows rather than emitting the items one at a time",
            "Window is similar to Buffer, but rather than emitting packets of items from the source Observable, it emits Observables, each one of which emits a subset of items from the source Observable and then terminates with an onCompleted notification.\n" +
                    "\n" +
                    "Like Buffer, Window has many varieties, each with its own way of subdividing the original Observable into the resulting Observable emissions, each one of which contains a “window” onto the original emitted items. In the terminology of the Window operator, when a window “opens,” this means that a new Observable is emitted and that Observable will begin emitting items emitted by the source Observable. When a window “closes,” this means that the emitted Observable stops emitting items from the source Observable and terminates with an onCompleted notification to its observers.",
            R.drawable.operator_window,
            "    Observable.just(1, 2, 3, 4)\n" +
                    "            .window(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        //这里下游接受到的是一个observable\n" +
                    "                        it.subscribe {\n" +
                    "                            println(it)\n" +
                    "                        }\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "window同buffer类似\n" +
                    "buffer是把元素分组成list发送到下游\n" +
                    "而window是把元素分组成observable发送到下游\n")

    val OPERATOR_DEBOUNCE = Operator(
            "Debounce",
            "only emit an item from an Observable if a particular timespan has passed without it emitting another item",
            "The Debounce operator filters out items emitted by the source Observable that are rapidly followed by another emitted item.",
            R.drawable.operator_debounce,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.create<Int> {\n" +
                    "        //保存事件1\n" +
                    "        it.onNext(1)\n" +
                    "\n" +
                    "        //保存了事件1,在过了500ms时,事件2还未被发送,所以发送事件1\n" +
                    "        Thread.sleep(600)\n" +
                    "        //保存事件2\n" +
                    "        it.onNext(2)\n" +
                    "\n" +
                    "        //过了500ms时,事件3还未被发送,所以发送事件2\n" +
                    "        Thread.sleep(600)\n" +
                    "        //保存事件3\n" +
                    "        it.onNext(3)\n" +
                    "\n" +
                    "        Thread.sleep(400)\n" +
                    "        //此时应该发送事件4,间隔小于500ms,丢弃保存的事件3,保存事件4\n" +
                    "        it.onNext(4)\n" +
                    "\n" +
                    "        //过了500ms后,发送事件4\n" +
                    "    }\n" +
                    "            .debounce(500, TimeUnit.MILLISECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(6000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "4\n" +
                    "\n" +
                    "//tips\n" +
                    "对于debounce操作符,每一次发送事件实际上是对事件的一次保存\n" +
                    "如果保存事件超过debounce设置的时间,才发送到下游\n" +
                    "期间如果有其它事件要求发送,则丢弃当前保存的事件,保存新的要发送的事件\n"
    )

    val OPERATOR_DISTINCT = Operator(
            "Distinct",
            "suppress duplicate items emitted by an Observable\n",
            "The Distinct operator filters an Observable by only allowing items through that have not already been emitted.\n" +
                    "\n" +
                    "In some implementations there are variants that allow you to adjust the criteria by which two items are considered “distinct.” In some, there is a variant of the operator that only compares an item against its immediate predecessor for distinctness, thereby filtering only consecutive duplicate items from the sequence.",
            R.drawable.operator_distinct,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 1)\n" +
                    "            .distinct()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "简单的去重操作符\n" +
                    "使用equals()方法比较item是否为同一个"
    )

    val OPERATOR_ELEMENT_AT = Operator(
            "ElementAt",
            "emit only item n emitted by an Observable",
            "The ElementAt operator pulls an item located at a specified index location in the sequence of items emitted by the source Observable and emits that item as its own sole emission.",
            R.drawable.operator_element_at,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .elementAt(3)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "4\n" +
                    "\n" +
                    "//tips\n" +
                    "发送指定下标的事件\n" +
                    "如果下标不存在,发送complete事件"
    )

    val OPERATOR_FILTER = Operator(
            "Filter",
            "emit only those items from an Observable that pass a predicate test",
            "The Filter operator filters an Observable by only allowing items through that pass a test that you specify in the form of a predicate function.",
            R.drawable.operator_filter,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .filter {\n" +
                    "                it > 2\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "3\n" +
                    "4\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "只发送filter函数返回true的事件"
    )

    val OPERATOR_FIRST = Operator(
            "First",
            "emit only the first item (or the first item that meets some condition) emitted by an Observable",
            "If you are only interested in the first item emitted by an Observable, or the first item that meets some criteria, you can filter the Observable with the First operator.\n" +
                    "\n" +
                    "In some implementations, First is not implemented as a filtering operator that returns an Observable, but as a blocking function that returns a particular item at such time as the source Observable emits that item. In those implementations, if you instead want a filtering operator, you may have better luck with Take(1) or ElementAt(0).\n" +
                    "\n" +
                    "In some implementations there is also a Single operator. It behaves similarly to First except that it waits until the source Observable terminates in order to guarantee that it only emits a single item (otherwise, rather than emitting that item, it terminates with an error). You can use this to not only take the first item from the source Observable but to also guarantee that there was only one item.",
            R.drawable.operator_first,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .first(1)\n" +
                    "            .subscribe({\n" +
                    "                println(it)\n" +
                    "            },{\n" +
                    "                println(it)\n" +
                    "            })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "\n" +
                    "//tips\n" +
                    "只发送第一个事件"
    )

    val OPERATOR_IGNORE_ELEMENTS = Operator(
            "IgnoreElements",
            "do not emit any items from an Observable but mirror its termination notification",
            "The IgnoreElements operator suppresses all of the items emitted by the source Observable, but allows its termination notification (either onError or onCompleted) to pass through unchanged.\n" +
                    "\n" +
                    "If you do not care about the items being emitted by an Observable, but you do want to be notified when it completes or when it terminates with an error, you can apply the ignoreElements operator to the Observable, which will ensure that it will never call its observers’ onNext handlers.",
            R.drawable.operator_ignore_elements,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .ignoreElements()\n" +
                    "            .subscribe({\n" +
                    "                println(\"complete\")\n" +
                    "            })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "丢弃所有事件,然后发送onComplete事件"
    )

    val OPERATOR_LAST = Operator(
            "Last",
            "emit only the last item (or the last item that meets some condition) emitted by an Observable",
            "If you are only interested in the last item emitted by an Observable, or the last item that meets some criteria, you can filter the Observable with the Last operator.\n" +
                    "\n" +
                    "In some implementations, Last is not implemented as a filtering operator that returns an Observable, but as a blocking function that returns a particular item when the source Observable terminates. In those implementations, if you instead want a filtering operator, you may have better luck with TakeLast(1).",
            R.drawable.operator_last,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3)\n" +
                    "            .last(1)\n" +
                    "            .subscribe({\n" +
                    "                println(it)\n" +
                    "            }, {\n" +
                    "                println(it)\n" +
                    "            })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "3\n" +
                    "\n" +
                    "//tips\n" +
                    "只发送最后一个事件"
    )

    val OPERATOR_SAMPLE = Operator(
            "Sample",
            "emit the most recent items emitted by an Observable within periodic time intervals",
            "The Sample operator periodically looks at an Observable and emits whichever item it has most recently emitted since the previous sampling.\n" +
                    "\n" +
                    "In some implementations, there is also a ThrottleFirst operator that is similar, but emits not the most-recently emitted item in the sample period, but the first item that was emitted during that period.",
            R.drawable.operator_sample,
            "fun main(args: Array<String>) {\n" +
                    "    //每1s向下游发送一个事件\n" +
                    "    Observable.interval(1, TimeUnit.SECONDS)\n" +
                    "            .sample(2, TimeUnit.SECONDS) //对上游的事件每2s进行一次采样发送最近的到下游\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "4\n" +
                    "7\n" +
                    "\n" +
                    "//tips\n" +
                    "周期性的对上游发送的事件采样,采取上游最近一次发送的事件真正发送给下游\n" +
                    "如果某个时期内上游没有数据发射，那么这段时期内就没有被观察的数据，他也不会发射最近的" +
                    "结合宝石图很容易理解"
    )

    val OPERATOR_SKIP = Operator(
            "Skip",
            "suppress the first n items emitted by an Observable",
            "You can ignore the first n items emitted by an Observable and attend only to those items that come after, by modifying the Observable with the Skip operator.",
            R.drawable.operator_skip,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .skip(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "3\n" +
                    "4\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "丢弃指定数目的事件,发送后面的事件"
    )

    val OPERATOR_SKIP_LAST = Operator(
            "SkipLast",
            "suppress the final n items emitted by an Observable",
            "You can ignore the final n items emitted by an Observable and attend only to those items that come before them, by modifying the Observable with the SkipLast operator.",
            R.drawable.operator_skip_last,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .skipLast(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "和skip类似,只是从末端开始丢弃事件"
    )

    val OPERATOR_TAKE = Operator(
            "Take",
            "emit only the first n items emitted by an Observable",
            "You can emit only the first n items emitted by an Observable and then complete while ignoring the remainder, by modifying the Observable with the Take operator.",
            R.drawable.operator_take,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .take(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "skip的反向操作,从事件起始取指定个数发送,剩余事件丢弃"
    )

    val OPERATOR_TAKE_LAST = Operator(
            "TakeLast",
            "emit only the final n items emitted by an Observable",
            "You can emit only the final n items emitted by an Observable and ignore those items that come before them, by modifying the Observable with the TakeLast operator.",
            R.drawable.operator_take_last,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4)\n" +
                    "            .takeLast(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "3\n" +
                    "4\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "skipLast的反向操作,从事件末端取指定个数发送,前面的丢弃"
    )

    val OPERATOR_COMBINLATEST = Operator(
            "CombineLatest",
            "when an item is emitted by either of two Observables, combine the latest item emitted by each Observable via a specified function and emit items based on the results of this function",
            "The CombineLatest operator behaves in a similar way to Zip, but while Zip emits items only when each of the zipped source Observables have emitted a previously unzipped item, CombineLatest emits an item whenever any of the source Observables emits an item (so long as each of the source Observables has emitted at least one item). When any of the source Observables emits an item, CombineLatest combines the most recently emitted items from each of the other source Observables, using a function you provide, and emits the return value from that function.",
            R.drawable.operator_combine_latest,
            "fun main(args: Array<String>) {\n" +
                    "    val o1 = Observable.interval(1, TimeUnit.SECONDS)\n" +
                    "    val o2 = Observable.interval(2, TimeUnit.SECONDS).map { (it.toInt() - (0 - 'a'.toInt())).toChar() }\n" +
                    "    Observable.combineLatest(o1, o2, BiFunction<Long, Char, String> { p0, p1 ->\n" +
                    "        println(\"o1最近发送的为\$p0 o2最近发送的为\$p1\")\n" +
                    "        p0.toString() + \" \" + p1\n" +
                    "    })\n" +
                    "            .subscribe { s -> println(s) }\n" +
                    "    Thread.sleep(5000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "o1最近发送的为1 o2最近发送的为a\n" +
                    "1 a\n" +
                    "o1最近发送的为2 o2最近发送的为a\n" +
                    "2 a\n" +
                    "o1最近发送的为3 o2最近发送的为a\n" +
                    "3 a\n" +
                    "o1最近发送的为3 o2最近发送的为b\n" +
                    "3 b\n" +
                    "o1最近发送的为4 o2最近发送的为b\n" +
                    "4 b\n" +
                    "\n" +
                    "//tips \n" +
                    "combineLatest中每一个observable发射事件时它会去其它observable拿到它们最近发送的事件然后组合成一个新的事件发送到下游\n" +
                    "如果其它observable只要有一个没有发送事件,则丢弃事件" +
                    "一般用于表单验证\n"
    )

    val OPERATOR_JOIN = Operator(
            "Join",
            "combine items emitted by two Observables whenever an item from one Observable is emitted during a time window defined according to an item emitted by the other Observable",
            "The Join operator combines the items emitted by two Observables, and selects which items to combine based on duration-windows that you define on a per-item basis. You implement these windows as Observables whose lifespans begin with each item emitted by either Observable. When such a window-defining Observable either emits an item or completes, the window for the item it is associated with closes. So long as an item’s window is open, it will combine with any item emitted by the other Observable. You define the function by which the items combine.",
            R.drawable.operator_join,
            "fun main(args: Array<String>) {\n" +
                    "    val obsA: Observable<Long> = Observable.just(1L).delay(2,TimeUnit.SECONDS)\n" +
                    "    val obsB: Observable<String> = Observable.just(\"A\",\"B\").delay(4,TimeUnit.SECONDS)\n" +
                    "\n" +
                    "    //A的窗口期,也就是返回的observable的生命周期,为4s\n" +
                    "    val funcA = Function<Long, Observable<Long>> {\n" +
                    "        Observable.timer(4, TimeUnit.SECONDS)\n" +
                    "    }\n" +
                    "\n" +
                    "    //B的窗口期,为2s\n" +
                    "    val funcB = Function<String, Observable<Long>> {\n" +
                    "        Observable.timer(2, TimeUnit.SECONDS)\n" +
                    "    }\n" +
                    "\n" +
                    "    //结合的方式\n" +
                    "    val funcF = BiFunction<Long, String, String> { t1, t2 ->\n" +
                    "        println(\"t1 = \$t1 t2 = \$t2\")\n" +
                    "        return@BiFunction t1.toString() + t2\n" +
                    "    }\n" +
                    "    obsA.join(obsB, funcA, funcB, funcF)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "\n" +
                    "    Thread.sleep(5000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "t1 = 1 t2 = A\n" +
                    "1A\n" +
                    "t1 = 1 t2 = B\n" +
                    "1B\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "比较抽象的操作符\n" +
                    "每个observable有一个指定的窗口期,使用observable实现,即窗口期为observable的生命周期\n" +
                    "如果任意一个observable发射的事件在另一个observable发射事件的窗口期内,则通过结合函数结合,然后发射到下游"
    )

    val OPERATOR_MERGE = Operator(
            "Merge",
            "combine multiple Observables into one by merging their emissions",
            "You can combine the output of multiple Observables so that they act like a single Observable, by using the Merge operator.\n" +
                    "\n" +
                    "Merge may interleave the items emitted by the merged Observables (a similar operator, Concat, does not interleave items, but emits all of each source Observable’s items in turn before beginning to emit items from the next source Observable).",
            R.drawable.operator_merge,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.merge(Observable.just(1, 3, 5), Observable.just(2, 4, 6))\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "3\n" +
                    "5\n" +
                    "2\n" +
                    "4\n" +
                    "6\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "将多个observable的事件序列合并为一个事件序列发送到下游\n" +
                    "数据可能会交错"
    )

    val OPERATOR_START_WITH = Operator(
            "StartWith",
            "emit a specified sequence of items before beginning to emit the items from the source Observable",
            "If you want an Observable to emit a specific sequence of items before it begins emitting the items normally expected from it, apply the StartWith operator to it.\n" +
                    "\n" +
                    "(If, on the other hand, you want to append a sequence of items to the end of those normally emitted by an Observable, you want the Concat operator.)",
            R.drawable.operator_start_with,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(4,5,6)\n" +
                    "            .startWith(Observable.just(1,2,3))\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "在原始数据源前面插入指定的数据发送给下游\n" +
                    "startWith接受单个item或者一个Observable"
    )

    val OPERATOR_SWITCH = Operator(
            "Switch",
            "convert an Observable that emits Observables into a single Observable that emits the items emitted by the most-recently-emitted of those Observables",
            "Switch subscribes to an Observable that emits Observables. Each time it observes one of these emitted Observables, the Observable returned by Switch unsubscribes from the previously-emitted Observable begins emitting items from the latest Observable. Note that it will unsubscribe from the previously-emitted Observable when a new Observable is emitted from the source Observable, not when the new Observable emits an item. This means that items emitted by the previous Observable between the time the subsequent Observable is emitted and the time that subsequent Observable itself begins emitting items will be dropped (as with the yellow circle in the diagram above).",
            R.drawable.operator_switch,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.switchOnNext<Int>(Observable.just(Observable.just(1,2),Observable.just(3,4)))\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "switchOnNext把发射的o1,o2转化为两个observable发送到下游\n" +
                    "当下游收到o1,它开始发送o1的事件,如果发送o1d的事件过程中,收到了o2,则开始发送o2的事件,丢弃o1的事件\n"
    )

    val OPERATOR_ZIP = Operator(
            "Zip",
            "combine the emissions of multiple Observables together via a specified function and emit single items for each combination based on the results of this function",
            "The Zip method returns an Observable that applies a function of your choosing to the combination of items emitted, in sequence, by two (or more) other Observables, with the results of this function becoming the items emitted by the returned Observable. It applies this function in strict sequence, so the first item emitted by the new Observable will be the result of the function applied to the first item emitted by Observable #1 and the first item emitted by Observable #2; the second item emitted by the new zip-Observable will be the result of the function applied to the second item emitted by Observable #1 and the second item emitted by Observable #2; and so forth. It will only emit as many items as the number of items emitted by the source Observable that emits the fewest items.",
            R.drawable.operator_zip,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.zip(Observable.just(1, 2, 3),\n" +
                    "            Observable.just(\"A\", \"B\", \"C\"),\n" +
                    "            io.reactivex.functions.BiFunction<Int, String, String> { t1, t2 ->\n" +
                    "                t1.toString() + t2\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1A\n" +
                    "2B\n" +
                    "3C\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "zip结合两个observable的事件,类似于conbineLatest,不过更简单,它是一对一结合"
    )

    val OPERATOR_RETRY = Operator(
            "Retry",
            "if a source Observable emits an error, resubscribe to it in the hopes that it will complete without error",
            "The Retry operator responds to an onError notification from the source Observable by not passing that call through to its observers, but instead by resubscribing to the source Observable and giving it another opportunity to complete its sequence without error. Retry always passes onNext notifications through to its observers, even from sequences that terminate with an error, so this can cause duplicate emissions (as shown in the diagram above).",
            R.drawable.operator_retry,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.create<Int> {\n" +
                    "        it.onNext(1)\n" +
                    "        it.onNext(2)\n" +
                    "        it.onError(Throwable(\"Error\"))\n" +
                    "    }\n" +
                    "            .retry(2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "1\n" +
                    "2\n" +
                    "1\n" +
                    "2\n" +
                    "java.lang.Throwable: Error\n" +
                    "\n" +
                    "\n" +
                    "//tips\n" +
                    "当上游发送error时,使用retry可以再次订阅上游\n" +
                    "可以通过参数传入需要重试的次数\n" +
                    "还可以使用retryWhen()更加具体的确定重新订阅条件"

    )

    val OPERATOR_CATCH = Operator(
            "Catch",
            "recover from an onError notification by continuing the sequence without error",
            "The Catch operator intercepts an onError notification from the source Observable and, instead of passing it through to any observers, replaces it with some other item or sequence of items, potentially allowing the resulting Observable to terminate normally or not to terminate at all.\n" +
                    "\n" +
                    "There are several variants of the Catch operator, and a variety of names used by different ReactiveX implementations to describe this operation, as you can see in the sections below.\n" +
                    "\n" +
                    "In some ReactiveX implementations, there is an operator called something like “OnErrorResumeNext” that behaves like a Catch variant: specifically reacting to an onError notification from the source Observable. In others, there is an operator with that name that behaves more like a Concat variant: performing the concatenation operation regardless of whether the source Observable terminates normally or with an error. This is unfortunate and confusing, but something we have to live with.",
            R.drawable.operator_catch,
            "//重载:onErrorResumeNext onErrorReturn onErrorReturnItem onExceptionResumeNext\n" +
                    "fun main(args: Array<String>) {\n" +
                    "    Observable.create<Int> {\n" +
                    "        it.onNext(1)\n" +
                    "        it.onNext(2)\n" +
                    "        it.onError(Throwable(\"Error\"))\n" +
                    "    }\n" +
                    "            .onErrorResumeNext(Function {\n" +
                    "                return@Function Observable.just(1, 2, 3)\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "\n" +
                    "//tips\n" +
                    "catch实际使用的是onErrorResumeNext onErrorReturn onErrorReturnItem onExceptionResumeNext这些操作符\n" +
                    "可根据需要灵活的处理error事件"
    )

    val OPERATOR_DELAY = Operator(
            "Delay",
            "shift the emissions from an Observable forward in time by a particular amount",
            "The Delay operator modifies its source Observable by pausing for a particular increment of time (that you specify) before emitting each of the source Observable’s items. This has the effect of shifting the entire sequence of items emitted by the Observable forward in time by that specified increment.",
            R.drawable.operator_delay,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1)\n" +
                    "            .delay(2,TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(5000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "延迟某个时间段向下游发送数据\n" +
                    "还可以使用delaySubscription()对订阅进行延迟"
    )

    val OPERATOR_DO = Operator(
            "Do",
            "register an action to take upon a variety of Observable lifecycle events",
            "You can register callbacks that ReactiveX will call when certain events take place on an Observable, where those callbacks will be called independently from the normal set of notifications associated with an Observable cascade. There are a variety of operators that various ReactiveX implementations have designed to allow for this.",
            R.drawable.operator_do,
            "//重载:doAfterTerminate doOnComplete doOnDispose doOnEach doOnError doOnLifecycle doOnNext doOnSubscribe doOnTerminate onTerminateDetach\n" +
                    "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3)\n" +
                    "            .doOnNext { println(\"onNext->\$it\") }\n" +
                    "            .doOnComplete { println(\"onComplete->\") }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "onNext->1\n" +
                    "1\n" +
                    "onNext->2\n" +
                    "2\n" +
                    "onNext->3\n" +
                    "3\n" +
                    "onComplete->\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "do操作符可以理解为对某个事件进行拦截,然后进行一些操作\n"
    )

    val OPERATOR_MATERIALIZE = Operator(
            "Materialize",
            "represent both the items emitted and the notifications sent as emitted items, or reverse this process",
            "A well-formed, finite Observable will invoke its observer’s onNext method zero or more times, and then will invoke either the onCompleted or onError method exactly once. The Materialize operator converts this series of invocations — both the original onNext notifications and the terminal onCompleted or onError notification — into a series of items emitted by an Observable.",
            R.drawable.operator_materialize,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3)\n" +
                    "            .materialize()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "OnNextNotification[1]\n" +
                    "OnNextNotification[2]\n" +
                    "OnNextNotification[3]\n" +
                    "OnCompleteNotification\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "materialize将事件包装成Notification类发送到下游"
    )

    val OPERATOR_DEMATERIALIZE = Operator(
            "Dematerialize",
            " reverse this materialize process",
            "The Dematerialize operator reverses this process. It operates on an Observable that has previously been transformed by Materialize and returns it to its original form.",
            R.drawable.operator_dematerialize,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3)\n" +
                    "            .materialize()\n" +
                    "            .dematerialize<Int>()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "materialize的逆过程"
    )

    val OPERATOR_OBSERVE_ON = Operator(
            "ObserveOn",
            "specify the Scheduler on which an observer will observe this Observable",
            "Many implementations of ReactiveX use “Schedulers” to govern an Observable’s transitions between threads in a multi-threaded environment. You can instruct an Observable to send its notifications to observers on a particular Scheduler by means of the ObserveOn operator.\nNote that ObserveOn will forward an onError termination notification immediately if it receives one, and will not wait for a slow-consuming observer to receive any not-yet-emitted items that it is aware of first. This may mean that the onError notification jumps ahead of (and swallows) items emitted by the source Observable, as in the diagram above.",
            R.drawable.operator_observe_on,
            "fun main(args: Array<String>) {\n" +
                    "    println(\"Thread Id = \" + Thread.currentThread().id)\n" +
                    "    Observable.just(1)\n" +
                    "            .observeOn(Schedulers.io())\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(\"Thead Id = \" + Thread.currentThread().id)\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                    ,\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "Thread Id = 1\n" +
                    "Thead Id = 13\n" +
                    "1\n" +
                    "\n" +
                    "//tips\n" +
                    "observeOn用于指定下游对事件处理的线程\n" +
                    "observeOn类似于挡板,挡板下面的操作按照上一块挡板指定的调度进行"
    )

    val OPERATOR_SERIALIZE = Operator(
            "Serialize",
            "force an Observable to make serialized calls and to be well-behaved",
            "It is possible for an Observable to invoke its observers’ methods asynchronously, perhaps from different threads. This could make such an Observable violate the Observable contract, in that it might try to send an OnCompleted or OnError notification before one of its OnNext notifications, or it might make an OnNext notification from two different threads concurrently. You can force such an Observable to be well-behaved and synchronous by applying the Serialize operator to it.",
            R.drawable.operator_serialize,
            "目前还不知道有啥用"
    )

    val OPERATOR_SUBSCRIBE_ON = Operator(
            "SubscribeOn",
            "specify the Scheduler on which an Observable will operate",
            "Many implementations of ReactiveX use “Schedulers” to govern an Observable’s transitions between threads in a multi-threaded environment. You can instruct an Observable to do its work on a particular Scheduler by calling the Observable’s SubscribeOn operator.",
            R.drawable.operator_subscribe_on,
            "fun main(args: Array<String>) {\n" +
                    "    println(\"Thread Id = ${Thread.currentThread().id}\")\n" +
                    "    Observable.create<Int> {\n" +
                    "        println(\"Thread Id = ${Thread.currentThread().id}\")\n" +
                    "    }\n" +
                    "            .subscribeOn(Schedulers.io())\n" +
                    "            .doOnSubscribe {\n" +
                    "                println(\"Thread Id = ${Thread.currentThread().id}\")\n" +
                    "            }\n" +
                    "            .subscribe (\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "Thread Id = 1\n" +
                    "Thread Id = 1\n" +
                    "Thread Id = 13\n" +
                    "\n" +
                    "//tips\n" +
                    "subscribeOn用于指定产生事件的线程\n" +
                    "在一条链上多次使用subscribeOn只有最上面的生效"
    )

    val OPERATOR_TIME_INTERVAL = Operator(
            "TimeInterval",
            "convert an Observable that emits items into one that emits indications of the amount of time elapsed between those emissions",
            "The TimeInterval operator intercepts the items from the source Observable and emits in their place objects that indicate the amount of time that elapsed between pairs of emissions.",
            R.drawable.operator_time_interval,
            "fun main(args: Array<String>) {\n" +
                    "    val time = System.currentTimeMillis()\n" +
                    "    Observable.create<Int> {\n" +
                    "        Thread.sleep(500)\n" +
                    "        it.onNext(1)\n" +
                    "\n" +
                    "        Thread.sleep(600)\n" +
                    "        it.onNext(2)\n" +
                    "\n" +
                    "        Thread.sleep(700)\n" +
                    "        it.onNext(3)\n" +
                    "    }\n" +
                    "            .timeInterval(TimeUnit.MILLISECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(\"time = \${it.time()} value = \${it.value()}\")\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "time = 500 value = 1\n" +
                    "time = 600 value = 2\n" +
                    "time = 700 value = 3\n" +
                    "\n" +
                    "//tips\n" +
                    "TimeInterval将距离上一个事件发送所消耗的时间和事件包装成Timed类发送到下游"
    )

    val OPERATOR_TIME_OUT = Operator(
            "TimeOut",
            "mirror the source Observable, but issue an error notification if a particular period of time elapses without any emitted items",
            "The Timeout operator allows you to abort an Observable with an onError termination if that Observable fails to emit any items during a specified span of time.",
            R.drawable.operator_timeout,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.create<Int> {\n" +
                    "        it.onNext(1)\n" +
                    "        it.onNext(2)\n" +
                    "\n" +
                    "        Thread.sleep(500)\n" +
                    "        it.onNext(3)\n" +
                    "    }\n" +
                    "            .timeout(400, TimeUnit.MILLISECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "java.util.concurrent.TimeoutException\n" +
                    "\n" +
                    "//tips\n" +
                    "在指定时间内如果上游未发送事件则会发送一个error事件"
    )

    val OPERATOR_TIME_STAMP = Operator(
            "TimeStamp",
            "attach a timestamp to each item emitted by an Observable indicating when it was emitted",
            "The Timestamp operator attaches a timestamp to each item emitted by the source Observable before reemitting that item in its own sequence. The timestamp indicates at what time the item was emitted.",
            R.drawable.operator_timestamp,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3)\n" +
                    "            .timestamp()\n" +
                    "            .doOnSubscribe {\n" +
                    "                println(\"current time = \"+System.currentTimeMillis())\n" +
                    "            }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(\"time = \${it.time()} value = \${it.value()}\")\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "current time = 1524993935773\n" +
                    "time = 1524993935773 value = 1\n" +
                    "time = 1524993935773 value = 2\n" +
                    "time = 1524993935773 value = 3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "向下游发送对事件包装的Timed类" +
                    "Timed类保存了事件被发射的时间\n"
    )

    val OPERATOR_USING = Operator(
            "Using",
            "create a disposable resource that has the same lifespan as the Observable",
            "The Using operator is a way you can instruct an Observable to create a resource that exists only during the lifespan of the Observable and is disposed of when the Observable terminates.",
            R.drawable.operator_using,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.using(\n" +
                    "            {\n" +
                    "                val file = File(\"./using.text\")\n" +
                    "                file  //返回临时文件\n" +
                    "            },\n" +
                    "            {\n" +
                    "                it.createNewFile()  //处理临时文件\n" +
                    "                Observable.just(\"Create File\") //向下游发消息\n" +
                    "            },\n" +
                    "            {\n" +
                    "                it.deleteOnExit()  //回收临时文件\n" +
                    "                println(\"删除文件~\")\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "Create File\n" +
                    "删除文件~\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "using提供一种对临时文件处理的回调\n"
    )

    val OPERATOR_ALL = Operator(
            "All",
            "determine whether all items emitted by an Observable meet some criteria",
            "Pass a predicate function to the All operator that accepts an item emitted by the source Observable and returns a boolean value based on an evaluation of that item. All returns an Observable that emits a single boolean value: true if and only if the source Observable terminates normally and every item emitted by the source Observable evaluated as true according to this predicate; false if any item emitted by the source Observable evaluates as false according to this predicate.",
            R.drawable.operator_all,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3)\n" +
                    "            .all { it<4 }\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                   )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "true\n" +
                    "\n" +
                    "//tips\n" +
                    "所有事件满足条件向下游发射true事件"
    )

    val OPERATOR_AMB = Operator(
            "Amb",
            "given two or more source Observables, emit all of the items from only the first of these Observables to emit an item or notification",
            "When you pass a number of source Observables to Amb, it will pass through the emissions and notifications of exactly one of these Observables: the first one that sends a notification to Amb, either by emitting an item or sending an onError or onCompleted notification. Amb will ignore and discard the emissions and notifications of all of the other source Observables.",
            R.drawable.operator_amb,
            "fun main(args: Array<String>) {\n" +
                    "    \n" +
                    "    val obs1 = Observable.just(1,2,3).delay(100,TimeUnit.MILLISECONDS)\n" +
                    "    val obs2 = Observable.just(4,5,6).delay(200,TimeUnit.MILLISECONDS)\n" +
                    "    val obs3 = Observable.just(7,8,9).delay(300,TimeUnit.MILLISECONDS)\n" +
                    "\n" +
                    "    Observable.amb(arrayListOf(obs1,obs2,obs3))\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                      println(\"complete\")\n" +
                    "                    }\n" +
                    "                   )\n" +
                    "    Thread.sleep(500)\n" +
                    "}\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "接受一个observable集合,只发送最先发送的事件的observable的所有事件"
    )

    val OPERATOR_CONTAINS = Operator(
            "Contains",
            "determine whether an Observable emits a particular item or not",
            "Pass the Contains operator a particular item, and the Observable it returns will emit true if that item is emitted by the source Observable, or false if the source Observable terminates without emitting that item.\n" +
                    "\n" +
                    "A related operator, IsEmpty returns an Observable that emits true if and only if the source Observable completes without emitting any items. It emits false if the source Observable emits an item.",
            R.drawable.operator_contains,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1,2,3,4)\n" +
                    "            .contains(5)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "                   )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "false\n" +
                    "\n" +
                    "//tips\n" +
                    "判断事件中是否包含指定事件\n" +
                    "根据equals方法判断是否为相同元素\n"
    )

    val OPERATOR_DEFAULT_IF_EMPTY = Operator(
            "DefaultIfEmpty",
            "emit items from the source Observable, or a default item if the source Observable emits nothing",
            "The DefaultIfEmpty operator simply mirrors the source Observable exactly if the source Observable emits any items. If the source Observable terminates normally (with an onComplete) without emitting any items, the Observable returned from DefaultIfEmpty will instead emit a default item of your choosing before it too completes.",
            R.drawable.operator_default_if_empty,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.empty<Int>()\n" +
                    "            .defaultIfEmpty(5)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "5\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "上游如果不发送任何时间则发送指定的默认事件"
    )

    val OPERATOR_SEQUENCE_EQUAL = Operator(
            "SequenceEqual",
            "determine whether two Observables emit the same sequence of items",
            "Pass SequenceEqual two Observables, and it will compare the items emitted by each Observable, and the Observable it returns will emit true only if both sequences are the same (the same items, in the same order, with the same termination state).",
            R.drawable.operator_sequence_equal,
            "fun main(args: Array<String>) {\n" +
                    "    val obs1 = Observable.just(1,2,3)\n" +
                    "    val obs2 = Observable.just(1,2,3)\n" +
                    "    Observable.sequenceEqual(obs1,obs2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "true\n" +
                    "\n" +
                    "//tips\n" +
                    "判断两个observable是否发送相同的事件序列"
    )

    val OPERATOR_SKIP_UNTIL = Operator(
            "SkipUntil",
            "discard items emitted by an Observable until a second Observable emits an item",
            "The SkipUntil subscribes to the source Observable, but ignores its emissions until such time as a second Observable emits an item, at which point SkipUntil begins to mirror the source Observable.",
            R.drawable.operator_skip_until,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    val obs1 = Observable.interval(1, TimeUnit.SECONDS).take(10)\n" +
                    "    val obs2 = Observable.just(1).delay(5,TimeUnit.SECONDS)\n" +
                    "\n" +
                    "       obs1.skipUntil(obs2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "7\n" +
                    "8\n" +
                    "9\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "skipUntil表示丢弃事件直到接受的observable发射一个事件"
    )

    val OPERATOR_SKIP_WHILE = Operator(
            "SkipWhile",
            "discard items emitted by an Observable until a specified condition becomes false",
            "The SkipWhile subscribes to the source Observable, but ignores its emissions until such time as some condition you specify becomes false, at which point SkipWhile begins to mirror the source Observable.",
            R.drawable.operator_skip_while,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.interval(1, TimeUnit.SECONDS).take(10)\n" +
                    "            .skipWhile({\n" +
                    "                it<4\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "7\n" +
                    "8\n" +
                    "9\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "skipWhile丢弃上游发送的事件,直到接受的条件返回false"
    )

    val OPERATOR_TAKE_UNTIL = Operator(
            "TakeUntil",
            "discard any items emitted by an Observable after a second Observable emits an item or terminates",
            "The TakeUntil subscribes and begins mirroring the source Observable. It also monitors a second Observable that you provide. If this second Observable emits an item or sends a termination notification, the Observable returned by TakeUntil stops mirroring the source Observable and terminates.",
            R.drawable.operator_take_until,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    val obs1 = Observable.interval(1, TimeUnit.SECONDS).take(10)\n" +
                    "    val obs2 = Observable.just(1).delay(3, TimeUnit.SECONDS)\n" +
                    "\n" +
                    "            obs1\n" +
                    "            .takeUntil(obs2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "当takeUntil接受的observable发射任何一个数据,takeUntil链上的事件开始被丢弃"
    )

    val OPERATOR_TAKE_WHILE = Operator(
            "TakeWhile",
            "mirror items emitted by an Observable until a specified condition becomes false",
            "The TakeWhile mirrors the source Observable until such time as some condition you specify becomes false, at which point TakeWhile stops mirroring the source Observable and terminates its own Observable.",
            R.drawable.operator_take_while,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.interval(1, TimeUnit.SECONDS).take(10)\n" +
                    "            .takeWhile({\n" +
                    "                it < 4\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "当takeWhile接受的条件返回false时,takeUntil链上的事件开始被丢弃"
    )

    val OPERATOR_CONCAT = Operator(
            "Concat",
            "emit the emissions from two or more Observables without interleaving them",
            "he Concat operator concatenates the output of multiple Observables so that they act like a single Observable, with all of the items emitted by the first Observable being emitted before any of the items emitted by the second Observable (and so forth, if there are more than two).\n" +
                    "\n" +
                    "Concat waits to subscribe to each additional Observable that you pass to it until the previous Observable completes. Note that because of this, if you try to concatenate a “hot” Observable, that is, one that begins emitting items immediately and before it is subscribed to, Concat will not see, and therefore will not emit, any items that Observable emits before all previous Observables complete and Concat subscribes to the “hot” Observable.\n" +
                    "\n" +
                    "In some ReactiveX implementations there is also a ConcatMap operator (a.k.a. concat_all, concat_map, concatMapObserver, for, forIn/for_in, mapcat, selectConcat, or selectConcatObserver) that transforms the items emitted by a source Observable into corresponding Observables and then concatenates the items emitted by each of these Observables in the order in which they are observed and transformed.\n" +
                    "\n" +
                    "The StartWith operator is similar to Concat, but prepends, rather than appends, items or emissions of items to those emitted by a source Observable.\n" +
                    "\n" +
                    "The Merge operator is also similar. It combines the emissions of two or more Observables, but may interleave them, whereas Concat never interleaves the emissions from multiple Observables.",
            R.drawable.operator_concat,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    val obs1 = Observable.just(1, 2, 3).delay(1, TimeUnit.SECONDS)\n" +
                    "    val obs2 = Observable.just(4, 5, 6)\n" +
                    "\n" +
                    "    Observable.concat(obs1, obs2)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(5000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "类似于merge,合并多个observable的事件,但是保证了事件有序性"
    )

    val OPERATOR_REDUCE = Operator(
            "Reduce",
            "apply a function to each item emitted by an Observable, sequentially, and emit the final value",
            "The Reduce operator applies a function to the first item emitted by the source Observable and then feeds the result of the function back into the function along with the second item emitted by the source Observable, continuing this process until the source Observable emits its final item and completes, whereupon the Observable returned from Reduce emits the final value returned from the function.\n" +
                    "\n" +
                    "This sort of operation is sometimes called “accumulate,” “aggregate,” “compress,” “fold,” or “inject” in other contexts.",
            R.drawable.operator_reduce,
            "fun main(args: Array<String>) {\n" +
                    "    Observable.just(1, 2, 3, 4, 5)\n" +
                    "            .reduce({ result, newValue ->\n" +
                    "                result + newValue\n" +
                    "            })\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "15\n" +
                    "\n" +
                    "//tips\n" +
                    "对于第一个事件,reduce会吞掉他然后把它作为result,下一个事件作为newValue传递给reduce接受的函数,以此类推,然后发射最后计算出的结果" +
                    "类似于scan"
    )

    val OPERATOR_CONNECT = Operator(
            "Connect",
            "instruct a connectable Observable to begin emitting items to its subscribers",
            "A connectable Observable resembles an ordinary Observable, except that it does not begin emitting items when it is subscribed to, but only when the Connect operator is applied to it. In this way you can wait for all intended observers to subscribe to the Observable before the Observable begins emitting items.",
            R.drawable.operator_connect,
            "fun main(args: Array<String>) {\n" +
                    "    \n" +
                    "    //每1s发送一个long型 共10个\n" +
                    "    val obs = Observable.interval(0, 1, TimeUnit.SECONDS).take(10)\n" +
                    "    \n" +
                    "    //调用publish()将普通observable转换为ConnectObservable\n" +
                    "    val connectObs = obs.publish()\n" +
                    "\n" +
                    "    connectObs\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    \n" +
                    "    //在调用connect方法之前订阅的,能收到所有事件\n" +
                    "    connectObs.connect()\n" +
                    "    \n" +
                    "    //在调用connect方法之后订阅的,只能收到剩余事件\n" +
                    "    connectObs\n" +
                    "            .delaySubscription(3, TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "4\n" +
                    "5\n" +
                    "5\n" +
                    "6\n" +
                    "6\n" +
                    "7\n" +
                    "7\n" +
                    "8\n" +
                    "8\n" +
                    "9\n" +
                    "9\n" +
                    "complete\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "ConnectObservable调用connect()之后,才开始发射事件\n" +
                    "connect可以更灵活的控制上游发射事件的时间\n"
    )

    val OPERATOR_PUBLISH = Operator(
            "Publish",
            "convert an ordinary Observable into a connectable Observable",
            "A connectable Observable resembles an ordinary Observable, except that it does not begin emitting items when it is subscribed to, but only when the Connect operator is applied to it. In this way you can prompt an Observable to begin emitting items at a time of your choosing.",
            R.drawable.operator_publish,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    //每1s发送一个long型 共10个\n" +
                    "    val obs = Observable.interval(0, 1, TimeUnit.SECONDS).take(10)\n" +
                    "\n" +
                    "    //调用publish()将普通observable转换为ConnectObservable\n" +
                    "    val connectObs = obs.publish()\n" +
                    "\n" +
                    "    connectObs\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "\n" +
                    "    //在调用connect方法之前订阅的,能收到所有事件\n" +
                    "    connectObs.connect()\n" +
                    "\n" +
                    "    //在调用connect方法之后订阅的,只能收到剩余事件\n" +
                    "    connectObs\n" +
                    "            .delaySubscription(3, TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    }\n" +
                    "            )\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "4\n" +
                    "5\n" +
                    "5\n" +
                    "6\n" +
                    "6\n" +
                    "7\n" +
                    "7\n" +
                    "8\n" +
                    "8\n" +
                    "9\n" +
                    "9\n" +
                    "complete\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "publish一般和connect配合,负责转化observable"
    )

    val OPERATOR_REFCOUNT = Operator(
            "RefCount",
            "make a Connectable Observable behave like an ordinary Observable",
            "A connectable Observable resembles an ordinary Observable, except that it does not begin emitting items when it is subscribed to, but only when the Connect operator is applied to it. In this way you can prompt an Observable to begin emitting items at a time of your choosing.\n" +
                    "\n" +
                    "The RefCount operator automates the process of connecting to and disconnecting from a connectable Observable. It operates on a connectable Observable and returns an ordinary Observable. When the first observer subscribes to this Observable, RefCount connects to the underlying connectable Observable. RefCount then keeps track of how many other observers subscribe to it and does not disconnect from the underlying connectable Observable until the last observer has done so.",
            R.drawable.operator_ref_count,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    //每1s向下游发射一个数据\n" +
                    "    val obsOrigin = Observable.interval(0, 1, TimeUnit.SECONDS).take(10)\n" +
                    "\n" +
                    "    //转化为ConnectObservable\n" +
                    "    val connectObs = obsOrigin.publish()\n" +
                    "\n" +
                    "    /*\n" +
                    "    * 转化为普通obs,虽然是普通obs但是还是有区别\n" +
                    "    * 当这个obs被订阅时开始发射数据,后面再订阅它只能接受后面的事件*/\n" +
                    "    val obsNormal = connectObs.refCount()\n" +
                    "\n" +
                    "    obsNormal\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "    \n" +
                    "    //延迟3s订阅\n" +
                    "    obsNormal\n" +
                    "            .delaySubscription(3, TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "    Thread.sleep(10000)\n" +
                    "}"
    )

    val OPERATOR_REPLAY = Operator(
            "Replay",
            "ensure that all observers see the same sequence of emitted items, even if they subscribe after the Observable has begun emitting items",
            "A connectable Observable resembles an ordinary Observable, except that it does not begin emitting items when it is subscribed to, but only when the Connect operator is applied to it. In this way you can prompt an Observable to begin emitting items at a time of your choosing.\n" +
                    "\n" +
                    "If you apply the Replay operator to an Observable before you convert it into a connectable Observable, the resulting connectable Observable will always emit the same complete sequence to any future observers, even those observers that subscribe after the connectable Observable has begun to emit items to other subscribed observers.",
            R.drawable.operator_replay,
            "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    //每1s向下游发射一个数据\n" +
                    "    val obsOrigin = Observable.interval(0, 1, TimeUnit.SECONDS).take(10)\n" +
                    "\n" +
                    "    //转化为ConnectObservable之前,调用replay(),并指定缓存个数为4个\n" +
                    "    val connectObs = obsOrigin\n" +
                    "            .replay(4)\n" +
                    "\n" +
                    "    //开始发送数据\n" +
                    "    connectObs.connect()\n" +
                    "\n" +
                    "    connectObs\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "\n" +
                    "    //延迟3s订阅\n" +
                    "    connectObs\n" +
                    "            .delaySubscription(3, TimeUnit.SECONDS)\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(\"complete\")\n" +
                    "                    })\n" +
                    "    Thread.sleep(10000)\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "0\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "4\n" +
                    "5\n" +
                    "5\n" +
                    "6\n" +
                    "6\n" +
                    "7\n" +
                    "7\n" +
                    "8\n" +
                    "8\n" +
                    "9\n" +
                    "9\n" +
                    "complete\n" +
                    "complete\n" +
                    "\n" +
                    "//tips\n" +
                    "replay用于缓存ConnectObservable发射的数据\n" +
                    "replay会自动返回一个ConnectObservable,无需再调用publish"
    )

    val OPERATOR_TO = Operator(
            "To",
            "convert an Observable into another object or data structure",
            "The various language-specific implementations of ReactiveX have a variety of operators that you can use to convert an Observable, or a sequence of items emitted by an Observable, into another variety of object or data structure. Some of these block until the Observable terminates and then produce an equivalent object or data structure; others return an Observable that emits such an object or data structure.\n" +
                    "\n" +
                    "In some implementations of ReactiveX, there is also an operator that converts an Observable into a “Blocking” Observable. A Blocking Observable extends the ordinary Observable by providing a set of methods, operating on the items emitted by the Observable, that block. Some of the To operators are in this Blocking Obsevable set of extended operations.",
            R.drawable.operator_to,
            "//重载:blockingIterable blockingLatest blockingMostRecent blockingNext sorted to toFuture toList toMap toMultimap toSortedList\n" +
                    "fun main(args: Array<String>) {\n" +
                    "\n" +
                    "    val obs = Observable.just(1, 2, 3, 4)\n" +
                    "\n" +
                    "    obs.toList()\n" +
                    "            .subscribe(\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    },\n" +
                    "                    {\n" +
                    "                        println(it)\n" +
                    "                    })\n" +
                    "\n" +
                    "    obs.blockingIterable()\n" +
                    "            .forEach({\n" +
                    "                println(it)\n" +
                    "            })\n" +
                    "\n" +
                    "}\n" +
                    "\n" +
                    "//output\n" +
                    "[1, 2, 3, 4]\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "\n" +
                    "//tips\n" +
                    "to操作符用于将一个observable转换为其它数据结构"

    )
}