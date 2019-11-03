package site.zhangsun.generator.callback;

import org.mybatis.generator.api.ProgressCallback;

public class CallBack implements ProgressCallback {
    @Override
    public void introspectionStarted(int i) {
        System.out.println("introspectionStarted: " + i + "   " + System.currentTimeMillis());
    }

    @Override
    public void generationStarted(int i) {
        System.out.println("generationStarted: " + i+ System.currentTimeMillis());
    }

    @Override
    public void saveStarted(int i) {
        System.out.println("saveStarted: " + i+ System.currentTimeMillis());
    }

    @Override
    public void startTask(String s) {
        System.out.println("startTask: " + System.currentTimeMillis());
    }

    @Override
    public void done() {
        System.out.println("done " + System.currentTimeMillis());
    }

    @Override
    public void checkCancel() throws InterruptedException {
        System.out.println("checkCancel " + System.currentTimeMillis());
    }
}
