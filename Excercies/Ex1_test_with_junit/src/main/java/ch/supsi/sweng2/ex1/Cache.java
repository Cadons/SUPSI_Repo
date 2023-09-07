package ch.supsi.sweng2.ex1;

public interface Cache {
    int length(int v) throws Exception;
    public Worker createWorker(int v);
}
