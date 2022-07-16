package usecase;

public interface VoidUseCaseHandler<T extends UseCase> {

    void handle(T t);
}
