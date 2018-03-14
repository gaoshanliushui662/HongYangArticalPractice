package example.com.rxjavademo.concat;

import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by zhao.yaosheng on 2018/3/14.
 */

public class ConcatDemo {

    public static final String TAG = "Rxjava";

    public static void concat(){
        Observable.concat(Observable.just(1,2,3),
                        Observable.just(4,5,6),
                        Observable.just(7,8,9),
                        Observable.just(10,11,12))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("Rxjava", "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void concatArray(){
        Observable.concatArray(Observable.just(1,2,3),
                Observable.just(4,5,6),
                Observable.just(7,8,9),
                Observable.just(10,11,12),
                Observable.just(13,14,15))
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Integer value) {
                        Log.e("Rxjava", "接收到了事件" + value);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public static void zip(){
        Observable<Integer> observable1 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                Log.e(TAG, "被观察者1发送了事件1");
                e.onNext(1);
                // 为了方便展示效果，所以在发送事件后加入2s的延迟
                Thread.sleep(1000);

                Log.e(TAG, "被观察者1发送了事件2");
                e.onNext(2);
                Thread.sleep(1000);

                Log.e(TAG, "被观察者1发送了事件3");
                e.onNext(3);
//                Thread.sleep(1000);

                e.onComplete();
            }
        }).subscribeOn(Schedulers.io());

        Observable<String> observable2 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                Log.e(TAG, "被观察者2发送了事件A");
                e.onNext("A");
                Thread.sleep(1000);

                Log.e(TAG, "被观察者2发送了事件B");
                e.onNext("B");
                Thread.sleep(1000);

                Log.e(TAG, "被观察者2发送了事件C");
                e.onNext("C");
                Thread.sleep(1000);

                Log.e(TAG, "被观察者2发送了事件D");
                e.onNext("D");
//                Thread.sleep(1000);

                e.onComplete();
            }
        }).subscribeOn(Schedulers.newThread());

        Observable.zip(observable1, observable2, new BiFunction<Integer, String, String>() {
            @Override
            public String apply(Integer integer, String s) throws Exception {
                return integer + s;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                Log.e(TAG, "最终接收到的事件 =  " + value);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                Log.e(TAG, "onComplete");
            }
        });

    }

}
